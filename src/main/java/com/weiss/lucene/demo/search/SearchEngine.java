/*
 * SearchEngine.java
 *
 * Created on 6 March 2006, 14:52
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.weiss.lucene.demo.search;

import java.io.IOException;

import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;

/**
 * 
 * @author John
 */
public class SearchEngine {
	private final IndexSearcher searcher = null;
	private final QueryParser parser = null;

	/** Creates a new instance of SearchEngine */
	public SearchEngine() throws IOException {
		// searcher = new IndexSearcher(new IndexReader("index-directory"));
		// parser = new QueryParser("content", new StandardAnalyzer());
	}

	// public Hits performSearch(String queryString) throws IOException,
	// ParseException {
	// Query query = parser.parse(queryString);
	// Hits hits = searcher.search(query);
	// return hits;
	// }
}
