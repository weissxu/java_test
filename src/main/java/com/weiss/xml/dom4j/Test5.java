package com.weiss.xml.dom4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.ElementPath;
import org.dom4j.io.SAXReader;

public class Test5 {

    public static void main(String[] args) {
        SAXReader reader;
        try {
            InputStream is = null;
            is = new FileInputStream("src/student.xml");
            reader = new SAXReader();
            reader.setDefaultHandler(new ElementHandler() {

                
                public void onStart(ElementPath elementPath) {
                    Element e = elementPath.getCurrent();
                    if ("学生名册".equals(e.getName())) {
                        System.out.println("找到根元素节点，开始解析～～～");
                    } else if ("学生".equals(e.getName())) {

                        System.out.printf("解析到学生的学号： %s\n", e.attributeValue("学号"));
                    } 
                    e.detach();// 记得从内存中移去
                }

                
                public void onEnd(ElementPath elementPath) {
                    Element e = elementPath.getCurrent();
                     if ("姓名".equals(e.getName())) {

                        System.out.printf("解析到学生姓名：%s\n", e.getText());
                    } else if ("性别".equals(e.getName())) {

                        System.out.printf("解析到学生性别：%s\n", e.getText());
                    } else if ("年龄".equals(e.getName())) {

                        System.out.printf("解析到学生年龄：%s\n", e.getText());
                    }
                    e.detach();
                }
            });
            System.out.println("begin time: " + System.currentTimeMillis());
            reader.read(is);
            System.out.println("end time: " + System.currentTimeMillis());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}