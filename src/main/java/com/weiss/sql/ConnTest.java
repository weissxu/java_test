package com.weiss.sql;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnTest {
	private static final String url = "jdbc:mysql://127.0.0.1:3306/test"; // 定义数据库的地址，pubs为所要访问的数据库名称,127.0.0.1:1433是ip地址和端口
	private static final String user = "root"; // 数据库的用户名
	private static final String password = "root"; // 数据库的密码

	private static final String sql = "insert user ( id,name,password) values (%d,'%s','%s')";

	public static void main(String[] args) throws Exception {

		Connection conn = null;
		System.out.println("hello");
		conn=DriverManager.getConnection(url,user,password);
		System.out.println(conn);
		conn.close();
	}
}
