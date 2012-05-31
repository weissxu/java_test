package com.weiss.singletonAndmutiton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.weiss.util.DBUtil;

public class KeyInfo {
	private int keyMax = 0;
	private int keyMin = 0;
	private int keyNext = 1;
	private String name;
	private int poolSize = 20;

	public int getKeyNext() {
		if (keyNext > keyMax) {
			// update and select
			String sql = "update keygenerator set id=id+ " + poolSize;
			Connection conn = DBUtil.getInstance().getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = conn.prepareStatement("select * from keygenerator");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					keyMin = rs.getInt("id") + 1;
					keyNext = keyMin;
					keyMax = keyMin + poolSize;
				}
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				System.out.println(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtil.close(rs);
				DBUtil.close(pstmt);
				DBUtil.close(conn);
			}

		}
		return keyNext++;
	}

	public int getKeyMax() {
		return keyMax;
	}

	public int getKeyMin() {
		return keyMin;
	}

	public KeyInfo(String name) {
		super();
		this.name = name;
	}
}
