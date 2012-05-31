package com.weiss.xml.dom4j;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Test2 {
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws Exception {
		SAXReader saxReader = new SAXReader();
		System.out.println("begin time: " + System.currentTimeMillis());
		Document doc = saxReader.read(Test2.class.getClassLoader().getResource("student.xml"));
		System.out.println("end time: " + System.currentTimeMillis());
		Element root = doc.getRootElement();

		System.out.println("root element: " + root.getName());

		List childList = root.elements();

		System.out.println(childList.size());

		List childList2 = root.elements("学生");

		System.out.println(childList2.size());

		Element first = root.element("学生");

		System.out.println(first.attributeValue("学号"));

		for (Iterator iter = root.elementIterator(); iter.hasNext();) {
			Element e = (Element) iter.next();

			System.out.println(e.attributeValue("学号"));
		}

		System.out.println("---------------------------");

		// DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// DocumentBuilder db = dbf.newDocumentBuilder();
		// System.out.println("begin time: " + System.currentTimeMillis());
		// org.w3c.dom.Document document = db.parse(new File("student2.xml"));
		// System.out.println("  end time: " + System.currentTimeMillis());
		// DOMReader domReader = new DOMReader();
		//
		// Document d = domReader.read(document);
		//
		// Element rootElement = d.getRootElement();
		//
		// System.out.println(rootElement.getName());
	}
}
