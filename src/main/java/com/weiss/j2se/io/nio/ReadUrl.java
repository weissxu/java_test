package com.weiss.j2se.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class ReadUrl {

	public static void main(String[] args) throws IOException {

		InetSocketAddress socketAddress = new InetSocketAddress("www.baidu.com", 80);
		// step1:打开连接
		SocketChannel channel = SocketChannel.open(socketAddress);
		Charset charset = Charset.forName("gbk");
		// step2:发送请求，使用GBK编码
		channel.write(charset.encode("GET " + "/ HTTP/1.1" + "\r\n\r\n"));
		// step3:读取数据
		ByteBuffer buffer = ByteBuffer.allocate(1024);// 创建1024字节的缓冲
		while (channel.read(buffer) != -1) {
			buffer.flip();// flip方法在读缓冲区字节操作之前调用。
			System.out.println(charset.decode(buffer));
			// 使用Charset.decode方法将字节转换为字符串
			buffer.clear();// 清空缓冲
		}
		channel.close();
	}
}
