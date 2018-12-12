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
package com.zs.ina.admin.master.emailtemplate.dao;

import com.zs.ina.admin.master.documents.dao.DocumentIMPL;
import com.zs.ina.common.constants.TableNames;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author 100035
 */
public class EmailTempIMPL implements EmailTempDAO {

    Logger logger = Logger.getLogger(DocumentIMPL.class);

    ObservableList<EmailTempBEAN> retrieveEmailTempsList = FXCollections.observableArrayList();
    @Autowired
    DataSource dataSource;
    private JdbcTemplate mySqlJdbcTemplate;

    /**
     *
     * @param emailTempBEAN
     * @return
     */
    @Override
    public int insertEmailTemplate(EmailTempBEAN emailTempBEAN) {
        int row = 0;
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO " + TableNames.TABLE_EMAIL_TEMPLATES + "(\n"
                + "	template_id,\n"
                + "	template_head,\n"
                + "	subhead,\n"
                + "	subject,\n"
                + "	from_mail,\n"
                + "	salutation,\n"  
                + "	content\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		0,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?\n"
                + "	);";
        row = mySqlJdbcTemplate.update(sql, new Object[]{emailTempBEAN.getHead(),
            emailTempBEAN.getSubhead(),
            emailTempBEAN.getSubject(),
            emailTempBEAN.getFromMail(),
            emailTempBEAN.getSalutation(),
            emailTempBEAN.getContents()
        });
        return row;
    }

    /**
     *
     * @param emailTempBEAN
     * @return
     */
    @Override
    public int updateEmailTemplate(EmailTempBEAN emailTempBEAN) {

        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        int row = 0;

        String sql = "UPDATE " + TableNames.TABLE_EMAIL_TEMPLATES + "\n"
                + "SET template_head = ?,\n"
                + " subhead = ?,\n"
                + " subject = ?,\n"
                + " from_mail = ?,\n"
                + " salutation = ?,\n"
                + " content = ?\n"
                + "WHERE\n"
                + "	template_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{emailTempBEAN.getHead(),
            emailTempBEAN.getSubhead(),
            emailTempBEAN.getSubject(),
            emailTempBEAN.getFromMail(),
            emailTempBEAN.getSalutation(),  
            emailTempBEAN.getContents(),
            emailTempBEAN.getTemplateId()
        });

        return row;
    }

    /**
     *
     * @return
     */
    @Override
    public List<EmailTempBEAN> retreiveEmailTemplates() {
        List<EmailTempBEAN> listEmailTemplates = new ArrayList<>();
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TABLE_EMAIL_TEMPLATES + "";
        listEmailTemplates = mySqlJdbcTemplate.query(query, new EmailTempIMPL.DocumentMapperTemplates());
        return listEmailTemplates;
    }

    /**
     *
     */
    public class DocumentMapperTemplates implements RowMapper<EmailTempBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public EmailTempBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            
            EmailTempBEAN emailTempBEAN = new EmailTempBEAN();
            emailTempBEAN.setTemplateId(rs.getString("template_id"));
            emailTempBEAN.setHead(rs.getString("template_head"));
            emailTempBEAN.setSubhead(rs.getString("subhead"));
            emailTempBEAN.setSubject(rs.getString("subject"));
            emailTempBEAN.setFromMail(rs.getString("from_mail"));
            emailTempBEAN.setSalutation(rs.getString("salutation"));
            emailTempBEAN.setContents(rs.getString("content"));
            return emailTempBEAN;
        }
    }

}
