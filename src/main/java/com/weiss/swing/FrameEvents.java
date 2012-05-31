package com.weiss.swing;

import javax.swing.JFrame;

public class FrameEvents extends JFrame{
	MyPanel panel=null;
	public FrameEvents(){
		panel=new MyPanel();
		this.setSize(500,400);
		this.add(panel);
		this.addMouseListener(panel);
		this.addMouseMotionListener(panel);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new FrameEvents();
	}

}
