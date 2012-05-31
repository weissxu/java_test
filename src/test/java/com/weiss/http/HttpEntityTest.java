package com.weiss.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.ContentProducer;
import org.apache.http.entity.EntityTemplate;
import org.apache.http.entity.FileEntity;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpEntityTest {
	@Test
	public void testConsume1() throws IOException{
		FileEntity entity=new FileEntity(new File("e:/Test.java"), "text/html");
		commonConsume(entity);
	}
	@Test
	public void testConsume2() throws IOException{
		BasicHttpEntity entity=new BasicHttpEntity();
		entity.setContent(new FileInputStream("e:/Test.java"));
		commonConsume(entity);
	}
	
	@Test
	public void testProduce() throws IOException{
		ContentProducer myContentProducer = new ContentProducer() {

		    public void writeTo(OutputStream out) throws IOException {
		      out.write("ContentProducer rocks! ".getBytes());
		      out.write(("Time requested: " + new Date()).getBytes());
		    }
		};

		HttpEntity myEntity = new EntityTemplate(myContentProducer);
		myEntity.writeTo(System.out);
	}
	
	private void commonConsume(HttpEntity entity) throws IOException {
		InputStream is = entity.getContent();
		EntityUtils.consume(entity);
		System.out.println(is);
		if(is instanceof FileInputStream){
			System.out.println("open: "+((FileInputStream)is).getChannel().isOpen());
		}
	}
}
