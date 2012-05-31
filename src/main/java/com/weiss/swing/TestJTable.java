package com.weiss.swing;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TestJTable extends JFrame{

	private JTable table=null;
	private JScrollPane jsp=null;
	public TestJTable(){
		Vector<String> rowData=new Vector<String>();
		rowData.add("s001");
		rowData.add("weiss");
		rowData.add("23");
		rowData.add("male");
		rowData.add("computer");
		rowData.add("hebei");
		Vector<Vector<String>> rowDatas=new Vector<Vector<String>>();
		rowDatas.add(rowData);
		
		Vector<String> columnNames=new Vector<String>();
		columnNames.add("id");
		columnNames.add("name");
		columnNames.add("age");
		columnNames.add("sex");
		columnNames.add("dept");
		columnNames.add("addr");
		table=new JTable(rowDatas,columnNames);
		jsp=new JScrollPane(table);
		this.add(jsp);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setSize(500,400);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestJTable();
	}

}
