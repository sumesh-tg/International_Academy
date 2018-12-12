/*
 * Copyright (C) 2016 100018
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
package com.zs.ina.assesment.relative.dao;

import com.zs.ina.assesment.dao.ProgramSuggestedRequiredDAO;
import com.zs.ina.assesment.plus2.dao.Assesmentplus2DAO;
import com.zs.ina.common.GlobalClassDAO;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.common.constants.TableNames;
import com.zs.ina.context.Context;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author 100018
 */
public class RelativeIMPL implements RelativeDAO {

    static Logger logger = Logger.getLogger(RelativeIMPL.class);

    /**
     *
     * @param relativePOJO
     * @return
     */
    @Override
    public int insertRelativeDetails(RelativeBEAN relativePOJO) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        relativePOJO.setRowId("re_" + UiiDGenerator.getUIID8());
        try {
            String sql = "INSERT INTO " + TableNames.TABLE_ENQ_ASMNT_RELATIVE + " (\n"
                    + "	relative_id,\n"
                    + "	country_relative,\n"
                    + "	relationship,\n"
                    + "	enquiry_id,\n"
                    + "	created_user,\n"
                    + "	created_date,\n"
                    + "	updated_date,\n"
                    + "	updated_user,\n"
                    + "	latest_flag\n"
                    + ")\n"
                    + "VALUES\n"
                    + "	(\n"
                    + "		'" + relativePOJO.getRowId() + "',\n"
                    + "		'" + relativePOJO.getCountryOfRelative() + "',\n"
                    + "		'" + relativePOJO.getRelationship() + "',\n"
                    + "		'" + relativePOJO.getEnquiryId() + "',\n"
                    + "		'" + Context.getInstance().currentProfile().getProfilePOJO().getUsername() + "',\n"
                    + "		NOW(),\n"
                    + "		NULL,\n"
                    + "		NULL,\n"
                    + "		'y'\n"
                    + "	);";
            stmt = con.createStatement();
            row = stmt.executeUpdate(sql);
            if (row > 0) {
                if (row > 0) {
                    GlobalClassDAO.updateLatestFlags(relativePOJO.getEnquiryId(), TableNames.TABLE_ENQ_ASMNT_RELATIVE, "n", " relative_id NOT IN('" + relativePOJO.getRowId() + "')");
                }
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param rowId
     * @return
     */
    @Override
    public int deleteRelativeDetails(String rowId) {
        String _isLatestFlag = GlobalClassDAO.checkCurrentEnquiryIdHaveLatestFlag(rowId, TableNames.TABLE_ENQ_ASMNT_RELATIVE, "relative_id");
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String query = "DELETE FROM " + TableNames.TABLE_ENQ_ASMNT_RELATIVE + " WHERE relative_id = '" + rowId + "' ";
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
            if (row == 1) {
                if (_isLatestFlag != null) {
                    GlobalClassDAO.updateLatestflagOnDelete(_isLatestFlag, TableNames.TABLE_ENQ_ASMNT_RELATIVE);
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param relativePOJO
     * @return
     */
    @Override
    public int updateRelativeDetails(RelativeBEAN relativePOJO) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {

            String sql = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_RELATIVE + "\n"
                    + "SET country_relative = '" + relativePOJO.getCountryOfRelative() + "',\n"
                    + " relationship = '" + relativePOJO.getRelationship() + "',\n"
                    + " updated_date = NOW(),\n"
                    + " updated_user = '" + Context.getInstance().currentProfile().getProfilePOJO().getUsername() + "'\n"
                    + "WHERE\n"
                    + "	relative_id = '" + relativePOJO.getRowId() + "'";
            stmt = con.createStatement();
            row = stmt.executeUpdate(sql);

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param enquiryId
     * @return
     */
    @Override
    public List<RelativeBEAN> retrieveRelativeDetails(String enquiryId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<RelativeBEAN> listRelativeDetails = new ArrayList<RelativeBEAN>();
        try {
            String query = "SELECT * FROM " + TableNames.TABLE_ENQ_ASMNT_RELATIVE;
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                RelativeBEAN relativePOJO = new RelativeBEAN();
                relativePOJO.setRowId(rs.getString("relative_id"));
                relativePOJO.setCountryOfRelative(rs.getString("country_relative"));
                relativePOJO.setRelationship(rs.getString("relationship"));
                relativePOJO.setEnquiryId(rs.getString("enquiry_id"));
                listRelativeDetails.add(relativePOJO);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return listRelativeDetails;
    }

}
