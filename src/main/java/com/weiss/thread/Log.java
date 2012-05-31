package com.weiss.thread;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Log {
	private static PrintWriter writer = null;
	static {
		try {
			writer = new PrintWriter(new FileWriter("log.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void print(String str) {
		writer.write(str+"\r\n");
		writer.flush();
	}

	public static void close() {
		if (null != writer) {
			writer.close();
		}
	}
}
