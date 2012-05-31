package com.weiss.j2se.io;

import java.io.IOException;

public class TestPipe {

	public static void main(String[] args) throws IOException {
		Receiver receiver=new Receiver();
		Sender sender=new Sender(receiver);
		//			os.connect(is);
		new Thread(sender).run();
		new Thread(receiver).run();

	}

}
