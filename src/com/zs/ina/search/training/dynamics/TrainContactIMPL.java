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
package com.zs.ina.search.training.dynamics;

import com.zs.ina.common.constants.TableNames;
import com.zs.ina.search.colleges.contacts.EduContactIMPL;
import com.zs.ina.search.common.bean.ContactsBEAN;
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
public class TrainContactIMPL implements TrainContactDAO {

    Logger logger = Logger.getLogger(TrainContactIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate mySqlJdbcTemplate;

    @Override
    public boolean insertContacts(ContactsBEAN contactsBEAN) {
        int row = 0;
        String sqls = "INSERT INTO " + TableNames.TBL_TR_HEAD_CONTACT + " (\n"
                + "	contact_id,\n"
                + "	training_id,\n"
                + "	contact_name,\n"
                + "	department,\n"
                + "	std_isd,\n"
                + "	phone_no,\n"
                + "	email\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?\n"
                + "	);";
        row = mySqlJdbcTemplate.update(sqls, new Object[]{contactsBEAN.getRowId(),
            contactsBEAN.getTrainingId(),
            contactsBEAN.getContactName(),
            contactsBEAN.getDepartment(),
            contactsBEAN.getStdIsd(),
            contactsBEAN.getPhone(),
            contactsBEAN.getEmail()
        });
        return (row > 0) ? true : false;

    }

    @Override
    public boolean updateContacts(ContactsBEAN eduContactBEAN) {
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        int row = 0;

        String sql = "UPDATE " + TableNames.TBL_TR_HEAD_CONTACT + "\n"
                + "SET training_id = ?,contact_name = ?,department = ?,std_isd = ?,phone_no = ?,email = ?\n"
                + "WHERE\n"
                + "	contact_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{eduContactBEAN.getCollegeId(),
            eduContactBEAN.getContactName(),
            eduContactBEAN.getDepartment(),
            eduContactBEAN.getStdIsd(),
            eduContactBEAN.getPhone(),
            eduContactBEAN.getEmail(),
            eduContactBEAN.getRowId()

        });

        return (row > 0) ? true : false;

    }

    @Override
    public boolean deleteContactDetails(String id) {
        int row = 0;
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM  " + TableNames.TBL_TR_HEAD_CONTACT + "\n"
                + " WHERE\n"
                + "	contact_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{id});
        return (row > 0) ? true : false;

    }

    @Override
    public ObservableList<ContactsBEAN> retrieveContacts(String id) {

        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        List<ContactsBEAN> listTemp = new ArrayList<>();
        ObservableList<ContactsBEAN> listContacts = FXCollections.observableArrayList();
        String query = "SELECT * FROM " + TableNames.TBL_TR_HEAD_CONTACT + " WHERE training_id = '" + id + "'";
        listTemp = mySqlJdbcTemplate.query(query, new ContactsMapperTemplates());
        listContacts.addAll(listTemp);
        return listContacts;
    }

    public class ContactsMapperTemplates implements RowMapper<ContactsBEAN> {

        @Override
        public ContactsBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            ContactsBEAN contactsBEAN = new ContactsBEAN();
            contactsBEAN.setRowId(rs.getString("contact_id"));
            contactsBEAN.setTrainingId(rs.getString("training_id"));
            contactsBEAN.setContactName(rs.getString("contact_name"));
            contactsBEAN.setDepartment(rs.getString("department"));
            contactsBEAN.setStdIsd(rs.getString("std_isd"));
            contactsBEAN.setPhone(rs.getString("phone_no"));
            contactsBEAN.setEmail(rs.getString("email"));
            return contactsBEAN;
        }

    }
}
