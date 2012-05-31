package com.weiss.ftp;

import org.apache.ftpserver.util.EncryptUtils;
import org.junit.Test;

public class EncryptUtilsTest {
	private static final String HELLO_WORLD = "hello,world";

	@Test
	public void testEncrypt() {
		String md5 = EncryptUtils.encryptMD5(HELLO_WORLD);
		String sha = EncryptUtils.encryptSHA(HELLO_WORLD);
		System.out.println("md5: " + md5);
		System.out.println("sha: " + sha);
	}
}
