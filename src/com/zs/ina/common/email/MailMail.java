package com.zs.ina.common.email;

import java.io.File;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

//import com.zs.i.uploadimage.UploadImageDAO;

/**
 *
 * @author 100018
 */
public class MailMail {
//	@Autowired
//	UploadImageDAO uploadImageDAO;

    @Autowired
    private JavaMailSender mailSender;

    /**
     *
     * @param mailSender
     */
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     *
     * @param from
     * @param to
     * @param subject
     * @param msg
     * @param ccList
     */
    public void sendMail(final String from, final String to, final String subject, final String msg,
            final String ccList) {

        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
                mimeMessage.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccList));
				mimeMessage.setFrom(new InternetAddress(from));
                mimeMessage.setSubject(subject);
                // mimeMessage.setText(msg);
                mimeMessage.setContent(msg, "text/html");

            }
        };
        mailSender.send(messagePreparator);
    }

    /**
     *
     * @param mailSentPOJO
     */
    public void sendMail(MailSentPOJO mailSentPOJO) {

        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                System.out.println("HAIIIII =========="+mailSentPOJO);
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mailSentPOJO.getTo()));
//                mimeMessage.setRecipients(Message.RecipientType.CC, InternetAddress.parse(mailSentPOJO.getCc()));
				mimeMessage.setFrom(new InternetAddress(mailSentPOJO.getFrom()));
                mimeMessage.setSubject(mailSentPOJO.getSubject());
                // mimeMessage.setText(msg);
                mimeMessage.setContent(mailSentPOJO.getMessage(), "text/html");

            }
        };
        mailSender.send(messagePreparator);
    }

    /**
     *
     * @param from
     * @param to
     * @param subject
     * @param msg
     * @param file
     */
    public void sendMail(final String from, final String to, final String subject, final String msg, File file) {
        try {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setFrom(new InternetAddress(uploadImageDAO.getCompanyEmail()));  
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(msg);

            // attach the file  
//        FileSystemResource file = new FileSystemResource(new File("c:/rr.jpg"));  
            helper.addAttachment("DailyFollowupDetail.xlsx", file);//image will be sent by this name  

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
