package com.weiss.rpc;

import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ContextHandlerCollection;
import org.mortbay.jetty.handler.HandlerCollection;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

public class RpcServer {
	public static void main(String[] args) throws Exception {
		Server rpcServer = new Server(9508);
		ContextHandlerCollection contexts = new ContextHandlerCollection();
		// Context root = new Context(contexts, "/TriggerXmlRpcServlet",
		// Context.SESSIONS);
		Context root = new Context(contexts, "/MyXmlRpcServlet");
		root.addServlet(new ServletHolder(new MyXmlRpcServlet()), "/*");
		HandlerCollection handlers = new HandlerCollection();

		handlers.setHandlers(new Handler[] { root });

		rpcServer.setHandler(handlers);
		rpcServer.start();
	}
}
