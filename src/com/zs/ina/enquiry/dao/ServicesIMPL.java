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
package com.zs.ina.enquiry.dao;

import com.zs.ina.common.constants.TableNames;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author 100018
 */
public class ServicesIMPL implements ServicesDAO {

    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;

    /**
     *
     * @param enquiryId
     * @return
     */
    @Override
    public List<ServicesBEAN> getAllServices(String enquiryId) {
        List<ServicesBEAN> servicesBEANList = new ArrayList<>();
        try {
            con = DBPool.getInstance().getConn();
            String query = "SELECT\n"
                    + "study_pgm_id as id,	study AS service,\n"
                    + "	country,\n"
                    + "	location\n"
                    + "FROM\n"
                    + "	"+TableNames.TABLE_ENQ_ASMNT_STUDY+" WHERE enquiry_id='" + enquiryId + "'\n"
                    + "UNION ALL\n"
                    + "SELECT\n"
                    + "work_id as id,	work AS service,\n"
                    + "	country,\n"
                    + "	location\n"
                    + "FROM\n"
                    + "	" + TableNames.TABLE_ENQ_ASMNT_WORK + " WHERE enquiry_id='" + enquiryId + "'\n"
                    + "UNION ALL\n"
                    + "SELECT\n"
                    + "migrate_id as id,\n"
                    + "	migrate AS service,\n"
                    + "	country,  location as location\n"
                    + "FROM\n"
                    + "	" + TableNames.TABLE_ENQ_ASMNT_MIGRATION + " WHERE enquiry_id='" + enquiryId + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            System.out.println("GET ALL SERVICES query ::"+query);
            while (rs.next()) {
                ServicesBEAN servicesBEAN = new ServicesBEAN();
                servicesBEAN.setId(rs.getString("id"));
                servicesBEAN.setService(rs.getString("service"));
                servicesBEAN.setCountry(rs.getString("country"));
                servicesBEAN.setLocation(rs.getString("location"));
                servicesBEANList.add(servicesBEAN);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return servicesBEANList;
    }

    /**
     *
     * @param enquiryId
     * @return
     */
    @Override
    public boolean deleteStudy(String enquiryId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param enquiryId
     * @return
     */
    @Override
    public boolean deleteWork(String enquiryId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param enquiryId
     * @return
     */
    @Override
    public boolean deleteMigrate(String enquiryId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param enquiryId
     * @return
     */
    @Override
    public boolean updateStudy(String enquiryId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param enquiryId
     * @return
     */
    @Override
    public boolean updateWork(String enquiryId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param enquiryId
     * @return
     */
    @Override
    public boolean updateMigration(String enquiryId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
