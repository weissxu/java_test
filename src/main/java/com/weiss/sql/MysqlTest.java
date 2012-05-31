package com.weiss.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

import org.apache.commons.dbutils.DbUtils;
import org.junit.Test;

public class MysqlTest {
	private static final String url = "jdbc:mysql://192.168.0.104:3306/test"; // 定义数据库的地址，pubs为所要访问的数据库名称,127.0.0.1:1433是ip地址和端口
	private static final String user = "root"; // 数据库的用户名
	private static final String password = "root"; // 数据库的密码

	private static final String sql = "insert user ( id,name,password) values (%d,'%s','%s')";

	@Test
	public void testConn() throws Exception {
		DbUtils.loadDriver("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, user, password);
		System.out.println(conn);
		DbUtils.close(conn);
	}

	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;

		DbUtils.loadDriver("com.mysql.jdbc.Driver");

		try {
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			conn.setSavepoint();
			stmt = conn.createStatement();
			stmt.executeUpdate(String.format(sql, 3, "weiss", "weiss"));

			Savepoint sv1 = conn.setSavepoint("new User:[3,weiss]");
			try {
				stmt.executeUpdate(String.format(sql, 4, "mike", "mike"));
			} catch (SQLException e) {
				conn.rollback(sv1);
			}

			Savepoint sv2 = conn.setSavepoint("new User:[3,mike]");
			try {
				stmt.executeUpdate(String.format(sql, 3, "weiss", "weiss"));
			} catch (SQLException e) {
				conn.rollback(sv2);
			}
			conn.commit();

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(stmt);
			DbUtils.closeQuietly(conn);
		}

	}
}
