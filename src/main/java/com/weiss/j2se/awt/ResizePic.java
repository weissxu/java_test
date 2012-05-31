package com.weiss.j2se.awt;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ResizePic {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		File src = new File("apple.jpg");
		Image image = ImageIO.read(src);
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		System.out.println("width: " + width + "  height: " + height);
		System.out.println("will be changed to 200x100");
		BufferedImage bufferedImage = new BufferedImage(200, 100, BufferedImage.TYPE_INT_RGB);
		bufferedImage.getGraphics().drawImage(image, 0, 0, 200, 100, null);
		FileOutputStream fos = new FileOutputStream("appleChanged200x100.jpg");
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
		encoder.encode(bufferedImage);
		System.out.println("转换成功...");
		fos.close();
	}
}
