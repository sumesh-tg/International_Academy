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
package com.zs.ina.admin.master.emailtemplate.signature.dao;

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
public class SignatureIMPL implements SignatureDAO {

    Logger logger = Logger.getLogger(SignatureIMPL.class);
    private static final String TABLE_EMAIL_SIGNATURE = "master_signature";
    ObservableList<SignatureBEAN> retrieveEmailSignList = FXCollections.observableArrayList();
    @Autowired
    DataSource dataSource;
    private JdbcTemplate mySqlJdbcTemplate;

    /**
     *
     * @param signatureBEAN
     * @return
     */
    @Override
    public int insertEmailSignature(SignatureBEAN signatureBEAN) {

        int row = 0;
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO " + TABLE_EMAIL_SIGNATURE + "(\n"
                + "	signature_id,\n"
                + "	email_template_head,\n"
                + "	signature\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		0,\n"
                + "		?,\n"
                + "		?\n"
                + "	);";
        row = mySqlJdbcTemplate.update(sql, new Object[]{signatureBEAN.getEmailHead(),
            signatureBEAN.getSignature()
        });
        return row;
    }

    /**
     *
     * @param signatureBEAN
     * @return
     */
    @Override
    public int updateEmailSignature(SignatureBEAN signatureBEAN) {

        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        int row = 0;

        String sql = "UPDATE " + TABLE_EMAIL_SIGNATURE + "\n"
                + "SET email_template_head = ?,\n"
                + " signature = ?\n"
                + "WHERE\n"
                + "	signature_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{signatureBEAN.getEmailHead(),
            signatureBEAN.getSignature(),
            signatureBEAN.getSignatureId()
        });

        return row;
    }

    /**
     *
     * @return
     */
    @Override
    public List<SignatureBEAN> retreiveEmailSignature() {
        List<SignatureBEAN> listEmailSignatures = new ArrayList<>();
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TABLE_EMAIL_SIGNATURE + "";
        listEmailSignatures = mySqlJdbcTemplate.query(query, new SignatureIMPL.DocumentMapperTemplates());
        return listEmailSignatures;
    }

    /**
     *
     */
    public class DocumentMapperTemplates implements RowMapper<SignatureBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public SignatureBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            SignatureBEAN signatureBEAN = new SignatureBEAN();
            signatureBEAN.setSignatureId(rs.getString("signature_id"));
            signatureBEAN.setEmailHead(rs.getString("email_template_head"));
            signatureBEAN.setSignature(rs.getString("signature"));
            return signatureBEAN;
        }
    }
}
