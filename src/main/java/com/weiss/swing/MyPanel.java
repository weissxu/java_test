package com.weiss.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class MyPanel extends JPanel implements KeyListener,MouseListener,MouseMotionListener{
	int x=20;
	int y=20;
	int startx,starty,endx,endy;
	
	public void paint(Graphics g){
 		super.paint(g);
 		g.setColor(Color.red);
		g.fillOval(x, y, 10, 10);
		g.drawLine(startx, starty, endx, endy);
	}

	
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP){
			y--;
		}else if(e.getKeyCode()==KeyEvent.VK_DOWN){
			y++;
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT){
			x--;
		}else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			x++;
		}
		if(x>200) x=200;
		if(x<0)x=0;
		if(y>200)y=200;
		if(y<0)y=0;
		this.repaint();
	}
	
	public void keyTyped(KeyEvent e) {
	}
	
	public void keyReleased(KeyEvent e) {
	}


	
	public void mouseClicked(MouseEvent e) {
		
		System.out.println("坐标：（"+e.getX()+","+e.getY()+")");
	}
	
	public void mousePressed(MouseEvent e) {
		startx=e.getX()-10;
		starty=e.getY()-30;
		/*startx=e.getXOnScreen();
		starty=e.getYOnScreen();*/
		System.out.println("start坐标：（"+startx+","+starty+")");
		System.out.println("坐标：（"+e.getX()+","+e.getY()+")");
	}
	
	public void mouseReleased(MouseEvent e) {
		endx=e.getX()-10;
		endy=e.getY()-30;
		System.out.println("end坐标：（"+e.getX()+","+e.getY()+")");
		repaint();
	}
	
	public void mouseEntered(MouseEvent e) {
		System.out.println("------mouse enter");
	}
	
	public void mouseExited(MouseEvent e) {
		System.out.println("------mouse exit");
	}


	
	public void mouseDragged(MouseEvent e) {
		
//		repaint();
	}
	
	public void mouseMoved(MouseEvent e) {
//		System.out.println("motion坐标：（"+e.getX()+","+e.getY()+")");
	}

}
