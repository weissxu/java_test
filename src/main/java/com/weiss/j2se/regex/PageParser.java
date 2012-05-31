package com.weiss.j2se.regex;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

//<a class="inner" target="new" title="爆笑恶搞！让你别把枪乱扔嘛！ @Youtube搞笑视频k" href="http://www.tudou.com/programs/view/DyXZzB8benQ/">helo
//<img class="pack_clipImg" width="120" height="90" alt="爆笑恶搞！让你别把枪乱扔嘛！ @Youtube搞笑视频k" src=" http://i3.tdimg.com/122/427/346/p.jpg ">
//</a>
public class PageParser {

	// public static final String TEMPLATE =
	// "<a target='_blank' href='http://www.tudou.com/playlist/p/l15083545i119721664.html' ><img src='http://i3.tdimg.com/b/20120302/c299.jpg' alt='' /></a>";
	public static final String TEMPLATE = "<a target=\"_blank\" href=\"http://www.tudou.com/playlist/p/l15083545i119721664.html\" ><img src=\"http://i3.tdimg.com/b/20120302/c299.jpg\" alt=\"\" /></a>";

	public static final String url = "http://movie.tudou.com";
	// public static final String url =
	// "http://www.tudou.com/playlist/lc0OPFWp5.html";
	public static final Log logger = LogFactory.getLog(PageParser.class);
	// public static final String REG =
	// "<a.*href=([\"'])?([\\s\\S]+)\1.*<img.*src=[\"']?([\\s\\S]+?)[\"']?.*</a>";
	public static final String REG = "href='([^']+)'|href=\"([^\"]+)\"";
	public static final String HREFREG = "<a.*?>[\\s\\S]*?</a>";
	public static final String TITLEREG = "title=[\"']?(.+)[\"']?.*";
	public static final String PATHNAME = "pageContent.txt";
	public static String CONTENT;
	public static Pattern pattern;
	public static Pattern hrefPattern;
	static {
		pattern = Pattern.compile(REG, Pattern.CASE_INSENSITIVE);
		// hrefPattern = Pattern.compile(HREFREG, Pattern.CASE_INSENSITIVE);
		// try {
		// CONTENT = getContent();
		// } catch (IOException e) {
		// logger.info("error occured while reading file...", e);
		// }
	}

	@Test
	public void testTemplate() {
		Matcher matcher = pattern.matcher(TEMPLATE);
		if (matcher.find()) {
			logger.info(matcher.group(2));
		}
	}

	@Test
	public void testGetContent() throws ClientProtocolException, IOException {
		System.out.println(getContent());
	}

	public static void main(String[] args) {
		logger.info("start...");
		List<String> hrefs = new LinkedList<String>();
		Matcher matcher = hrefPattern.matcher(CONTENT);
		while (matcher.find()) {
			hrefs.add(matcher.group());
		}
		for (String href : hrefs) {
			if (href.contains("img"))
				parseHref(href);
		}
	}

	private static void parseHref(String href) {
		Matcher matcher = pattern.matcher(href);
		if (matcher.matches()) {
			logger.info("1: " + matcher.group(1) + "   2: " + matcher.group(2));

		}
	}

	public static String getContent() throws ClientProtocolException, IOException {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		// System.out.println(entity.getContentEncoding().getValue());
		String content = EntityUtils.toString(entity, "gbk");
		EntityUtils.consume(entity);
		return content;
	}
}
