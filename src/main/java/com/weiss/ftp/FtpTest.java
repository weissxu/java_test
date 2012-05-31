package com.weiss.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.junit.Before;
import org.junit.Test;

public class FtpTest {
	public static final int BINARY_FILE_TYPE = FTP.BINARY_FILE_TYPE;
	public static final int ASCII_FILE_TYPE = FTP.ASCII_FILE_TYPE;
	private static final String server = "localhost";
	private static final int port = 21;
	private static final String user = "admin";
	private static final String password = "admin";

	private FTPClient ftpClient;

	@Before
	public void before() throws SocketException, IOException {
		ftpClient = new FTPClient();
		ftpClient.connect(server, port);
		System.out.println("Connected to " + server + ".");
		System.out.println(ftpClient.getReplyCode());
		ftpClient.login(user, password);
	}

	@Test
	public void testDir() throws Exception {
		FTPFile[] ftpFiles = ftpClient.listFiles("");
		for (FTPFile file : ftpFiles) {
			System.out.println(file.getName());
		}
	}

	@Test
	public void testSend() throws Exception {
		String newName = "Test.java";
		InputStream iStream = new FileInputStream(new File("e:/Test.java"));
		ftpClient.storeFile(newName, iStream);
	}

	@Test
	public void testGet() throws Exception {
		String sourceFileName = "README.txt";
		ftpClient.retrieveFile(sourceFileName, FileUtils.openOutputStream(new File("d:/" + sourceFileName)));
	}

	@Test
	public void testMkdir() throws Exception {
		String pathName = "weiss";
		ftpClient.makeDirectory(pathName);
	}
}
