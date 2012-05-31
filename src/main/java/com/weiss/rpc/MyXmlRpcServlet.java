package com.weiss.rpc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcHandlerMapping;
import org.apache.xmlrpc.webserver.XmlRpcServlet;

public class MyXmlRpcServlet extends XmlRpcServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected XmlRpcHandlerMapping newXmlRpcHandlerMapping() throws XmlRpcException {
		PropertyHandlerMapping mapping = new PropertyHandlerMapping();
		mapping.setVoidMethodEnabled(true);
		mapping.addHandler("MyHandler", MyHandler.class);
		return mapping;
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		super.doPost(request, response);
	}
}
