package com.weiss.j2se.awt;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

public class ResizePic3 {
	String fromdir = "";
	String todir = "";
	String imgfile = "";
	String sysimgfile = "";
	String ext = "jpg"; // 默认图片格式
	int size = 0;

	public boolean CreateResizePic3() throws Exception {
		double Ratio = 0.0;
		BufferedImage bufferedImg = null;
		Image Itemp = null;

		File file = new File(fromdir, imgfile);
		if (!file.isFile())
			throw new Exception(file + "   is   not   image   file   error   in   CreateResizePic3!");

		File ThF = new File(todir, sysimgfile + "." + ext);
		try {
			bufferedImg = ImageIO.read(file);
			int BW = (size * bufferedImg.getWidth() / bufferedImg.getHeight());
			int BH = size;
			Itemp = bufferedImg.getScaledInstance(BW, BH, Image.SCALE_SMOOTH);
			BufferedImage BImg = null;

			try {
				if (bufferedImg.getHeight() > size) {
					Ratio = (double) (size) / bufferedImg.getHeight();
				}
				AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(Ratio, Ratio), null);
				BImg = op.filter(bufferedImg, null);

			} catch (Exception ex) {
				BImg = new BufferedImage(Itemp.getWidth(null), Itemp.getHeight(null), BufferedImage.TYPE_INT_RGB);
				Graphics2D g2 = BImg.createGraphics();
				g2.drawImage(Itemp, 0, 0, null);
			}

			ImageIO.write(BImg, ext, ThF);
		} catch (Exception ex) {
			this.saveJust();
			System.out.println("   ImageIo.write   error   in   CreatThum:   " + ex.getMessage());
			return false;
		}
		return (true);
	}

	public void saveJust() {
		try {
			File F = new File(fromdir, imgfile);
			if (!F.isFile())
				throw new Exception(F + "   is   not   image   file   error   in   CreateResizePic3!");
			File ThF = new File(todir, imgfile);
			FileInputStream fis = new FileInputStream(F);
			FileOutputStream fos = new FileOutputStream(ThF);
			byte[] b = new byte[255];
			int bRead = 0;
			while ((bRead = fis.read(b)) > -1)
				fos.write(b, 0, bRead);
			fos.close();
			fis.close();
		} catch (Exception e) {
		}

	}

	public void setfromdir(String str) {
		fromdir = str;
	}

	public void settodir(String str) {
		todir = str;
	}

	public void setimgfile(String str) {
		imgfile = str;
	}

	public void setsysimgfile(String str) {
		for (int i = str.length() - 1; i >= 0; i--)
			if (str.charAt(i) == '.') {
				sysimgfile = str.substring(0, i);
				i = -1;
				return;
			}
		sysimgfile = str;
	}

	public void setsize(int s) {
		size = s;
	}

	public void setext(String str) {
		ext = str;
	}

}