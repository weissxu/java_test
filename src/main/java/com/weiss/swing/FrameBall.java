package com.weiss.swing;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class FrameBall extends JFrame{
	MyPanel panel=null;
	public FrameBall(){
		panel=new MyPanel();
		this.addKeyListener(panel);
		this.add(panel);
		this.setSize(250, 250);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}


	public static void main(String[] args) {
		new FrameBall();
	}

}
