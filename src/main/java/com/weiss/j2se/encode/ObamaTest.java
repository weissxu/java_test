package com.weiss.j2se.encode;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class ObamaTest {
	@Test
	public void testObamaWithBase64_length() throws Exception {
		Obama64.BLUFF = false;

		String str = "大象、大象";

		byte[] content = str.getBytes("UTF-16BE");

		for (int i = 0; i < 3; i++) {
			System.out.println("\"Obama64:\"");
			byte[] en = Obama64.encode(content);
			System.out.println(new String(en));
			System.out.println(new String(Obama64.decode(en), "UTF-16BE"));

			System.out.println("\"Base64:\"");
			en = Base64.encodeBase64(content);
			System.out.println(new String(en));
			System.out.println(new String(Base64.decodeBase64(en), "UTF-16BE"));
			System.out.println("-----------------------------------------------");
		}
	}

	@Test
	public void testObamaWithBase64_time() {
		byte[] content = "大象、大象，你的鼻子为什么那么长？~~~~\t!@#$#$%^%^$%&#123".getBytes();
		long start = System.currentTimeMillis();

		for (int i = 0; i < 1000000; i++) {
			byte[] en = Obama64.encode(content);
			byte[] de = Obama64.decode(en);
		}

		System.out.println(System.currentTimeMillis() - start);

		start = System.currentTimeMillis();

		for (int i = 0; i < 1000000; i++) {
			byte[] en = Base64.encodeBase64(content);
			byte[] de = Base64.decodeBase64(en);
		}

		System.out.println(System.currentTimeMillis() - start);

	}
	
	@Test
	public void testAscII(){
		String content = "大象、大象";
		byte[] asciiBytes = Obama64.encodeAsciiBytes(content);
		System.out.println(new String(asciiBytes));
		System.out.println(Obama64.decodeAsciiString(asciiBytes));
		
	}
}
