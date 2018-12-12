
/*
 * Copyright (c) 2015, ZoftSolutions. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.zs.ina.common.email;
/*
 * SMTPConfig.java requires these files:
 *   No Dependent files 
 */

import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ZoftSolution-Programmer
 */
public class SMTPConfig {

    static Logger logger = Logger.getLogger(SMTPConfig.class);

    /**
     *
     * @return
     */
    public static Properties readSMTPConfiguration() {
        Properties properties = new Properties();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("SMTPBox.properties");
     //  InputStream inputStream = SMTPConfig.class.getClassLoader().getResourceAsStream("SMTPBox.properties");
        try {
            properties.load(inputStream);
        } catch (Exception exception) {
            exception.printStackTrace();
            logger.info(exception.toString());

        }
        return properties;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        Properties smtpProperties = readSMTPConfiguration();

        System.out.println("<<::\tSMTP Configuration\t::>>\n" + smtpProperties.toString() + "\n<<::\tSMTP Configuration End\t::>>\n");
    }
}
