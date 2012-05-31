package com.weiss.jsoup;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class JsoupTest {
	private static final Logger log = Logger.getLogger(Jsoup.class);
	private static final String[] IMAGE_TYPE_ARRAY = { ".jpeg", ".jpg", ".png" };

	@Test
	public void testGetElementById() {
		String hello = "hello jsoup";
		String html = "<html><head><title>First parse</title></head>" + "<body><p>Parsed HTML into a doc.</p>"
				+ "<div id='jsoup'>" + hello + "</div>" + "</body></html>";
		Document doc = Jsoup.parse(html);
		Element content = doc.getElementById("jsoup");
		Assert.assertEquals(hello, content.text());
	}

	@Test
	public void testParseUrl() {
		parse("http://www.topit.me");
	}

	public void parse(String urlStr) {
		// 返回结果初始化。

		Document doc = null;
		try {
			doc = Jsoup.connect(urlStr).userAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.15)") // 设置User-Agent
					.timeout(5000) // 设置连接超时时间
					.get();
		} catch (MalformedURLException e) {
			log.error(e);
			return;
		} catch (IOException e) {
			if (e instanceof SocketTimeoutException) {
				log.error(e);
				return;
			}
			if (e instanceof UnknownHostException) {
				log.error(e);
				return;
			}
			log.error(e);
			return;
		}
		System.out.println(doc.title());
		Element head = doc.head();
		Elements metas = head.select("meta");
		for (Element meta : metas) {
			String content = meta.attr("content");
			if ("content-type".equalsIgnoreCase(meta.attr("http-equiv"))
					&& !StringUtils.startsWith(content, "text/html")) {
				log.debug(urlStr);
				return;
			}
			if ("description".equalsIgnoreCase(meta.attr("name"))) {
				System.out.println(meta.attr("content"));
			}
		}
		Element body = doc.body();

		List<String> imgSrcs = new LinkedList<String>();
		for (Element img : body.getElementsByTag("img")) {
			String imageUrl = img.attr("abs:src");// 获得绝对路径
			for (String suffix : IMAGE_TYPE_ARRAY) {
				if (imageUrl.indexOf("?") > 0) {
					imageUrl = imageUrl.substring(0, imageUrl.indexOf("?"));
				}
				if (StringUtils.endsWithIgnoreCase(imageUrl, suffix)) {
					imgSrcs.add(imageUrl);
					break;
				}
			}
		}
		System.out.println(imgSrcs);
	}
}
