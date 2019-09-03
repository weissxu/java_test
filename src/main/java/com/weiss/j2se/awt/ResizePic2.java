package com.weiss.j2se.awt;

import javax.swing.*;
import java.awt.*;

public class ResizePic2 {
    public static void saveImage(Image pImage, String ofileDirAndName) {
//		int w = pImage.getWidth(null);
//		int h = pImage.getHeight(null);
//		int[] pixels = new int[w * h];
//		PixelGrabber pg = new PixelGrabber(pImage, 0, 0, w, h, pixels, 0, w);
//		try {
//			pg.grabPixels();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		ByteArrayOutputStream bout = new ByteArrayOutputStream(300000);
//		System.out.println("3 ");
//		System.out.println("图像宽高: " + w + ": " + h);
//		BufferedImage bufferedImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
//
//		bufferedImage.setRGB(0, 0, 800, 600, pixels, 0, w);
//		float[] sharpKernel = { 0.0f, -1.0f, 0.0f, -1.0f, 5.0f, -1.0f, 0.0f, -1.0f, 0.0f };
//		System.out.println("5 ");
//		BufferedImageOp sharpen = new ConvolveOp(new Kernel(3, 3, sharpKernel), ConvolveOp.EDGE_NO_OP, null);
//		BufferedImage sharp = sharpen.filter(bufferedImage, null);
//		try {
//			JPEGEncodeParam jpegParam = JPEGCodec.getDefaultJPEGEncodeParam(sharp);
//			jpegParam.setQuality(1.0f, false);
//			JPEGImageEncoder jpeg = JPEGCodec.createJPEGEncoder(bout, jpegParam);
//			jpeg.encode(bufferedImage);
//			bout.writeTo(new FileOutputStream(ofileDirAndName));
//		} catch (Exception ee) {
//			System.err.println("出错了 " + ee);
//		}
    }

    public static void main(String[] args) {
        ImageIcon mi = new ImageIcon("apple.jpg");
        saveImage(mi.getImage(), "appleChanged2.jpg ");// 存成800*600的图像
    }
}
