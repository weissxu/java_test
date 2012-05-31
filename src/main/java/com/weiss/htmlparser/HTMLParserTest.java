package com.weiss.htmlparser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.HtmlPage;

public class HTMLParserTest {
	/**
	 * 入口方法.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		String path = "http://www.blogjava.net/amigoxie";
		URL url = new URL(path);
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);

		InputStream inputStream = conn.getInputStream();
		InputStreamReader isr = new InputStreamReader(inputStream, "utf8");
		StringBuffer sb = new StringBuffer();
		BufferedReader in = new BufferedReader(isr);
		String inputLine;

		while ((inputLine = in.readLine()) != null) {
			sb.append(inputLine);
			sb.append("\n");
		}

		String result = sb.toString();

		readByHtml(result);
		readTextAndLinkAndTitle(result);
	}

	/**
	 * 按页面方式处理.解析标准的html页面
	 * 
	 * @param content
	 *            网页的内容
	 * @throws Exception
	 */
	public static void readByHtml(String content) throws Exception {
		Parser myParser;
		myParser = Parser.createParser(content, "utf8");
		HtmlPage visitor = new HtmlPage(myParser);
		myParser.visitAllNodesWith(visitor);

		String textInPage = visitor.getTitle();
		System.out.println(textInPage);
		NodeList nodelist;
		nodelist = visitor.getBody();

		System.out.print(nodelist.asString().trim());
	}

	/**
	 * 分别读纯文本和链接.
	 * 
	 * @param result
	 *            网页的内容
	 * @throws Exception
	 */
	public static void readTextAndLinkAndTitle(String result) throws Exception {
		Parser parser;
		NodeList nodelist;
		parser = Parser.createParser(result, "utf8");
		NodeFilter textFilter = new NodeClassFilter(TextNode.class);
		NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
		NodeFilter titleFilter = new NodeClassFilter(TitleTag.class);
		OrFilter lastFilter = new OrFilter();
		lastFilter.setPredicates(new NodeFilter[] { textFilter, linkFilter, titleFilter });
		nodelist = parser.parse(lastFilter);
		Node[] nodes = nodelist.toNodeArray();
		String line = "";

		for (int i = 0; i < nodes.length; i++) {
			Node node = nodes[i];
			if (node instanceof TextNode) {
				TextNode textnode = (TextNode) node;
				line = textnode.getText();
			} else if (node instanceof LinkTag) {
				LinkTag link = (LinkTag) node;
				line = link.getLink();
			} else if (node instanceof TitleTag) {
				TitleTag titlenode = (TitleTag) node;
				line = titlenode.getTitle();
			}

			if (isTrimEmpty(line))
				continue;
			System.out.println(line);
		}
	}

	/**
	 * 去掉左右空格后字符串是否为空
	 */
	public static boolean isTrimEmpty(String astr) {
		if ((null == astr) || (astr.length() == 0)) {
			return true;
		}
		if (isBlank(astr.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 字符串是否为空:null或者长度为0.
	 */
	public static boolean isBlank(String astr) {
		if ((null == astr) || (astr.length() == 0)) {
			return true;
		} else {
			return false;
		}
	}
}