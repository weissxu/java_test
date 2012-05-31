package com.weiss.csdn;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Main {
	private static final Log logger = LogFactory.getLog(Main.class);
	private static final String filePath = "g:/www.csdn.net.sql";
	private static ExecutorService executor = Executors.newFixedThreadPool(10);
	private static int sizePerTime = 100000;

	public static void main(String[] args) throws IOException {
		logger.info("----------------start--------------------------");
		List<UserInfo> allUsers = new LinkedList<UserInfo>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
		String line;
		while ((line = reader.readLine()) != null) {
			UserInfo info = new UserInfo(line);
			allUsers.add(info);
			if (allUsers.size() >= sizePerTime) {
				logger.info("submit to pool the size is: " + allUsers.size());
				executor.submit(new RunnableWriter(allUsers));
				allUsers = new LinkedList<UserInfo>();
			}

		}
		executor.submit(new RunnableWriter(allUsers));

		logger.info("all users are submited to pool!!");
		executor.shutdown();
		logger.info("-------------------finished---------------------------");
	}

}
