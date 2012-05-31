package com.weiss.sql;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

//file_name varchar(255) not null,
//content longblob,
public class MysqlPic {
	private static final String path = "ali.png";
	private static Connection conn;
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String dbURL = "jdbc:mysql://192.168.0.10:3306/test";
	private static String userID = "root";
	private static String passwd = "root";

	public static void storeImage() throws Exception {
		Class.forName(driverName);
		conn = DriverManager.getConnection(dbURL, userID, passwd);
		PreparedStatement pstmt = conn.prepareStatement("insert into picture_db ( file_name,content) values (?,?)");

		FileInputStream in = new FileInputStream(path);
		pstmt.setBinaryStream(2, in, in.available());
		pstmt.setString(1, path);
		pstmt.executeUpdate();
		pstmt.executeUpdate("COMMIT");
		System.out.println(" 图片 " + path + " 被添加到数据库中！ ");

		pstmt.close();
		conn.close();
	}

	public static void readImage() throws Exception {
		Class.forName(driverName);
		conn = DriverManager.getConnection(dbURL, userID, passwd);
		PreparedStatement pstmt = conn.prepareStatement("select file_name,content from picture_db");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			// File file=new File("db_"+rs.getString(1));
			Blob blob = rs.getBlob(2);
			byte[] bytes = blob.getBytes(1, (int) blob.length());
			Gson gson = new Gson();
			String json = gson.toJson(bytes);
			System.out.println(json);
			// blob.getBinaryStream();
			IOUtils.write(bytes, new FileOutputStream("db_" + rs.getString(1)));
		}
		System.out.println("success read pic from db");
	}

	public static void main(String[] args) throws Exception {
		storeImage();
		readImage();
	}
}
