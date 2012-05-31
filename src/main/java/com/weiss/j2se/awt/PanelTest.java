package com.weiss.j2se.awt;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelTest extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		tt(g);
	}

	private void tt(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		java.awt.font.FontRenderContext frc = g2.getFontRenderContext();
		java.awt.Font f = new java.awt.Font("宋体", java.awt.Font.PLAIN, 80);
		java.awt.font.TextLayout tl = new java.awt.font.TextLayout("序号", f, frc);
		AffineTransform translateInstance = AffineTransform.getTranslateInstance(1, 200);
		java.awt.Shape sha = tl.getOutline(translateInstance);
		g2.setColor(java.awt.Color.BLUE);
		g2.draw(sha);
		// g2.fill(sha);//如果去掉这行就是空心字了
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.getContentPane().add(new PanelTest());
		f.setSize(400, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
