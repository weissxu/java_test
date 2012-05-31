package com.weiss.j2se.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPReceive {  
	  
    public static void main(String[] args) {  
        Integer port = 2345;  
        byte[] buffer = new byte[2048];  
        try {  
            DatagramSocket datagramSocket = new DatagramSocket(port);  
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);  
            try {  
                for (;;) {  
                    System.out.println("begin receive data....");  
                    datagramSocket.receive(datagramPacket);  
                    System.out.println(datagramPacket.getAddress().getAddress().toString()+"======="+new String(datagramPacket.getData(),0,datagramPacket.getData().toString().length()));  
                    System.out.println(datagramPacket.getData().toString().length());
                    datagramPacket.setLength(datagramPacket.getData().toString().length());  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        } catch (SocketException e) {  
            e.printStackTrace();  
        }  
  
    }  
  
}  