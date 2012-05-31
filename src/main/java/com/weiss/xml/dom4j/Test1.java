package com.weiss.xml.dom4j;

import java.io.FileOutputStream;
import java.io.FileWriter;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class Test1 {
	public static void main(String[] args) throws Exception {
		// Document document = DocumentHelper.createDocument();
		//
		// Element root = DocumentHelper.createElement("student");
		//
		// document.setRootElement(root);

		Element root = DocumentHelper.createElement("students");
		Document document = DocumentHelper.createDocument(root);
		Element stuElement = root.addElement("student");
		stuElement.addAttribute("id", "1");

		Element nameElement = stuElement.addElement("name");
		Element ageElement = stuElement.addElement("age");

		nameElement.setText("weiss");
		ageElement.setText("21");

		Element stuElement1 = root.addElement("student");
		stuElement1.addAttribute("id", "2");

		Element nameElement1 = stuElement1.addElement("name");
		Element ageElement1 = stuElement1.addElement("age");

		nameElement1.setText("mike");
		ageElement1.setText("22");

		XMLWriter xmlWriter = new XMLWriter();
		xmlWriter.write(document);

		OutputFormat format = new OutputFormat("    ", true);

		XMLWriter xmlWriter2 = new XMLWriter(new FileOutputStream("student2.xml"), format);
		xmlWriter2.write(document);
		xmlWriter2.close();

		XMLWriter xmlWriter3 = new XMLWriter(new FileWriter("student3.xml"), format);

		xmlWriter3.write(document);
		xmlWriter3.close();

	}
}
