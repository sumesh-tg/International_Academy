/*
 * Copyright (C) 2016 100035
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
package com.zs.ina.common.email.dao;

import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.common.constants.TableNames;
import com.zs.ina.common.email.MailSentPOJO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author 100035
 */
public class MailSentIMPL implements MailSentDAO {

    Logger logger = Logger.getLogger(MailSentIMPL.class);

    @Autowired
    DataSource dataSource;
    private JdbcTemplate mySqlJdbcTemplate;

    /**
     *
     * @param mailSentPOJO
     * @return
     */
    public int logNotification(MailSentPOJO mailSentPOJO) {

        mySqlJdbcTemplate = new JdbcTemplate(dataSource);

        mailSentPOJO.setEmailLogId("EMAIL_LOG_" + UiiDGenerator.getUIID8());
        System.out.println("EMAIL_LOG_ID :" + mailSentPOJO.getEmailLogId());
        int row = 0;
        String sqls = "INSERT INTO " + TableNames.TABLE_EMAIL_LOG + " (\n"
                + "	email_log_id,\n"
                + "	mail_from,\n"
                + "	mail_to,\n"
                + "	subject,\n"
                + "	content,\n"
                + "	sent_date,\n"
                + "	purpose,\n"
                + "	enquiry_id\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		0,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		NOW(),\n"
                + "		?,\n"
                + "		?\n"
                + "	);";
        row = mySqlJdbcTemplate.update(sqls, new Object[]{mailSentPOJO.getFrom(),
            mailSentPOJO.getTo(),
            mailSentPOJO.getSubject(),
            mailSentPOJO.getMessage(),
            mailSentPOJO.getEmailHead(),
            mailSentPOJO.getEnquiryId()
        });

        return row;

    }

    /**
     *
     * @param emailHead
     * @return
     */
    public List<MailSentPOJO> getEmailTemplateDetails(String emailHead) {
        //   MailSentPOJO mailSentPOJO = new MailSentPOJO();

        List<MailSentPOJO> listMailSentPOJO = new ArrayList<>();
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT\n"
                + "	email_temps.from_mail,\n"
                + "	email_temps.subhead,\n"
                + "	email_temps.subject,\n"
                + "	email_temps.salutation,\n"
                + "	email_temps.content,\n"
                + "	master_sign.signature\n"
                + "FROM\n"
                + "	master_signature master_sign\n"
                + "RIGHT JOIN email_templates email_temps ON master_sign.email_template_head = email_temps.template_head\n"
                + "WHERE\n"
                + "	email_temps.template_head =?";
        listMailSentPOJO = mySqlJdbcTemplate.query(query, new Object[]{emailHead}, new DocumentMapperEmailTemplate());
//        if (listMailSentPOJO.isEmpty()) {
//            return mailSentPOJO;
//        } else {
//            return listMailSentPOJO.get(0);
//        }
        return listMailSentPOJO;

    }

    /**
     *
     * @param emailSubhead
     * @return
     */
    @Override
    public List<MailSentPOJO> getEmailTemplateDetailsUsingSubhead(String emailSubhead) {
        List<MailSentPOJO> listMailSentPOJO = new ArrayList<>();
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT\n"
                + "	email_temps.from_mail,\n"
                + "	email_temps.subhead,\n"
                + "	email_temps. SUBJECT,\n"
                + "	email_temps.salutation,\n"
                + "	email_temps.content,\n"
                + "	master_sign.signature\n"
                + "FROM\n"
                + "	master_signature master_sign\n"
                + "RIGHT JOIN email_templates email_temps ON master_sign.email_template_head = email_temps.template_head\n"
                + "WHERE\n"
                + "	email_temps.subhead =?";
        listMailSentPOJO = mySqlJdbcTemplate.query(query, new Object[]{emailSubhead}, new DocumentMapperEmailTemplate());
        return listMailSentPOJO;
    }

    /**
     *
     */
    public class DocumentMapperEmailTemplate implements RowMapper<MailSentPOJO> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public MailSentPOJO mapRow(ResultSet rs, int rowNum) throws SQLException {
            MailSentPOJO mailSentPOJO = new MailSentPOJO();
            mailSentPOJO.setFrom(rs.getString("from_mail"));
            mailSentPOJO.setEmailsubhead(rs.getString("subhead"));
            mailSentPOJO.setSubject(rs.getString("subject"));
            mailSentPOJO.setSalutation(rs.getString("salutation"));
            mailSentPOJO.setContent(rs.getString("content"));
            mailSentPOJO.setSignature(rs.getString("signature"));
            return mailSentPOJO;
        }
    }
}
