/*
 * Copyright (C) 2016 100018
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package test;

/**
 *
 * @author sumesh
 */

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import java.util.Properties;

/**
 *
 * @author 100018
 */
public class MailSender {

	private static final String SMTP_HOST_NAME = "server55.nsvista.com";
	private static final String SMTP_AUTH_USER = "sebin.jacob@zoftsolutions.com";
	private static final String SMTP_AUTH_PWD = "sebin123";

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
		MailSentPOJO mailSentPOJO = new MailSentPOJO("sumeshzoft@gmail.com", "jins.varghese@zoftsolutions.com", "HAI", "TEST CONTENT");
		MailSender.sendMail(mailSentPOJO);
	}

    /**
     *
     * @param mailSentPOJO
     * @throws Exception
     */
    public static void sendMail(MailSentPOJO mailSentPOJO) throws Exception {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");

		Authenticator auth = new SMTPAuthenticator();
		Session mailSession = Session.getDefaultInstance(props, auth);

		Transport transport = mailSession.getTransport();

		MimeMessage message = new MimeMessage(mailSession);
		message.setContent(mailSentPOJO.getContent(), "text/html");
		message.setSubject(mailSentPOJO.getSubject());
		message.setFrom(new InternetAddress(mailSentPOJO.getFrom()));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress("sumeshzoft@gmail.com"));

		transport.connect();
		transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
		transport.close();
	}

	private static class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			String username = SMTP_AUTH_USER;
			String password = SMTP_AUTH_PWD;
			return new PasswordAuthentication(username, password);
		}
	}
}