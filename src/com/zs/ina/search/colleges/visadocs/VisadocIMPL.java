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
package com.zs.ina.search.colleges.visadocs;

import com.zs.ina.common.constants.TableNames;
import com.zs.ina.search.colleges.dao.EduProviderIMPL;
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
public class VisadocIMPL implements VisadocDAO {

    Logger logger = Logger.getLogger(EduProviderIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate mySqlJdbcTemplate;

    @Override
    public int insertVisaDocuments(VisadocBEAN visadocBEAN) {
        int row = 0;
        String sqls = "INSERT INTO " + TableNames.TBL_SR_EDU_VISADOCS + " (\n"
                + "	row_id,\n"
                + "	visadoc_id,\n"
                + "	college_id,\n"
                + "	description\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?\n"
                + "	);";
        row = mySqlJdbcTemplate.update(sqls, new Object[]{visadocBEAN.getRowId(),
            visadocBEAN.getVisadocId(),
            visadocBEAN.getCollegeId(),
            visadocBEAN.getDescription()
        });
        return row;

    }

    @Override
    public int updateVisaDocuments(VisadocBEAN visadocBEAN) {
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        int row = 0;

        String sql = "UPDATE " + TableNames.TBL_SR_EDU_VISADOCS + "\n"
                + "SET college_id = ?,visadoc_id = ?,description = ?\n"
                + "WHERE\n"
                + "	row_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{visadocBEAN.getCollegeId(),
            visadocBEAN.getVisadocId(),
            visadocBEAN.getDescription(),
            visadocBEAN.getRowId()
        });

        return row;

    }

    @Override
    public ObservableList<VisadocBEAN> retrieveVisaDocuments(String id) {
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        List<VisadocBEAN> listTemp = new ArrayList<>();
        ObservableList<VisadocBEAN> listVisaDocs = FXCollections.observableArrayList();
        String query = "SELECT * FROM " + TableNames.TBL_SR_EDU_VISADOCS + " WHERE college_id = '" + id + "'";
        listTemp = mySqlJdbcTemplate.query(query, new VisaDocsMapperTemplates());
        listVisaDocs.addAll(listTemp);
        return listVisaDocs;

    }

    @Override
    public boolean deleteVisaDocument(String id) {
        int row = 0;
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM  " + TableNames.TBL_SR_EDU_VISADOCS + "\n"
                + " WHERE\n"
                + "	row_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{id});
        return (row > 0) ? true : false;

    }

    public class VisaDocsMapperTemplates implements RowMapper<VisadocBEAN> {

        @Override
        public VisadocBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            VisadocBEAN visadocBEAN = new VisadocBEAN();
            visadocBEAN.setRowId(rs.getString("row_id"));
            visadocBEAN.setVisadocId(rs.getString("visadoc_id"));
            visadocBEAN.setCollegeId(rs.getString("college_id"));
            visadocBEAN.setDescription(rs.getString("description"));
            return visadocBEAN;
        }

    }

}
