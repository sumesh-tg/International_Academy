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
package com.zs.ina.search.colleges.contacts;

import com.zs.ina.search.common.bean.ContactsBEAN;
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
public class EduContactIMPL implements EduContactDAO {

    Logger logger = Logger.getLogger(EduContactIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate mySqlJdbcTemplate;

    @Override
    public int insertContacts(ContactsBEAN eduContactBEAN) {
        int row = 0;
        String sqls = "INSERT INTO " + TableNames.TBL_SR_EDU_COLLEGE_CONTACTS + " (\n"
                + "	row_id,\n"
                + "	college_id,\n"
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
        row = mySqlJdbcTemplate.update(sqls, new Object[]{eduContactBEAN.getRowId(),
            eduContactBEAN.getCollegeId(),
            eduContactBEAN.getContactName(),
            eduContactBEAN.getDepartment(),
            eduContactBEAN.getStdIsd(),
            eduContactBEAN.getPhone(),
            eduContactBEAN.getEmail()
        });
        return row;

    }

    @Override
    public int updateContacts(ContactsBEAN eduContactBEAN) {
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        int row = 0;

        String sql = "UPDATE " + TableNames.TBL_SR_EDU_COLLEGE_CONTACTS + "\n"
                + "SET college_id = ?,contact_name = ?,department = ?,std_isd = ?,phone_no = ?,email = ?\n"
                + "WHERE\n"
                + "	row_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{eduContactBEAN.getCollegeId(),
            eduContactBEAN.getContactName(),
            eduContactBEAN.getDepartment(),
            eduContactBEAN.getStdIsd(),
            eduContactBEAN.getPhone(),
            eduContactBEAN.getEmail(),
            eduContactBEAN.getRowId()
            
        });

        return row;

    }
    
    
    @Override
    public boolean deleteContactDetails(String id) {
    
      int row = 0;
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM  "+ TableNames.TBL_SR_EDU_COLLEGE_CONTACTS + "\n"
                + " WHERE\n"
                + "	row_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{id});
        return (row > 0) ? true : false;
    }

    @Override
    public ObservableList<ContactsBEAN> retrieveContacts(String id) {

        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        List<ContactsBEAN> listTemp = new ArrayList<>();
        ObservableList<ContactsBEAN> listContacts = FXCollections.observableArrayList();
        String query = "SELECT * FROM " + TableNames.TBL_SR_EDU_COLLEGE_CONTACTS + " WHERE college_id = '" + id + "'";
        listTemp = mySqlJdbcTemplate.query(query, new ContactsMapperTemplates());
        listContacts.addAll(listTemp);
        return listContacts;
    }

    
    public class ContactsMapperTemplates implements RowMapper<ContactsBEAN> {

        @Override
        public ContactsBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            ContactsBEAN eduContactBEAN = new ContactsBEAN();
            eduContactBEAN.setRowId(rs.getString("row_id"));
            eduContactBEAN.setCollegeId(rs.getString("college_id"));
            eduContactBEAN.setContactName(rs.getString("contact_name"));
            eduContactBEAN.setDepartment(rs.getString("department"));
            eduContactBEAN.setStdIsd(rs.getString("std_isd"));
            eduContactBEAN.setPhone(rs.getString("phone_no"));
            eduContactBEAN.setEmail(rs.getString("email"));

            return eduContactBEAN;
        }

    }

}
