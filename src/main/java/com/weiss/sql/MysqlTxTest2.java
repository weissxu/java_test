package com.weiss.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbutils.DbUtils;

public class MysqlTxTest2 {
	private static int count;
	private static final String url = "jdbc:mysql://127.0.0.1:3306/test"; // 定义数据库的地址，pubs为所要访问的数据库名称,127.0.0.1:1433是ip地址和端口
	private static final String user = "root"; // 数据库的用户名
	private static final String password = "root"; // 数据库的密码

	private static final String sql = "select * from user";
	private static final String insertSql = "insert user (id,name ,password) values (12,'gaga','gaga')";

	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		DbUtils.loadDriver("com.mysql.jdbc.Driver");

		try {
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			stmt = conn.createStatement();
			stmt.executeUpdate(insertSql);
//			rs = stmt.executeQuery(sql);
//			print(rs);
//
//			rs = stmt.executeQuery(sql);
//			print(rs);
//
//			rs = stmt.executeQuery(sql);
//			print(rs);
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

	private static void print(ResultSet rs) throws SQLException {
		System.out.println((++count)+" time: ");
		while(rs.next()){
			System.out.println("                id: "+rs.getInt("id"));
		}
	}
}
