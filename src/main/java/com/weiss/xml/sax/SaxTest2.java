package com.weiss.xml.sax;

import java.util.Stack;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxTest2 {
	public static void main(String[] args) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();

		SAXParser parser = factory.newSAXParser();
		System.out.println("begin time: " + System.currentTimeMillis());
		parser.parse(SaxTest2.class.getClassLoader().getResourceAsStream("student.xml"), new MyHandler2());
		System.out.println("  end time: " + System.currentTimeMillis());
	}
}

class MyHandler2 extends DefaultHandler {
	private Stack<String> stack = new Stack<String>();

	private String name;

	private String gender;

	private String age;

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		stack.push(qName);

		for (int i = 0; i < attributes.getLength(); i++) {
			String attrName = attributes.getQName(i);
			String attrValue = attributes.getValue(i);

			System.out.println(attrName + "=" + attrValue);
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		String tag = stack.peek();

		if ("姓名".equals(tag)) {
			name = new String(ch, start, length);
		} else if ("性别".equals(tag)) {
			gender = new String(ch, start, length);
		} else if ("年龄".equals(tag)) {
			age = new String(ch, start, length);
		}
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		stack.pop();

		if ("学生".equals(qName)) {
			System.out.println("姓名: " + name);
			System.out.println("性别: " + gender);
			System.out.println("年龄: " + age);

			System.out.println();
		}

	}

	@Override
	public void processingInstruction(String target, String data) throws SAXException {
		System.out.println("target: " + target);
		System.out.println("data: " + data);
	}
}
