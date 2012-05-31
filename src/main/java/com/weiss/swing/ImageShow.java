package com.weiss.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageShow extends JFrame{
	MyPanel1 panel=null;
	public ImageShow() {
		panel=new MyPanel1();
		this.add(panel);
		this.setSize(500, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new ImageShow();
	}


}
@SuppressWarnings("serial")
class MyPanel1 extends JPanel{
	Image image=null;
	MyPanel1(){
		image=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/2.jpg"));
	}
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(image, 100, 100, 200, 200, this);
		g.setColor(Color.red);
		g.drawString("game over", 50, 50);
	}
}