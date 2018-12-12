/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.enquiry.empstatus.dao;

import com.zs.ina.context.Context;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author zoft
 */
public class EmployeeStatusDAO {

    /**
     *
     * @param selectedBranch
     * @return
     */
    public static List<EmpStatusBEAN> getEmpStatus(String selectedBranch) {
        int row = 0;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<EmpStatusBEAN> empStatusList = new ArrayList<>();
        try {
            String query = "SELECT\n"
                    + "	DISTINCT UPPER(CONCAT(ld.first_name,\" \",ld.last_name)) as staff_name ,ld.phone,ld.ext_number,(CASE WHEN r.role = 'ROLE_ADMIN' THEN 'Administrator'\n"
                    + "WHEN r.role = 'ROLE_COUNSELOR' THEN 'Counsellor'\n"
                    + "WHEN r.role = 'ROLE_OFFICE' THEN 'Front Office Staff'\n"
                    + " ELSE r.role  END) as role,IFNULL((DATEDIFF(CURDATE() ,la.attempt_date)),2) as attendance,b.phone_number\n"
                    + "FROM\n"
                    + "	login_details_tbl ld\n"
                    + "LEFT JOIN login_tbl l ON (\n"
                    + "	ld.login_id = l.login_id\n"
                    + ")\n"
                    + "LEFT JOIN role_tbl r ON(\n"
                    + "r.role_id=l.role_id\n"
                    + ")\n"
                    + "LEFT JOIN login_auditor la ON(\n"
                    + "la.user_id=ld.login_id \n"
                    + ")LEFT JOIN branch_tbl b ON(\n"
                    + "ld.branch_id=b.branch_id \n"
                    + ") WHERE ld.branch_id=b.branch_id and b.branch_name=? and l.user_name<>'" + Context.getInstance().currentProfile().getProfilePOJO().getUsername() + "' ORDER BY attendance asc";
            System.out.println("test attendance" + query);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, selectedBranch);
            rs = ps.executeQuery();
            while (rs.next()) {
                EmpStatusBEAN scheduleBEAN = new EmpStatusBEAN();
                //   scheduleBEAN.setId(rs.getString(1));
                scheduleBEAN.setName(rs.getString(1));
                scheduleBEAN.setExt_number(rs.getString(3));
                scheduleBEAN.setPosition(rs.getString("role"));
                if (rs.getString("attendance").equals("0")) {
                    scheduleBEAN.setStatus("Present");
                } else {
                    scheduleBEAN.setStatus("Absent");
                }
                scheduleBEAN.setContact_number(rs.getString("phone"));
                empStatusList.add(scheduleBEAN);

            }
            for (EmpStatusBEAN empStatusBEAN : empStatusList) {
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return empStatusList;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        List<EmpStatusBEAN> appointmentList = getEmpStatus("kollam");
        for (EmpStatusBEAN ed : appointmentList) {
            System.out.println("test ===>>> " + ed.toString());
        }
    }
}
