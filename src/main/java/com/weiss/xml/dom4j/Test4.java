package com.weiss.xml.dom4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.ElementPath;
import org.dom4j.io.SAXReader;

public class Test4  implements ElementHandler {  
        
        SAXReader reader;  
        public Test4() {  
            // test.xml文件跟类放在同一目录下  
            try {  
                InputStream is=null;
                try {
                    is = new FileInputStream("src/test.xml");
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }   
                reader = new SAXReader();  
                reader.setDefaultHandler(this);  
                reader.read(is);  
            } catch (DocumentException e) {  
                e.printStackTrace();  
            }  
        }  
          
        public void onEnd(ElementPath ep) {  
            Element e = ep.getCurrent(); //获得当前节点  
            if(e.getName().equals("PARAM_TIMESTAMP"))  
                System.out.println("解析到时间:"+e.getText());  
            else if(e.getName().equals("CITYINFO")){  
                System.out.printf("解析到CITYINFO,属性值为：%s,%s,%s\n", e  
                        .attributeValue("City"), e.attributeValue("Name"), e  
                        .attributeValue("No"));  
            }  
            e.detach(); //记得从内存中移去  
        }  
      
          
        public void onStart(ElementPath ep) {  
        }  
          
        public static void main(String[] args){  
            new Test4();  
        }  
      
    }  