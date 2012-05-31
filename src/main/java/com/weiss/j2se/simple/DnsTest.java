package com.weiss.j2se.simple;

import java.util.Hashtable;

import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.junit.Test;

public class DnsTest {
	@Test
	public void test() throws Exception{
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
		env.put("java.naming.provider.url", "dns:");
		DirContext ctx = new InitialDirContext(env);
		Attributes attributes =ctx.getAttributes("_sip._udp.domain.com", new String [] { "SRV" });
//		 ctx.getAttributes("_sip._udp", new String [] { "SRV" });
		System.out.println(attributes);
	}
}
