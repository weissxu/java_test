package com.weiss.lucene;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

public class FsIndexer {
	public static final String INDEX = "/Users/siwei/lucene/index/";
	public static final String DATA = "/Users/siwei/lucene/data/";
	private static final String querystr = "JedisConnectionException client";

	// private static final String querystr =
	// "redis.clients.jedis.exceptions.JedisConnectionException client";

	@Test
	public void testIndex() throws IOException {
		Directory index = new SimpleFSDirectory(new File(INDEX));

		Analyzer luceneAnalyzer = new StandardAnalyzer(Version.LUCENE_35);
		IndexWriter indexWriter = new IndexWriter(index, new IndexWriterConfig(Version.LUCENE_35, luceneAnalyzer));
		File[] datas = new File(DATA).listFiles();
		for (File file : datas) {
			if (file.isFile() && file.getName().contains("class")) {
				System.out.println("Indexing file " + file.getCanonicalPath());
				Document document = new Document();
				Reader txtReader = new FileReader(file);
				document.add(new Field("path", file.getAbsolutePath(), Field.Store.YES, Field.Index.NOT_ANALYZED));
				document.add(new Field("content", txtReader));

				indexWriter.addDocument(document);
			}
		}

		indexWriter.close();
		System.out.println("success to index...");
	}

	@Test
	public void search() throws ParseException, Exception {

		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
		Query q = new QueryParser(Version.LUCENE_35, "content", analyzer).parse(querystr);

		// 3. search
		int hitsPerPage = 50;
		IndexReader reader = IndexReader.open(new SimpleFSDirectory(new File(INDEX)));
		IndexSearcher searcher = new IndexSearcher(reader);
		TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
		searcher.search(q, collector);
		ScoreDoc[] hits = collector.topDocs().scoreDocs;

		// 4. display results
		System.out.println("Found " + hits.length + " hits.");
		for (int i = 0; i < hits.length; ++i) {
			int docId = hits[i].doc;
			Document d = searcher.doc(docId);
			System.out.println((i + 1) + ". " + d.get("path"));
		}

		// searcher can only be closed when there
		// is no need to access the documents any more.
		searcher.close();
	}

}
