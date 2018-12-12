/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.common.email;

import static com.zs.ina.common.email.SMTPConfig.readSMTPConfiguration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
public class E_MailSender {

    static Logger logger = Logger.getLogger(E_MailSender.class);

    /**
     *
     * @param mailAddress
     * @param name
     * @throws AddressException
     * @throws MessagingException
     */
    public static void sendMail(String mailAddress, String name) throws AddressException, MessagingException {
        String toAddress = mailAddress;
        String body = "";
        Properties properties = readSMTPConfiguration();
        properties.put(properties.getProperty("host.name"), properties.getProperty("host.value"));
        properties.put(properties.getProperty("host.port.name"), properties.getProperty("host.port.value"));
        properties.put(properties.getProperty("host.smtp.auth.name"), properties.getProperty("host.smtp.auth.value"));
        properties.put(properties.getProperty("host.smtp.starttls.name"), properties.getProperty("host.smtp.starttls.value"));
        properties.put(properties.getProperty("host.smtp.ssl.trust.name"), properties.getProperty("host.smtp.ssl.trust.value"));
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("jinszoft@gmail.com", "6891kolli");
                Map<String, String> mapEmailConfig = new HashMap<>();
                mapEmailConfig = MailConfigDAO.retrieveEmailConfig();
                if (mapEmailConfig.get("email") == null) {
                    mapEmailConfig.put("email", "jinszoft@gmail.com");
                    mapEmailConfig.put("pass", "6891kolli");
                }
                return new PasswordAuthentication(mapEmailConfig.get("email"), mapEmailConfig.get("pass"));
            }
        };

        Session session = Session.getInstance(properties, auth);

        // creates a new e-mail message
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress("info@zoftsolutions.com"));
        InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject("Welcome to International Accadamy\n\n");
        msg.setSentDate(new Date());
        body = "<html>\n"
                + "\n"
                + "  <body><h3>  Dear " + name + ",</h3><br>\n"
                + "  <h2>Welcome to International Academy</h2><br>\n";

        body = body + "<h4>Prefered Countries for Study   : " + "</h4>";
        body = body + "<br><h4>Prefered Countries for Work : " + "</h4>";
        body = body + "<br><h4>Prefered Countries for Migration : " + "</h4>";
        body = body + "<br><h4>Prefered Training Courses   :" + "</h4>";
        body = body + "<br><br><br><img src='http://internationalacademy.in/wp-content/themes/internationalacademy/images/logo.jpg' alt='International Academy'>\n"
                + "</body>\n"
                + "\n"
                + "</html>";
        msg.setContent(body, "text/html");
        Transport.send(msg);
        logger.info("An email has been sent to " + name);
    }

    /**
     *
     * @param mailSentPOJO
     */
    public static void sendMail(MailSentPOJO mailSentPOJO) {
        try {
            //from enquiry
            String toAddress = mailSentPOJO.getTo().trim();
            //  toAddress = "sumeshzoft@gmail.com";
            Properties properties = readSMTPConfiguration();
            properties.put(properties.getProperty("host.name"), properties.getProperty("host.value"));
            properties.put(properties.getProperty("host.port.name"), properties.getProperty("host.port.value"));
            properties.put(properties.getProperty("host.smtp.auth.name"), properties.getProperty("host.smtp.auth.value"));
            properties.put(properties.getProperty("host.smtp.starttls.name"), properties.getProperty("host.smtp.starttls.value"));
            properties.put(properties.getProperty("host.smtp.ssl.trust.name"), properties.getProperty("host.smtp.ssl.trust.value"));
            // creates a new session with an authenticator
            Authenticator auth = new Authenticator() {
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    //                return new PasswordAuthentication("jinszoft@gmail.com", "6891kolli");
                    Map<String, String> mapEmailConfig = new HashMap<>();
                    mapEmailConfig = MailConfigDAO.retrieveEmailConfig();
                    if (mapEmailConfig.get("email") == null) {
                        mapEmailConfig.put("email", "jinszoft@gmail.com");
                        mapEmailConfig.put("pass", "6891kolli");
                    }
                    return new PasswordAuthentication(mapEmailConfig.get("email"), mapEmailConfig.get("pass"));
                }
            };

            Session session = Session.getInstance(properties, auth);
            // creates a new e-mail message
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(mailSentPOJO.getFrom()));
            InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject(mailSentPOJO.getSubject());
            msg.setSentDate(new Date());
            msg.setContent(mailSentPOJO.getContent(), "text/html");
            Transport.send(msg);
        } catch (AddressException ex) {
            ex.printStackTrace();
            logger.error(ex);
        } catch (MessagingException ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
    }

    /**
     *
     * @param mailAddress
     * @param message
     * @param subject
     * @throws AddressException
     * @throws MessagingException
     */
    public static void sendMail(String mailAddress, String message, String subject) throws AddressException, MessagingException {
        //from enquiry
        String toAddress = mailAddress.trim();
        Properties properties = readSMTPConfiguration();
        properties.put(properties.getProperty("host.name"), properties.getProperty("host.value"));
        properties.put(properties.getProperty("host.port.name"), properties.getProperty("host.port.value"));
        properties.put(properties.getProperty("host.smtp.auth.name"), properties.getProperty("host.smtp.auth.value"));
        properties.put(properties.getProperty("host.smtp.starttls.name"), properties.getProperty("host.smtp.starttls.value"));
        properties.put(properties.getProperty("host.smtp.ssl.trust.name"), properties.getProperty("host.smtp.ssl.trust.value"));
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                //                return new PasswordAuthentication("jinszoft@gmail.com", "6891kolli");
                Map<String, String> mapEmailConfig = new HashMap<>();
                mapEmailConfig = MailConfigDAO.retrieveEmailConfig();
                if (mapEmailConfig.get("email") == null) {
                    mapEmailConfig.put("email", "jinszoft@gmail.com");
                    mapEmailConfig.put("pass", "6891kolli");
                }
                return new PasswordAuthentication(mapEmailConfig.get("email"), mapEmailConfig.get("pass"));
            }
        };

        Session session = Session.getInstance(properties, auth);
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("info@zoftsolutions.com"));
        InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setContent(message, "text/html");
        Transport.send(msg);
    }

    /**
     *
     * @param args
     * @throws MessagingException
     */
    public static void main(String[] args) throws MessagingException {
//        String message = "<html>\n"
//                + "\n"
//                + "  <body><h3>  Dear hiii " + ",</h3><br>\n"
//                + "  <h2>Welcome to International Academy</h2><br>\n";
//
//        message = message + "<h4>Thank you for registering with us.<br> Countries for Study   : " + "</h4>";
//        message = message + "<h4>Prefered Countries for Study   : " + "</h4>";
//        message = message + "<br><h4>Prefered Countries for Work : " + "</h4>";
//        message = message + "<br><h4>Prefered Countries for Migration : " + "</h4>";
//        message = message + "<br><h4>Prefered Training Courses   :" + "</h4>";
//        message = message + "<br><br><br><img src='http://internationalacademy.in/wp-content/themes/internationalacademy/images/logo.jpg' alt='International Academy'>\n"
//                + "</body>\n"
//                + "\n"
//                + "</html>";
//        //  sendMail("sumeshzoft@gmail.com", "name");
//        sendMail("sumeshzoft@gmail.com", message, "test name");
//        StringBuilder sb = new StringBuilder();
//        sb.append("<html>");
//        sb.append("<head>");
//        sb.append("</head>");
//        sb.append("<table>");
//        sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Name");
//        sb.append("</th>");
//        sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Code");
//        sb.append("</th>");
//        sb.append("<tr>");
//        sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " + "MUHASSIN");
//        sb.append("</td>");
//        sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " + "1234");
//        sb.append("</td>");
//        sb.append("</tr>");
//        sb.append("<tr>");
//        sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " + "MUSTHAFA");
//        sb.append("</td>");
//        sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " + "4321");
//        sb.append("</td>");
//        sb.append("</tr>");
//        sb.append("</table>");
//        sb.append("</body>");
//        sb.append("</html>");
//
//        System.out.println(sb.toString());
//
        MailSentPOJO jO = new MailSentPOJO("Content", "SUBJECT", "kpkeerthy661@gmail.com", "m4muhassin3pdkas@gmail.com");
        sendMail(jO);

    }
}
