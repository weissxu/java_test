package com.weiss.httpcomponet;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class RenRen {
	// The configuration items
	private static String userName = "siwei666@163.com";
	private static String password = "xss19880906";
	private static String redirectURL = "http://blog.renren.com/blog/304317577/449470467";

	// Don't change the following URL
	private static String renRenLoginURL = "http://www.renren.com/PLogin.do";

	// The HttpClient is used in one session
	private HttpResponse response;
	private final DefaultHttpClient httpclient = new DefaultHttpClient();

	private boolean login() {
		HttpPost httpost = new HttpPost(renRenLoginURL);
		// All the parameters post to the web site
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("origURL", redirectURL));
		nvps.add(new BasicNameValuePair("domain", "renren.com"));
		nvps.add(new BasicNameValuePair("isplogin", "true"));
		nvps.add(new BasicNameValuePair("formName", ""));
		nvps.add(new BasicNameValuePair("method", ""));
		nvps.add(new BasicNameValuePair("submit", "登录"));
		nvps.add(new BasicNameValuePair("email", userName));
		nvps.add(new BasicNameValuePair("password", password));
		try {
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			response = httpclient.execute(httpost);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			httpost.abort();
		}
		return true;
	}

	private String getRedirectLocation() {
		Header locationHeader = response.getFirstHeader("Location");
		if (locationHeader == null) {
			return null;
		}
		return locationHeader.getValue();
	}

	private String getText(String redirectLocation) {
		HttpGet httpget = new HttpGet(redirectLocation);
		// Create a response handler
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = "";
		try {
			responseBody = httpclient.execute(httpget, responseHandler);
		} catch (Exception e) {
			e.printStackTrace();
			responseBody = null;
		} finally {
			httpget.abort();
			httpclient.getConnectionManager().shutdown();
		}
		return responseBody;
	}

	public void printText() {
		if (login()) {
			String redirectLocation = getRedirectLocation();
			if (redirectLocation != null) {
				System.out.println(getText(redirectLocation));
			}
		}
	}

	public static void main(String[] args) {
		RenRen renRen = new RenRen();
		renRen.printText();
	}
}
