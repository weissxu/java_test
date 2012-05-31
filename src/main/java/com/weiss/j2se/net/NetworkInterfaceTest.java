package com.weiss.j2se.net;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.junit.Test;

public class NetworkInterfaceTest {
	@Test
	public void testIP() throws SocketException {
		Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
		while (interfaces.hasMoreElements()) {
			NetworkInterface face = interfaces.nextElement();
			// System.out.println("hardwareAddress:" + new
			// String(face.getHardwareAddress()));
			Enumeration<InetAddress> addres = face.getInetAddresses();
			while (addres.hasMoreElements()) {
				InetAddress addr = addres.nextElement();
				System.out.println("ip: " + addr.getHostAddress());
			}
		}
	}
}
