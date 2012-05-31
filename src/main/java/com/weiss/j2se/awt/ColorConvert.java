package com.weiss.j2se.awt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorConvert extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension d = getSize();
		render(d.width, d.height / 2, (Graphics2D) g);
	}

	public void render(int w, int h, Graphics2D g2) {
		BufferedImage srcImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D imageG2 = srcImg.createGraphics();

		// 设置白底黑字
		imageG2.setBackground(Color.WHITE);
		imageG2.clearRect(0, 0, w, h);
		imageG2.setColor(Color.BLACK);

		String text = "标题：订货单位";
		Font font = new Font("宋体", Font.BOLD, 28);
		FontRenderContext frc = g2.getFontRenderContext();
		TextLayout tl = new TextLayout(text, font, frc);
		tl.draw(imageG2, 50, 50);

		// 文字的宽度将压缩一半显示
		g2.drawImage(srcImg, 0, 0, srcImg.getWidth() / 2, srcImg.getHeight(), null);
		// 文字正常显示
		g2.drawImage(srcImg, 0, srcImg.getHeight(), srcImg.getWidth(), srcImg.getHeight(), null);
	}

	public static void main(String s[]) {
		JFrame f = new JFrame();
		f.setSize(400, 500);
		f.getContentPane().add(new ColorConvert());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}