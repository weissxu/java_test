package com.weiss.j2se.io.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AsyncClient {
	private static final Log logger=LogFactory.getLog(AsyncClient.class);
	private static final String host="localhost";
	private static final int port = 8080;
	private SocketChannel sc;
	private final int MAX_LENGTH = 1024;
	private ByteBuffer r_buff = ByteBuffer.allocate(MAX_LENGTH);
	private ByteBuffer w_buff = ByteBuffer.allocate(MAX_LENGTH);

	public AsyncClient() {
	}

	private void run() {
		try {
			InetSocketAddress addr = new InetSocketAddress(host, port);
			// 生成一个socketchannel
			sc = SocketChannel.open();

			// 连接到server
			sc.connect(addr);
			while (!sc.finishConnect()){
				Thread.sleep(1000);
			}
			System.out.println("connection has been established!...");

			while (true) {
				logger.info("client a round....");
				// 回射消息
				String echo;
				try {
					System.err.println("Enter msg you'd like to send: ");
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					// 输入回射消息
					echo = br.readLine();

					// 把回射消息放入w_buff中
					w_buff.clear();
					w_buff.put(echo.getBytes());
					w_buff.flip();
				} catch (IOException ioe) {
					System.err.println("sth. is wrong with br.readline() ");
				}

				// 发送消息
				while (w_buff.hasRemaining())
					sc.write(w_buff);
				w_buff.clear();

				// 进入接收状态
				rec();
				// 间隔1秒
				Thread.sleep(1000);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

	// //////////
	// 读取server端发回的数据，并显示
	public void rec() throws IOException {
		int count;
		r_buff.clear();
		count = sc.read(r_buff);
		r_buff.flip();
		byte[] temp = new byte[r_buff.limit()];
		r_buff.get(temp);
		System.out.println("reply is " + count + " long, and content is: " + new String(temp));
	}

	public static void main(String args[]) {
		new AsyncClient().run();
	}
}

