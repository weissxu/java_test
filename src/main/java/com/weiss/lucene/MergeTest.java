package com.weiss.lucene;

import java.io.File;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

public class MergeTest {
	/**
	 * @author Shane Zhao about merge Index in PCCW BJDEV 将小索引文件合并到大的索引文件中去
	 * 
	 * @param from
	 *            将要合并到to文件的文件
	 * @param to
	 *            将from文件合并到该文件
	 * @param sa
	 */
	private static void mergeIndex(File from, File to, StandardAnalyzer sa) {
		IndexWriter indexWriter = null;
		try {
			System.out.println("正在合并索引文件!\t ");
			// indexWriter = new IndexWriter(to, sa, false);
			indexWriter = new IndexWriter(new SimpleFSDirectory(to), new IndexWriterConfig(Version.LUCENE_35, sa));
			// indexWriter.setMergeFactor(100000);
			// indexWriter.setMaxFieldLength(Integer.MAX_VALUE);
			// indexWriter.setMaxBufferedDocs(Integer.MAX_VALUE);
			// indexWriter.setMaxMergeDocs(Integer.MAX_VALUE);
			FSDirectory[] fs = { new SimpleFSDirectory(from) };
			indexWriter.addIndexes(fs);
			// indexWriter.optimize();
			indexWriter.close();
			System.out.println("已完成合并!\t ");
		} catch (Exception e) {
			System.out.println("合并索引出错！");
			e.printStackTrace();
		} finally {
			try {
				if (indexWriter != null)
					indexWriter.close();
			} catch (Exception e) {

			}

		}

	}

	public static void main(String[] areg) {
		File from = new File("F:/web/faq/lucene/indexDir");
		File to = new File("F:/indexDir");
		mergeIndex(from, to, new StandardAnalyzer(Version.LUCENE_35));
	}

}
