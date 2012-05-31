package com.weiss.xml.jdom;

import java.io.FileWriter;

import org.jdom.Attribute;
import org.jdom.Comment;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class JDomTest1
{
	public static void main(String[] args) throws Exception
	{
		Document document = new Document();

		Element root = new Element("root");

		document.addContent(root);

		Comment comment = new Comment("This is my comments");

		root.addContent(comment);

		Element e = new Element("hello");

		e.setAttribute("sohu", "www.sohu.com");

		root.addContent(e);

		Element e2 = new Element("world");

		Attribute attr = new Attribute("test", "hehe");

		e2.setAttribute(attr);

		e.addContent(e2);

		e2.addContent(new Element("aaa").setAttribute("a", "b")
				.setAttribute("x", "y").setAttribute("gg", "hh").setText("text content"));

		
		Format format = Format.getPrettyFormat();
		
		format.setIndent("    ");
//		format.setEncoding("gbk");
		
		XMLOutputter out = new XMLOutputter(format);

		out.output(document, new FileWriter("jdom.xml"));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}
}
