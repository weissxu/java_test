package com.weiss;

public class Sh1 {
	public static String testDigest(String info) {
		try {

			// java.security.MessageDigest
			// alg=java.security.MessageDigest.getInstance("MD5");
			java.security.MessageDigest alga = java.security.MessageDigest.getInstance("SHA-1");
			alga.update(info.getBytes());
			byte[] digesta = alga.digest();
			// System.out.println("本信息摘要是:"+byte2hex(digesta));
			// 通过某中方式传给其他人你的信息(myinfo)和摘要(digesta) 对方可以判断是否更改或传输正常
			return byte2hex(digesta);
		} catch (java.security.NoSuchAlgorithmException ex) {
			System.out.println("非法摘要算法");
			return "erro";
		}

	}

	public static String byte2hex(byte[] b) // 二行制转字符串
	{
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			// if (n<b.length-1) hs=hs+":";
		}
		return hs;
	}
}