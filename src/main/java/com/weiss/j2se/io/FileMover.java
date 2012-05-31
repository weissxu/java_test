package com.weiss.j2se.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class FileMover {
	private static final Logger logger = Logger.getLogger(FileMover.class);
	public static final String FOLDER = "/Users/siwei/workspace/eclipse/test/";
	public static final String DISTFOLDER = "/Users/siwei/redis-test/JAVASRC/";

	public static void main(String[] args) throws IOException {
		File[] files = new File(FOLDER).listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".java");
			}
		});

		for (File file : files) {
			FileUtils.copyFile(file, new File(DISTFOLDER + file.getName()));
			logger.info("done " + file.getName());
			FileUtils.forceDelete(file);
		}
		logger.info("success..");
	}
}
