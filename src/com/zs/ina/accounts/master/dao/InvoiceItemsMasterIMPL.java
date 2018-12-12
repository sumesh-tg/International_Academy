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
package com.zs.ina.accounts.master.dao;

import com.zs.ina.accounts.invoice.dao.InvoiceItemsBEAN;
import com.zs.ina.common.constants.TableNames;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * @author SUMESH T.G <ZoftSolutions>
 */
public class InvoiceItemsMasterIMPL implements InvoiceItemsMasterDAO {

    static Logger logger = Logger.getLogger(InvoiceItemsMasterIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertInvoiceMasterItems(InvoiceItemsBEAN invoiceItemsBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO account_master_items_tbl (\n"
                + "	item_id,\n"
                + "	item_name,\n"
                + "	description,\n"
                + "	rate,\n"
                + "	unit,\n"
                + "	tax,\n"
                + "	category,\n"
                + "	is_this_mandatory,\n"
                + "	mandatory_for,\n"
                + "	is_delete\n"
                + ")\n"
                + "VALUES\n"
                + "	(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        int row = jdbcTemplate.update(sql, new Object[]{invoiceItemsBEAN.getItemId(), invoiceItemsBEAN.getItemName(),
            invoiceItemsBEAN.getDescription(), invoiceItemsBEAN.getRate(), invoiceItemsBEAN.getUnit(),
            invoiceItemsBEAN.getTaxId(), invoiceItemsBEAN.getCategory(), invoiceItemsBEAN.getIsThisMandatory(),
            invoiceItemsBEAN.getMandatoryFor(), invoiceItemsBEAN.getIsDelete()
        });
        return (row > 0);
    }

    @Override
    public boolean updateInvoiceMasterItems(InvoiceItemsBEAN invoiceItemsBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE account_master_items_tbl\n"
                + " SET item_name =  ?,\n"
                + " description =  ?,\n"
                + " rate =  ?,\n"
                + " unit =  ?,\n"
                + " tax =  ?,\n"
                + " category =  ?,\n"
                + " is_this_mandatory =  ?,\n"
                + " mandatory_for =  ?,\n"
                + " is_delete =  ?\n"
                + " WHERE\n"
                + "	item_id =  ?";
        int row = jdbcTemplate.update(sql, new Object[]{invoiceItemsBEAN.getItemName(),
            invoiceItemsBEAN.getDescription(), invoiceItemsBEAN.getRate(), invoiceItemsBEAN.getUnit(),
            invoiceItemsBEAN.getTaxId(), invoiceItemsBEAN.getCategory(), invoiceItemsBEAN.getIsThisMandatory(),
            invoiceItemsBEAN.getMandatoryFor(), invoiceItemsBEAN.getIsDelete(), invoiceItemsBEAN.getItemId()
        });
        return (row > 0);
    }

    @Override
    public boolean deleteInvoiceMasterItems(String lineId) {

        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM account_master_items_tbl\n"
                + " WHERE\n"
                + " item_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{lineId});
        return (row > 0);

    }

    @Override
    public ObservableList<InvoiceItemsBEAN> retrieveInvoiceMasterItems() {
        ObservableList<InvoiceItemsBEAN> listContacts = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM account_master_items_tbl ";
        List<InvoiceItemsBEAN> temp = jdbcTemplate.query(query, new Object[]{}, new ItemMasterMapper());
        listContacts.addAll(temp);
        return listContacts;
    }

    public class ItemMasterMapper implements RowMapper<InvoiceItemsBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public InvoiceItemsBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            InvoiceItemsBEAN invoiceItemsBEAN = new InvoiceItemsBEAN();
            invoiceItemsBEAN.setItemId(rs.getString("item_id"));
            invoiceItemsBEAN.setItemName(rs.getString("item_name"));
            invoiceItemsBEAN.setDescription(rs.getString("description"));
            invoiceItemsBEAN.setRate(rs.getString("rate"));
            invoiceItemsBEAN.setUnit(rs.getString("unit"));
            invoiceItemsBEAN.setTaxId(rs.getString("tax"));
            invoiceItemsBEAN.setCategory(rs.getString("category"));
            invoiceItemsBEAN.setIsThisMandatory(rs.getString("is_this_mandatory"));
            invoiceItemsBEAN.setMandatoryFor(rs.getString("mandatory_for"));
            invoiceItemsBEAN.setIsDelete(rs.getString("is_delete"));
            return invoiceItemsBEAN;
        }

    }
}
