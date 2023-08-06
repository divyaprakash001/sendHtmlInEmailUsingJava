package com.divya.email_sending;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class App {
	public static void main(String[] args) throws IOException {
		System.out.println("Preparing to send message ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter your subject here :: ");
		String subject = br.readLine();
		System.out.println("Enter your message here :: ");
		String message = br.readLine();
		String to = "divyaprakashara99@gmail.com";
		String from = "divyaprakashara09@gmail.com";

		sendHtml(message, subject, to, from);

	}

//	**********************************************************************************

	private static void sendHtml(String message, String subject, String to, String from) {
//	variable for gmail host
		String host = "smtp.gmail.com";

//	get the system properties
		Properties properties = System.getProperties();
		System.out.println("Properties :: " + properties);

//	setting important information to properties object

//	host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465"); // google smtp port = 465
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// step 1. to get the session object
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("divyaprakashara09@gmail.com", "rfwrxtsndmutqowv");
			}

		});

		session.setDebug(true);

//	step 2. compose the message [text, multi media]

		MimeMessage m_message = new MimeMessage(session);

		try {
//	from email id
			m_message.setFrom(from);

//		adding recipient
			m_message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

//		adding subject to message
			m_message.setSubject(subject);

//		adding text to message
			m_message.setContent("<h1><mark>This</mark> is a html file.</h1>", "text/html");

//	step 3. 	send the message using transport class
			Transport.send(m_message);

			System.out.println("Message sent successfully");

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}
