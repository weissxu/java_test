package com.weiss.csdn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mysql.jdbc.Statement;

public class RunnableWriter implements Runnable {
	private static Log logger = LogFactory.getLog(RunnableWriter.class);
	public static final String url = "jdbc:mysql://127.0.0.1:3306/test?rewriteBatchedStatements=true"; // 定义数据库的地址，pubs为所要访问的数据库名称,127.0.0.1:1433是ip地址和端口
	public static final String user = "root"; // 数据库的用户名
	public static final String password = "root"; // 数据库的密码

	public static final String sql = "insert csdn (name,password,email,md5_password) values (?,?,?,md5(?))";
	private List<UserInfo> users;
	private int count;

	public RunnableWriter(List<UserInfo> users) {
		count = 0;
		this.users = users;
	}

	@Override
	public void run() {
		Connection conn = null;
		Statement stmt = null;

		DbUtils.loadDriver("com.mysql.jdbc.Driver");

		try {
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			for (UserInfo info : users) {
				pstmt.setString(1, info.getName());
				pstmt.setString(2, info.getPassword());
				pstmt.setString(3, info.getEmail());
				pstmt.setString(4, info.getPassword());

				count++;
				pstmt.addBatch();
				if (count >= 2000) {
					pstmt.executeBatch();
					count = 0;
				}
			}

			pstmt.executeBatch();
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
		logger.info("finished ,the concrent size is: " + users.size());
	}

	public static void main(String[] args) {
		List<UserInfo> users = new LinkedList<UserInfo>();
		users.add(new UserInfo("zdg # 12344321 # zdg@csdn.net"));
		users.add(new UserInfo("LaoZheng # 670203313747 # chengming_zheng@163.com"));
		new RunnableWriter(users).run();
	}
}
