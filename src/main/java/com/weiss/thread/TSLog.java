package com.weiss.thread;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TSLog {
	private String name;
	private PrintWriter writer = null;

	public TSLog(String name) {
		super();
		this.name = name;
		try {
			writer = new PrintWriter(new FileWriter("log_" + name + ".txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void print(String str) {
		writer.write(str + "\r\n");
		writer.flush();
	}

	public void close() {
		if (null != writer) {
			writer.close();
		}
	}

}
