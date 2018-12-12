/*
 * Copyright ZoftSolutions(C) 2016 SUMESH T.G <ZoftSolutions>
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
 *  Developed by ZoftSolutions (2015) Company.
 */
package com.ina.application.config;

import com.zs.ina.common.email.MailConfigDAO;
import com.zs.ina.common.email.MailMail;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
@Configuration
@PropertySource("mail.properties")
public class EmailConfig {

    @Value("${smtp.host}")
    private String host;

    @Value("${smtp.user}")
    private String user;

    @Value("${smtp.pass}")
    private String pass;

    /**
     *
     * @return
     */
    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        Map<String, String> mapEmailConfig = new HashMap<>();
        mapEmailConfig = MailConfigDAO.retrieveEmailConfig();
        if (mapEmailConfig.get("email") != null) {
            javaMailSender.setHost(mapEmailConfig.get("host"));
            javaMailSender.setUsername(mapEmailConfig.get("email"));
            javaMailSender.setPassword(mapEmailConfig.get("pass"));
        } else {
            javaMailSender.setHost(host);
            javaMailSender.setUsername(user);
            javaMailSender.setPassword(pass);
        }

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        javaMailSender.setJavaMailProperties(props);
        return javaMailSender;
    }

    /**
     *
     * @return
     */
    @Bean
    public SimpleMailMessage simpleMailMessage() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("hh@dfgf.dfgfd");
        simpleMailMessage.setSubject("test");
        return simpleMailMessage;
    }

    /**
     *
     * @return
     */
    @Bean
    public MailMail mailMail() {
        return new MailMail();
    }
}
