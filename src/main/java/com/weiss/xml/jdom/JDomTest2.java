package com.weiss.xml.jdom;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class JDomTest2
{
	public static void main(String[] args) throws Exception
	{
		SAXBuilder builder = new SAXBuilder();
		
		Document doc = builder.build(new File("jdom.xml"));
		
		Element element = doc.getRootElement();
		
		System.out.println(element.getName());
		
		Element hello = element.getChild("hello");
		
		System.out.println(hello.getText());
		
		List list = hello.getAttributes();
		
		for(int i = 0 ;i < list.size(); i++)
		{
			Attribute attr = (Attribute)list.get(i);
			
			String attrName = attr.getName();
			String attrValue = attr.getValue();
			
			System.out.println(attrName + "=" + attrValue);
		}
		
		hello.removeChild("world");
		
		XMLOutputter out = new XMLOutputter(Format.getPrettyFormat().setIndent("    "));
		
		
		out.output(doc, new FileOutputStream("jdom2.xml"));		
		
	}
}
