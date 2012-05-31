package com.weiss.mail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender implements Runnable {
	private final String host = "smtp.163.com"; // 发件人使用发邮件的电子信箱服务器
	private final String from = "xusiwei666@163.com"; // 发邮件的出发地（发件人的信箱）
	private String to = "siwei666@163.com"; // 发邮件的目的地（收件人信箱）
	private Session session;

	public MailSender(String to) {
		if (to != null && to.length() > 0) {
			this.to = to;
		}
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true"); // 这样才能通过验证
		MyAuthenticator myauth = new MyAuthenticator("xusiwei666@163.com",
				"xss19880905");
		session = Session.getDefaultInstance(props, myauth);
	}

	private boolean send() {
		try {

			MimeMessage message = new MimeMessage(session);

			// Set the from address
			message.setFrom(new InternetAddress(from));

			// Set the to address
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));

			// Set the subject
			message.setSubject("测试程序！时间标识："+new SimpleDateFormat("yyyy--MM-dd hh:mm:ss").format(new Date()));

			// Set the content
			message.setText("这是用java写的发送电子邮件的测试程序！from xusiwei666");

			message.saveChanges();
			System.out.println(Thread.currentThread()+"正在发送邮件：邮件名：" +to+" time:"+new SimpleDateFormat("yyyy--MM-dd hh:mm:ss").format(new Date()));
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	public void run() {
		send();
	}
	public static void main(String[] args) {
		new MailSender(null).run();
	}
}
