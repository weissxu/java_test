package com.weiss.redis;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class RedisDownload {

	private static final Logger logger = Logger.getLogger(RedisDownload.class);

	public static class Task implements Callable<Task> {
		private final HttpClient client;
		private final String filename;
		private final String href;
		private Long startTime;

		public Task(HttpClient client, String href) {
			this.client = client;
			this.href = href;
			this.filename = href.substring(href.lastIndexOf("/") + 1);
		}

		@Override
		public Task call() throws Exception {
			startTime = System.currentTimeMillis();
			HttpUriRequest httpget = new HttpGet(href);

			HttpResponse response = client.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				Document document = Jsoup.parse(EntityUtils.toString(entity));
				Elements elements = document.getElementsByClass("line");
				StringBuilder builder = new StringBuilder();
				for (Element ele : elements) {
					builder.append(ele.text() + System.getProperty("line.separator"));
				}
				FileUtils.write(new File(folder + filename), builder.toString());
			}

			return this;
		}

		public String getFilename() {
			return filename;
		}

		public Long getStartTime() {
			return startTime;
		}

	}

	// <div class="line" id="LC1" style="background-color: transparent; "><span
	// class="kn">package</span> <span class="n">redis</span><span
	// class="o">.</span><span class="na">clients</span><span
	// class="o">.</span><span class="na">jedis</span><span
	// class="o">.</span><span class="na">tests</span><span
	// class="o">;</span></div>
	public static final String url = "https://github.com/xetorthio/jedis/tree/master/src/test/java/redis/clients/jedis/tests";
	public static final String url1 = "https://github.com/xetorthio/jedis/tree/72ca4943627bced9fafc316d0487e3f939a02009/src/test/java/redis/clients/jedis/tests/commands";
	public static final String url2 = "https://github.com/xetorthio/jedis/tree/72ca4943627bced9fafc316d0487e3f939a02009/src/test/java/redis/clients/jedis/tests/benchmark";
	public static final String folder = "/Users/siwei/redis-test/JAVASRC/";
	public static final String DOMAIN = "http://github.com";

	public static final List<String> urls = Arrays.asList(url, url1);
	public static final String ONE_URL = "https://github.com/xetorthio/jedis/blob/72ca4943627bced9fafc316d0487e3f939a02009/src/test/java/redis/clients/jedis/tests/ConnectionTest.java";
	public static int i = 1;

	@Test
	public void testLine() {
		System.out.println("hello" + System.getProperty("line.separator") + "world");
	}

	public static void main(String[] args) throws Exception {
		logger.info("start----------------");
		long start = System.currentTimeMillis();

		ExecutorService pool = new ThreadPoolExecutor(5, 5, 50, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>()) {
			@SuppressWarnings("unchecked")
			@Override
			protected void afterExecute(Runnable r, Throwable t) {
				super.afterExecute(r, t);
				try {
					Task task = ((FutureTask<Task>) r).get();
					logger.info(task.getFilename() + "----- cost time:"
							+ (System.currentTimeMillis() - task.getStartTime()));
				} catch (InterruptedException e1) {
					logger.error(e1);
				} catch (Exception e1) {
					logger.error(e1);
				}
			}
		};
		// BasicPooledConnAdapter
		DefaultHttpClient client = new DefaultHttpClient(new ThreadSafeClientConnManager());
		client.setReuseStrategy(new DefaultConnectionReuseStrategy());
		client.addRequestInterceptor(new HttpRequestInterceptor() {
			@Override
			public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
				logger.info(context.getAttribute(ExecutionContext.HTTP_CONNECTION));
				System.out.println("-------------------------");
			}
		});

		List<String> hrefs = getHref();
		for (String href : hrefs) {
			pool.submit(new Task(client, href));
		}
		pool.shutdown();
		while (!pool.isTerminated()) {
			Thread.sleep(50);
		}
		System.out.println("total time: " + (System.currentTimeMillis() - start));
	}

	private static List<String> getHref() throws IOException {
		List<String> hrefs = new ArrayList<String>();
		for (String url : urls) {
			Document document = Jsoup.connect(url).get();
			Elements elements = document.getElementsByClass("js-rewrite-sha");
			for (Element ele : elements) {
				// System.out.println(ele.text());
				String attr = ele.attr("href");
				if (attr.contains(".java")) {
					System.out.println(attr);
					hrefs.add(DOMAIN + attr);
				}
			}
		}
		return hrefs;
	}

	@Test
	public void testDownLoadOne() throws ClientProtocolException, IOException {
		HttpClient client = new DefaultHttpClient(new ThreadSafeClientConnManager());

		HttpUriRequest httpget = new HttpGet(ONE_URL);
		HttpResponse response = client.execute(httpget);
		HttpEntity entity = response.getEntity();
		// System.out.println(EntityUtils.toString(entity));
		if (entity != null) {
			Document document = Jsoup.parse(EntityUtils.toString(entity));
			Elements elements = document.getElementsByClass("line");
			StringBuilder builder = new StringBuilder();
			for (Element ele : elements) {
				builder.append(ele.text() + System.getProperty("line.separator"));
			}
			System.out.println(builder.toString());

			System.out.println("----------------");
		}
	}

	@Test
	public void testDownload() throws IOException {
		Document document = Jsoup.connect(url).get();
		// System.out.println(document.text());
		Elements elements = document.getElementsByClass("js-rewrite-sha");
		for (Element ele : elements) {
			System.out.println(ele.text());
			String attr = ele.attr("href");
			if (attr.contains("tests"))
				System.out.println(attr);
			System.out.println("----------------");
		}

	}
}
