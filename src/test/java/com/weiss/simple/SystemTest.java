package com.weiss.simple;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

public class SystemTest {
	@Test
	public void testConsole() {
		Console console = System.console();
		if (console != null)
			console.printf("hello,world!!%s", "weiss");
	}
	public static void main(String[] args) {
		new SystemTest().testConsole();
	}
}
