package com.weiss.j2se;

import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.ParsingDetector;
import info.monitorenter.cpdetector.io.UnicodeDetector;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import org.junit.Test;

public class CharsetTest {

	private static final String HELLO = "hello,world";

	@Test
	public void testEncode() throws CharacterCodingException {
		ByteBuffer encode = encode();
		System.out.println(encode);
	}

	private ByteBuffer encode() throws CharacterCodingException {
		CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();
		ByteBuffer encode = encoder.encode(CharBuffer.wrap(HELLO.toCharArray()));
		return encode;
	}

	@Test
	public void testDecode() throws IOException {
		ByteBuffer buffer = encode();
		CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
		String str = decoder.decode(buffer).toString();
		System.out.println(str);

	}

	@Test
	public void testUrl() throws MalformedURLException {
		String charset = CharsetTest.getReomoteURLFileEncode(new URL("http://www.baidu.com"));
		System.out.println(charset);
	}

	@Test
	public void testFile() {
		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
		detector.add(new ParsingDetector(true));
		detector.add(JChardetFacade.getInstance());
		// ASCIIDetector用于ASCII编码测定
		detector.add(ASCIIDetector.getInstance());
		// UnicodeDetector用于Unicode家族编码的测定
		detector.add(UnicodeDetector.getInstance());
		java.nio.charset.Charset charset = null;
		File f = new File("pageContent.txt");
		try {
			charset = detector.detectCodepage(new BufferedInputStream(new FileInputStream(f)), 100);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (charset != null) {
			System.out.println(f.getName() + "编码是：" + charset.name());
		} else {
			System.out.println(f.getName() + "未知");
		}
	}

	public static String getReomoteURLFileEncode(URL url) {
		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
		detector.add(new ParsingDetector(false));
		detector.add(JChardetFacade.getInstance());
		detector.add(ASCIIDetector.getInstance());
		detector.add(UnicodeDetector.getInstance());
		java.nio.charset.Charset charset = null;
		try {
			System.out.println(url);
			charset = detector.detectCodepage(url);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (charset != null) {
			return charset.name();
		} else {
			return "utf-8";
		}
	}

	/**
	 * 获得文件流的编码格式
	 */
	public static String getInputStreamEncode(InputStream is) {
		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
		detector.add(new ParsingDetector(false));
		detector.add(JChardetFacade.getInstance());
		detector.add(ASCIIDetector.getInstance());
		detector.add(UnicodeDetector.getInstance());
		java.nio.charset.Charset charset = null;
		try {
			charset = detector.detectCodepage(is, 0);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (charset != null) {
			return charset.name();
		} else {
			return "utf-8";
		}
	}

	/**
	 * 获得本地文件的编码格式
	 */
	public static String getLocalteFileEncode(String filePath) {
		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
		detector.add(new ParsingDetector(false));
		detector.add(JChardetFacade.getInstance());
		detector.add(ASCIIDetector.getInstance());
		detector.add(UnicodeDetector.getInstance());
		java.nio.charset.Charset charset = null;
		File file = new File(filePath);
		try {
			charset = detector.detectCodepage(file.toURI().toURL());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (charset != null) {
			return charset.name();
		} else {
			return "utf-8";
		}
	}

	/**
	 * 获得字符串的编码格式
	 */
	public static String getStringEncode(String str) {
		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
		detector.add(new ParsingDetector(false));
		detector.add(JChardetFacade.getInstance());
		detector.add(ASCIIDetector.getInstance());
		detector.add(UnicodeDetector.getInstance());
		java.nio.charset.Charset charset = null;
		InputStream myIn = new ByteArrayInputStream(str.getBytes());
		try {
			charset = detector.detectCodepage(myIn, 3);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (charset != null) {
			return charset.name();
		} else {
			return "utf-8";
		}

	}
}
