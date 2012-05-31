package com.weiss.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame implements ActionListener{
	JPanel panel=null;
	JButton bt1=null;
	JButton bt2=null;
	Cat cat=new Cat();
	public MyFrame(){
		panel=new JPanel();
		bt1=new JButton("black");
		bt2=new JButton("red");
		bt1.setActionCommand("black");
		bt2.setActionCommand("red");
		bt1.addActionListener(this);
		bt1.addActionListener(cat);
		bt2.addActionListener(this);
		this.add(bt1, BorderLayout.NORTH);
		panel.setBackground(Color.black);
		this.add(panel);
		this.add(bt2,BorderLayout.SOUTH);
		this.setSize(500, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public static void main(String[] args){
		new MyFrame();
	}
	
	public void actionPerformed(ActionEvent e) {
		if("black".equals(e.getActionCommand())){
			panel.setBackground(Color.black);
			System.out.println("-----black is pressed--------");
		}else if("red".equals(e.getActionCommand())){
			panel.setBackground(Color.red);
			System.out.println("-----red is pressed--------");
		}else{
			System.out.println("-----donot know--------");
		}
	}
}
class Cat implements ActionListener{

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("-----i am the cat ,i am listening and got it--------");
	}
	
}