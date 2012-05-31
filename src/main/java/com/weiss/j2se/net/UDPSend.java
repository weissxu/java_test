package com.weiss.j2se.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPSend {  
    public static void main(String[] args) {  
        try {  
            for (;;) {  
  
                DatagramSocket sendSocket = new DatagramSocket();  
                DatagramPacket dataPack = new DatagramPacket(  
                        "hello ,welcome to study java..".getBytes(),  
                        "hello ,welcome to study java..".length(), 
                        InetAddress.getByName("127.0.0.1"), 
                        new Integer(2345));  
                try {  
                    sendSocket.send(dataPack);  
                    sendSocket.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        } catch (SocketException e) {  
            e.printStackTrace();  
        } catch (UnknownHostException e) {  
            e.printStackTrace();  
        }  
    }  
  
}  
