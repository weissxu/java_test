package com.weiss.tagsoup;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class SoupTest {
	private static final String RELATIVEPATH = "src/main/resource/";
	public static final String FILENAME = "html-sample.txt";

	@Test
	public void test1() throws IOException, SAXException {
		StringReader xmlReader = new StringReader("");
		// InputStream in = this.getClass().getResourceAsStream(FILENAME);
		FileReader reader = new FileReader(new File(RELATIVEPATH + FILENAME));
		InputSource src = new InputSource(reader);// 构建InputSource实例
		// Parser parser = new Parser();// 实例化Parse
		// XMLWriter writer = new XMLWriter();// 实例化XMLWriter，即SAX内容处理器
		// parser.setContentHandler(writer);// 设置内容处理器
		// parser.parse(src);// 解析
		// Scanner scan = new PYXScanner();
		// scan.scan(xmlReader, parser);// 通过xmlReader读取解析后的结果
		// char[] buff = new char[1024];
		// while (xmlReader.read(buff) != -1) {
		// System.out.println(new String(buff));// 打印解析后的结构良好的HTML文档
		// }
		// // IOUtils.copy(xmlReader, new FileWriter(RELATIVEPATH +
		// // TARGETFILENAME));
		// IOUtils.closeQuietly(xmlReader);
		// IOUtils.closeQuietly(reader);
	}
}
