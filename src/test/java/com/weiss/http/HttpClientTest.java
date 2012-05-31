package com.weiss.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.NTCredentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.junit.Test;

public class HttpClientTest {
	@Test
	public void testParam() throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();

		httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_0);
		// Default to HTTP 1.0
		httpclient.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8");

		HttpGet httpget = new HttpGet("http://www.google.com/");
		httpget.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		// Use HTTP 1.1 for this request only
		httpget.getParams().setParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, Boolean.FALSE);

		httpclient.addRequestInterceptor(new HttpRequestInterceptor() {
			public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
				System.out.println(request.getParams().getParameter(CoreProtocolPNames.PROTOCOL_VERSION));
				System.out.println(request.getParams().getParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET));
				System.out.println(request.getParams().getParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE));
				System.out.println(request.getParams().getParameter(CoreProtocolPNames.STRICT_TRANSFER_ENCODING));
			}
		});
		httpclient.execute(httpget);
	}

	// @Test
	// public void testTrust(){
	// TrustManager easyTrustManager = new X509TrustManager() {
	// public void checkClientTrusted(X509Certificate[] chain,
	// String authType) throws CertificateException {
	// // 哦，这很简单！
	// }
	// public void checkServerTrusted(X509Certificate[] chain,
	// String authType) throws CertificateException {
	// //哦，这很简单！
	// }
	// @Override
	// public X509Certificate[] getAcceptedIssuers() {
	// return null;
	// }
	// };
	// SSLContext sslcontext = SSLContext.getInstance("TLS");
	// sslcontext.init(null, new TrustManager[] { easyTrustManager }, null);
	// SSLSocketFactory sf = new SSLSocketFactory(sslcontext);
	// SSLSocket socket = (SSLSocket) sf.createSocket();
	// socket.setEnabledCipherSuites(new String[] { "SSL_RSA_WITH_RC4_128_MD5"
	// });
	// HttpParams params = new BasicHttpParams();
	// params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 1000L);
	// sf.connectSocket(socket, "locahost", 443, null, -1, params);
	// }

	@Test
	public void testCredentials() {
		NTCredentials creds = new NTCredentials("user", "pwd", "workstation", "domain");
		System.out.println(creds.getUserPrincipal().getName());
		System.out.println(creds.getPassword());
	}

	@Test
	public void testAuth1() {
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope("somehost", AuthScope.ANY_PORT), 
				new UsernamePasswordCredentials("u1", "p1"));
		credsProvider.setCredentials(new AuthScope("somehost", 8080), 
				new UsernamePasswordCredentials("u2", "p2"));
		credsProvider.setCredentials(new AuthScope("otherhost", 8080, AuthScope.ANY_REALM, "ntlm"), 
				new UsernamePasswordCredentials("u3","p3"));
		System.out.println(credsProvider.getCredentials(new AuthScope("somehost", 80, "realm", "basic")));
		System.out.println(credsProvider.getCredentials(new AuthScope("somehost", 8080, "realm", "basic")));
		System.out.println(credsProvider.getCredentials(new AuthScope("otherhost", 8080, "realm", "basic")));
		System.out.println(credsProvider.getCredentials(new AuthScope("otherhost", 8080, null, "ntlm")));
	}
	
	@Test
	public void testAuth2() throws ClientProtocolException, IOException{
		HttpClient httpclient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		HttpGet httpget = new HttpGet("http://www.google.com/");
		httpclient.execute(httpget, localContext);
		AuthState proxyAuthState = (AuthState) localContext.getAttribute(ClientContext.PROXY_AUTH_STATE);
		System.out.println("Proxy auth scope: " + proxyAuthState.getAuthScope());
		System.out.println("Proxy auth scheme: " + proxyAuthState.getAuthScheme());
		System.out.println("Proxy auth credentials: " + proxyAuthState.getCredentials());
		AuthState targetAuthState = (AuthState) localContext.getAttribute(ClientContext.TARGET_AUTH_STATE);
		System.out.println("Target auth scope: " + targetAuthState.getAuthScope());
		System.out.println("Target auth scheme: " + targetAuthState.getAuthScheme());
		System.out.println("Target auth credentials: " + targetAuthState.getCredentials());
	}
	
	@Test
	public void testService() throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		HttpGet httpget = new HttpGet("http://localhost:8080/");
		httpclient.execute(httpget, localContext);
		HttpHost target = (HttpHost) localContext.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
		HttpUriRequest req = (HttpUriRequest) localContext.getAttribute(ExecutionContext.HTTP_REQUEST);
		System.out.println("Target host: " + target);
		System.out.println("Final request URI: " + req.getURI());
		System.out.println("Final request method: " + req.getMethod());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testUserToken() throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		HttpGet httpget = new HttpGet("http://localhost:8080/");
		HttpResponse response = httpclient.execute(httpget, localContext);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			entity.consumeContent();
		}
		Object userToken = localContext.getAttribute(ClientContext.USER_TOKEN);
		System.out.println(userToken);
	}
}
