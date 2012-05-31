package com.weiss.rpc;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class RpcClient {
	public static void main(String[] args) throws Exception {
		String xmlRpcHost = "http://localhost:9508/MyXmlRpcServlet/";
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setEnabledForExtensions(true);
		config.setServerURL(new URL(xmlRpcHost));
		XmlRpcClient xmlrpc = new XmlRpcClient();
		xmlrpc.setConfig(config);
		List<Object> list = new ArrayList<Object>();
		list.add('h');
		Object result = xmlrpc.execute("MyHandler.invoke", list);
		System.out.println(result);
	}
}
