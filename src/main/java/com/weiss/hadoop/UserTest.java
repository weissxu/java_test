package com.weiss.hadoop;

import java.io.IOException;

import org.apache.hadoop.security.UserGroupInformation;
import org.junit.Test;

public class UserTest {
	@Test
	public void testName() throws IOException {
		UserGroupInformation user = UserGroupInformation.getCurrentUser();
		String name = user.getUserName();
		System.out.println(name);
	}
}
