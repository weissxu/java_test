package com.weiss.dbutils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.log4j.Logger;

import com.mysql.jdbc.JDBC4Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DaoImpl implements Dao {

	private MysqlDataSource dataSource = new MysqlDataSource();
	private QueryRunner qryRun = null;
	private Logger logger = Logger.getLogger(getClass());
	private java.sql.DatabaseMetaData dbmb;

	public DaoImpl() {
		dataSource.setUrl("jdbc:mysql://192.168.1.102:3306/test");
		dataSource.setUser("root");
		dataSource.setPassword("root");
		qryRun = new QueryRunner(dataSource);
		dbmb = getDatabaseMetaData();
	}

	private java.sql.DatabaseMetaData getDatabaseMetaData() {
		Properties info = new Properties();
		info.setProperty("user", "root");
		info.setProperty("password", "root");
		java.sql.DatabaseMetaData metaData = null;
		try {
			metaData = new JDBC4Connection("192.168.1.102", 3306, info, "test", null).getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return metaData;
	}

	public <T extends Object> List<T> findAll(Class<T> clazz) {
		ResultSetHandler<List<T>> rsh = new BeanListHandler<T>(clazz);
		List<T> result = null;
		try {
			result = qryRun.query("select * from " + clazz.getSimpleName(), rsh);
			logger.debug("SQL: select * from " + clazz.getSimpleName());
		} catch (SQLException e) {
			logger.error("Can not this query table " + clazz.getSimpleName(), e);
		}
		return result;
	}

	public <T> T findById(Class<T> clazz, int id) {
		ResultSetHandler<T> rsh = new BeanHandler<T>(clazz);
		T result = null;
		try {
			ResultSet rs = dbmb.getPrimaryKeys(null, null, clazz.getSimpleName());
			String primary_key = null;
			while (rs.next()) {
				primary_key = rs.getString("Column_name");
			}
			if (!"".equals(primary_key) || null != primary_key) {
				result = qryRun.query("select * from " + clazz.getSimpleName() + " where " + primary_key + "=?", rsh, new Object[] { id });
				logger.debug("SQL: select * from " + clazz.getSimpleName() + " where " + primary_key + "=" + id);
			} else {
				logger.error("This table " + clazz.getSimpleName() + " has not primary key");
				throw new SQLException("This table has not primary key");
			}
		} catch (SQLException e) {
			logger.error("Can not this query table " + clazz.getSimpleName(), e);
		}
		return result;
	}

	public List<Map<String, Object>> executeQuery(String sql, Object... args) {
		MapListHandler rsh = new MapListHandler();
		List<Map<String, Object>> result = null;
		try {
			result = qryRun.query(sql, rsh, args);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public long insert(String sql, Object... args) {
		return executeUpdate(sql, args);
	}

	public long update(String sql, Object... args) {
		return executeUpdate(sql, args);
	}

	public void delete(String sql, Object... args) {
		executeUpdate(sql, args);
	}

	public long executeUpdate(String sql, Object... args) {
		long id = 0;
		try {
			id = qryRun.update(sql, args);
		} catch (SQLException e) {
			logger.error("This table can not changed !", e);
		}
		return id;
	}

	public int[] batchUpdate(String sql, Object[][] objs) {
		int[] ids = null;
		try {
			ids = qryRun.batch(sql, objs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ids;
	}
}