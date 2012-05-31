package com.weiss.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestSqlServer {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;

		String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=studadmin"; // 定义数据库的地址，pubs为所要访问的数据库名称,127.0.0.1:1433是ip地址和端口
		String user = "sa"; // 数据库的用户名
		String password = "root"; // 数据库的密码
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
					.newInstance();
			conn = DriverManager.getConnection(url, user, password); // 建立连接
			stmt = conn.createStatement(); // 创建Statment
			String sql = "select * from teacherInfo"; // 构造所要执行的SQL语句,employee是pubs数据库中的一个表
			ResultSet rs = stmt.executeQuery(sql); // 执行sql语句并返回结果给所定义的结果集
			System.out.println("id----------name");
			while (rs.next()) {
				System.out.println(rs.getString(1) + "----------"
						+ rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
