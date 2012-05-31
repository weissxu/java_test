package com.weiss.j2se.net;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class WeiboTest {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket s = new Socket("plaza.weibo.com", 80);
		InputStream in = s.getInputStream();
		OutputStream out = s.getOutputStream();
		out.write(FileUtils.readFileToByteArray(new File("src/main/resource/weibo.get")));

		// String result = IOUtils.toString(in);
		byte[] byteArray = IOUtils.toByteArray(in);
		System.out.println(new String(byteArray, "utf-8"));
		in.close();
		out.close();
		s.close();
	}
}
