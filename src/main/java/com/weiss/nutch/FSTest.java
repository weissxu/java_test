package com.weiss.nutch;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.BeforeClass;
import org.junit.Test;

public class FSTest {
	public static FileSystem fileSystem;

	@BeforeClass
	public static void before() throws IOException {
		fileSystem = FileSystem.get(new Configuration());
	}

	@Test
	public void testPath() throws IOException {
		Path path = new Path("student2.xml");
		System.out.println(path.getName());
		System.out.println(path.getParent());
		System.out.println(path.getFileSystem(new Configuration()));
		System.out.println(fileSystem);
	}

	@Test
	public void testName() throws IOException {

		System.out.println(fileSystem.getCanonicalServiceName());
		System.out.println(fileSystem.getDefaultBlockSize());
		System.out.println(fileSystem.getUsed());
	}
}
