package com.weiss.j2se.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

public class NioTest {

	private Charset charset = Charset.forName("GBK");// 创建GBK字符集

	@Test
	public void testReadHTML() {
		SocketChannel channel = null;
		try {
			InetSocketAddress socketAddress = new InetSocketAddress("www.baidu.com", 80);
			// step1:打开连接
			channel = SocketChannel.open(socketAddress);
			// step2:发送请求，使用GBK编码
			channel.write(charset.encode("GET / HTTP/1.1" + "\r\n\r\n"));
			// step3:读取数据
			ByteBuffer buffer = ByteBuffer.allocate(1024);// 创建1024字节的缓冲
			while (channel.read(buffer) != -1) {
				buffer.flip();// flip方法在读缓冲区字节操作之前调用。
				System.out.println(charset.decode(buffer));
				// 使用Charset.decode方法将字节转换为字符串
				buffer.clear();// 清空缓冲
			}
		} catch (IOException e) {
			System.err.println(e.toString());
		} finally {
			if (channel != null) {
				try {
					channel.close();
				} catch (IOException e) {
				}
			}
		}
	}

	@Test
	public void testCopyFile() throws Exception {
		String infile = "e:\\Test.java";
		String outfile = "e:\\Test1.java";
		// 获取源文件和目标文件的输入输出流
		FileInputStream fin = new FileInputStream(infile);
		FileOutputStream fout = new FileOutputStream(outfile);
		// 获取输入输出通道
		FileChannel fcin = fin.getChannel();
		FileChannel fcout = fout.getChannel();
		// 创建缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while (true) {
			// clear方法重设缓冲区，使它可以接受读入的数据
			buffer.clear();
			// 从输入通道中将数据读到缓冲区
			int r = fcin.read(buffer);
			// read方法返回读取的字节数，可能为零，如果该通道已到达流的末尾，则返回-1
			if (r == -1) {
				break;
			}
			// flip方法让缓冲区可以将新读入的数据写入另一个通道
			buffer.flip();
			// 从输出通道中将数据写入缓冲区
			fcout.write(buffer);
		}
		fin.close();
		fout.close();
	}

	@Test
	public void testSelector() throws CharacterCodingException, IOException {
		Charset charset = Charset.forName("ISO-8859-1");
		CharsetEncoder encoder = charset.newEncoder();
		CharsetDecoder decoder = charset.newDecoder();

		ByteBuffer buffer = ByteBuffer.allocate(512);

		Selector selector = Selector.open();

		ServerSocketChannel server = ServerSocketChannel.open();
		server.socket().bind(new java.net.InetSocketAddress(8000));
		server.configureBlocking(false);
		SelectionKey serverkey = server.register(selector, SelectionKey.OP_ACCEPT);

		for (;;) {
			selector.select();
			Set keys = selector.selectedKeys();

			for (Iterator i = keys.iterator(); i.hasNext();) {
				SelectionKey key = (SelectionKey) i.next();
				i.remove();

				if (key == serverkey) {
					if (key.isAcceptable()) {
						SocketChannel client = server.accept();
						client.configureBlocking(false);
						SelectionKey clientkey = client.register(selector, SelectionKey.OP_READ);
						clientkey.attach(new Integer(0));
					}
				} else {
					SocketChannel client = (SocketChannel) key.channel();
					if (!key.isReadable())
						continue;
					int bytesread = client.read(buffer);
					if (bytesread == -1) {
						key.cancel();
						client.close();
						continue;
					}
					buffer.flip();
					String request = decoder.decode(buffer).toString();
					buffer.clear();
					if (request.trim().equals("quit")) {
						client.write(encoder.encode(CharBuffer.wrap("Bye.")));
						key.cancel();
						client.close();
					} else {
						int num = ((Integer) key.attachment()).intValue();
						String response = num + ": " + request.toUpperCase();
						client.write(encoder.encode(CharBuffer.wrap(response)));
						key.attach(new Integer(num + 1));
					}
				}
			}
		}
	}
}
