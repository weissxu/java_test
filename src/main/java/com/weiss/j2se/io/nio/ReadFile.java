package com.weiss.j2se.io.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadFile {
	private static final String filePath = "student2.xml";

	public static void main(String[] args) throws IOException {
		StringBuilder builder = new StringBuilder();
		FileChannel fileChannel = new FileInputStream(filePath).getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while (true) {
			buffer.clear();
			int read = fileChannel.read(buffer);
			if (read == -1) {
				break;
			}
			buffer.flip();
			builder.append(new String(buffer.array(), 0, buffer.limit()));
		}
		System.out.println(builder.toString());
		fileChannel.close();

	}
}
