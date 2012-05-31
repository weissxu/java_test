package com.weiss.j2se.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AsyncServer implements Runnable {
	private static final Log logger=LogFactory.getLog(AsyncServer.class);
	private static final int port = 8080;
	private ByteBuffer r_buff = ByteBuffer.allocate(1024);
	private ByteBuffer w_buff = ByteBuffer.allocate(1024);

	public AsyncServer() {
		
	}

	public void run() {
		try {
			// 生成一个侦听端
			ServerSocketChannel ssc = ServerSocketChannel.open();
			// 将侦听端设为异步方式
			ssc.configureBlocking(false);
			// 生成一个信号监视器
			Selector s = Selector.open();
			// 侦听端绑定到一个端口
			ssc.socket().bind(new InetSocketAddress(port));
			// 设置侦听端所选的异步信号OP_ACCEPT
			ssc.register(s, SelectionKey.OP_ACCEPT);

			System.out.println("echo server has been set up ......");

			while (true) {
				logger.info("a round....");
				int n = s.select();
				if (n == 0) {// 没有指定的I/O事件发生
					continue;
				}
				Iterator<SelectionKey> it = s.selectedKeys().iterator();
				while (it.hasNext()) {
					SelectionKey key = (SelectionKey) it.next();
					if (key.isAcceptable()) {// 侦听端信号触发
						ServerSocketChannel server = (ServerSocketChannel) key.channel();
						// 接受一个新的连接
						SocketChannel sc = server.accept();
						sc.configureBlocking(false);
						// 设置该socket的异步信号OP_READ:当socket可读时，
						// 触发函数DealwithData();
						sc.register(s, SelectionKey.OP_READ);
					}
					if (key.isReadable()) {// 某socket可读信号
						dealwithData(key);
					}
					it.remove();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void dealwithData(SelectionKey key) throws IOException {
		// 由key获取指定socketchannel的引用
		SocketChannel sc = (SocketChannel) key.channel();
		r_buff.clear();
		// 读取数据到r_buff
		while ((sc.read(r_buff)) > 0)
			;
		// 确保r_buff可读
		r_buff.flip();

		w_buff.clear();
		// 将r_buff内容拷入w_buff
		w_buff.put(r_buff);
		w_buff.flip();
		// 将数据返回给客户端
		echoToClient(sc);

		w_buff.clear();
		r_buff.clear();
	}

	public void echoToClient(SocketChannel sc) throws IOException {
		while (w_buff.hasRemaining())
			sc.write(w_buff);
	}

	public static void main(String args[]) {
		new Thread(new AsyncServer()).start();;
	}
}

