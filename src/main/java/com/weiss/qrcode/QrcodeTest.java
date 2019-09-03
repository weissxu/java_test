package com.weiss.qrcode;

public class QrcodeTest {
//	class J2SEImage implements QRCodeImage {
//		BufferedImage image;
//
//		public J2SEImage(BufferedImage image) {
//			this.image = image;
//		}
//
//		@Override
//		public int getWidth() {
//			return image.getWidth();
//		}
//
//		@Override
//		public int getHeight() {
//			return image.getHeight();
//		}
//
//		@Override
//		public int getPixel(int x, int y) {
//			return image.getRGB(x, y);
//		}
//
//	}
//
//	@Test
//	public void testCode() throws IOException {
//		Qrcode qrcode = new Qrcode();
//		qrcode.setQrcodeErrorCorrect('M');
//		qrcode.setQrcodeEncodeMode('B');
//		qrcode.setQrcodeVersion(7);
//
//		String testString = "hello,world_-+^%$#@!~`=&)*(";
//
//		byte[] d = testString.getBytes("GBK");
//
//		BufferedImage bi = new BufferedImage(139, 139, BufferedImage.TYPE_INT_RGB);
//
//		// createGraphics
//		Graphics2D g = bi.createGraphics();
//
//		// set background
//		g.setBackground(Color.WHITE);
//		g.clearRect(0, 0, 139, 139);
//
//		g.setColor(Color.BLACK);
//
//		if (d.length > 0 && d.length < 123) {
//			boolean[][] b = qrcode.calQrcode(d);
//
//			for (int i = 0; i < b.length; i++) {
//
//				for (int j = 0; j < b.length; j++) {
//					if (b[j][i]) {
//						g.fillRect(j * 3 + 2, i * 3 + 2, 3, 3);
//					}
//				}
//
//			}
//		}
//
//		g.dispose();
//		bi.flush();
//
//		String FilePath = "TestQRCode.png";
//		File f = new File(FilePath);
//
//		ImageIO.write(bi, "png", f);
//		System.out.println("doned!");
//	}
//
//	@Test
//	public void testDecode() {
//		QRCodeDecoder decoder = new QRCodeDecoder();
//
//		File imageFile = new File("TestQRCode.png");
//
//		BufferedImage image = null;
//
//		try {
//			image = ImageIO.read(imageFile);
//		} catch (IOException e) {
//			System.out.println("Error: " + e.getMessage());
//		}
//		try {
//
//			String decodedData = new String(decoder.decode(new J2SEImage(image)), "GBK");
//
//			System.out.println(decodedData);
//		} catch (DecodingFailedException dfe) {
//			System.out.println("Error: " + dfe.getMessage());
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//
//	}

}
