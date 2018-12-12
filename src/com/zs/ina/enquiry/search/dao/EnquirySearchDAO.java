/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.enquiry.search.dao;

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
 * @author user
 */
public class EnquirySearchDAO {

    /**
     *
     * @param branch
     * @return
     */
    public static List<CounselorWorkCount> getCounselorWorkDetails(String branch) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        con = DBPool.getInstance().getConn();
        List<CounselorWorkCount> resList = new ArrayList<>();
        String sql = "select upper((SELECT fullname from employee_role_branch WHERE user_name =enquiry_assigning)) as counselor,\n"
                + "			 sum(case when completion_flag = 1 then 1 else 0 end) as 'Assessment Completed',\n"
                + "       sum(case when IFNULL(completion_flag,0) = 0 then 1 else 0 end) as 'Assessment Pending'\n"
                + "from enquiry_details \n"
                + "WHERE branch='" + branch + "' AND enquiry_assigning <>'Not Assigned'\n"
                + "group by enquiry_assigning";
        String query = " SELECT ed.enquiry_assigning,count(ed1.read_flag)"
                + " as Completed,count(ed2.read_flag) as"
                + " Pending from " + TableNames.TABLE_ENQ_DETAILS + " ed LEFT JOIN " + TableNames.TABLE_ENQ_DETAILS + " ed1 on"
                + " (ed.enquiry_id=ed1.enquiry_id and ed1.read_flag=1)"
                + "LEFT JOIN " + TableNames.TABLE_ENQ_DETAILS + " ed2 on "
                + "(ed.enquiry_id=ed2.enquiry_id and ed2.read_flag=0) "
                + " where ed.staff_branch='" + branch + "' and ed.enquiry_assigning<>''  group by ed.enquiry_assigning ";
        System.out.println("test :: " + sql);
        System.out.println("test :: " + query);
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                i = i + 1;
                CounselorWorkCount counselorWorkCount = new CounselorWorkCount();
                counselorWorkCount.setCname(rs.getString(1));
                counselorWorkCount.setAssesed(rs.getString(2));
                counselorWorkCount.setPendingCount(rs.getString(3));
                resList.add(counselorWorkCount);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return resList;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //   List<CounselorDetailsBEAN> resultList = getPerspectiveAppoinmentPending("kollam", "a");
    }
}
