package com.weiss.lucene.chinese;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import java.io.IOException;

public class IKAnalyzerDemo {
    public static void main(String[] args) {
        String fieldName = "text";
        String text = "暖,花,寓意,自行车,心情,美景,Flowers,唯美,花 草 树 木,唯美 花 自然,花都开好了,干净";
        Analyzer analyzer = null;//new IKAnalyzer(true);
        Directory directory = null;
        IndexWriter iwriter = null;
        IndexReader ireader = null;
        IndexSearcher isearcher = null;
        try {
            directory = new RAMDirectory();
            IndexWriterConfig iwConfig = new IndexWriterConfig(Version.LUCENE_35, analyzer);
            iwConfig.setOpenMode(OpenMode.CREATE_OR_APPEND);
            iwriter = new IndexWriter(directory, iwConfig);
            Document doc = new Document();
            doc.add(new Field("ID", "10000", Field.Store.YES, Field.Index.NOT_ANALYZED));
            doc.add(new Field(fieldName, text, Field.Store.YES, Field.Index.ANALYZED));
            iwriter.addDocument(doc);
            iwriter.close();

            // 搜索过程**********************************
            // 实例化搜索器
            ireader = IndexReader.open(directory);
            isearcher = new IndexSearcher(ireader);
            String keyword = "草泥马";
            QueryParser qp = new QueryParser(Version.LUCENE_35, fieldName, analyzer);
            qp.setDefaultOperator(QueryParser.AND_OPERATOR);
            Query query = qp.parse(keyword);
            TopDocs topDocs = isearcher.search(query, 5);
            System.out.println("命中：" + topDocs.totalHits);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            for (int i = 0; i < topDocs.totalHits; i++) {
                Document targetDoc = isearcher.doc(scoreDocs[i].doc);
                System.out.println("内容：" + targetDoc.toString());
            }
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (LockObtainFailedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (ireader != null) {
                try {
                    ireader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (directory != null) {
                try {
                    directory.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
