package com.weiss.httpcomponet;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class SinaLogin3 {
	public static String login(String email, String passwd) throws HttpException, IOException {
		PostMethod post = new PostMethod("http://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.3.11)");
		post.addParameter("service", "miniblog");
		post.addParameter("client", "ssologin.js(v1.3.11)");
		post.addParameter("entry", "miniblog");
		post.addParameter("encoding", "utf-8");
		post.addParameter("gateway", "1");
		post.addParameter("savestate", "7");
		post.addParameter("from", "");
		post.addParameter("useticket", "0");
		post.addParameter("username", email);
		post.addParameter("password", passwd);
		post.addParameter("url",
				"http://t.sina.com.cn/ajaxlogin.php?framelogin=1&callback=parent.sinaSSOController.feedBackUrlCallBack");
		post.addParameter("returntype", "META");
		HttpClient client = new HttpClient();
		client.executeMethod(post);
		GetMethod get = new GetMethod("http://t.sina.com.cn/pub/tags");
		client.executeMethod(get);
		System.out.println(new String(get.getResponseBody()));
		return new String(get.getResponseBody());
	}

	public static void main(String[] args) throws HttpException, IOException {
		login("xusiwei@mitao.cn", "qwer1234");
	}
}