package com.weiss.ftp;

import java.io.File;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.ssl.SslConfigurationFactory;
import org.apache.ftpserver.usermanager.Md5PasswordEncryptor;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;

public class MyFtpServer {
	private static FtpServerFactory serverFactory = new FtpServerFactory();
	private static ListenerFactory listenerFactory = new ListenerFactory();

	public static void main(String[] args) throws Exception {
		FtpServer server = constructSSLServer();
		// FtpServer server = constructSimpleServer();
		server.start();
	}

	private static FtpServer constructSSLServer() {

		// define SSL configuration
		SslConfigurationFactory ssl = new SslConfigurationFactory();
		ssl.setKeystoreFile(new File("src/main/resource/ftpserver.jks"));
		ssl.setKeystorePassword("password");

		// set the SSL configuration for the listener
		listenerFactory.setSslConfiguration(ssl.createSslConfiguration());
		listenerFactory.setImplicitSsl(false);

		// replace the default listener
		serverFactory.addListener("default", listenerFactory.createListener());

		PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
		userManagerFactory.setFile(new File("src/main/resource/users.properties"));
		userManagerFactory.setPasswordEncryptor(new Md5PasswordEncryptor());

		serverFactory.setUserManager(userManagerFactory.createUserManager());

		return serverFactory.createServer();
	}

	private static FtpServer constructSimpleServer() {

		listenerFactory.setPort(21);

		serverFactory.addListener("default", listenerFactory.createListener());

		FtpServer server = serverFactory.createServer();
		return server;
	}

}
