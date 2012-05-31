package com.weiss.excel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/*
 * 该程序需要odbc驱动！！
 */
public class ExcelReader {
	public static String readExcel(String odbcEntry,String sheetNum){
		StringBuffer ret=new StringBuffer();
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection conn=DriverManager.getConnection("jdbc:odbc:"+odbcEntry);
			java.sql.Statement stmt= conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from [Sheet"+sheetNum+"$]");
			ResultSetMetaData rsmeta=rs.getMetaData();
			int numOfCols=rsmeta.getColumnCount();
			while(rs.next()){
				for(int i=0;i<numOfCols;i++){
//					if(i)ret.append(",");
					ret.append(rs.getString(i));
				}
				ret.append("\n");
			}
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(readExcel("myExcelFile","1"));
	}

}
