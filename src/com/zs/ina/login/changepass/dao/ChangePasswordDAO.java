/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.login.changepass.dao;

import com.zs.ina.context.Context;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author zoft
 */
public class ChangePasswordDAO {
    Connection con=null;
    Statement stmt=null;
    ResultSet rs=null;
    PreparedStatement ps=null;
    int raw;

    /**
     *
     * @param passwordPOJO
     * @return
     */
    public int insert(ChangePasswordPOJO passwordPOJO) {
      //  JOptionPane.showMessageDialog(null,Context.getInstance().currentProfile().getProfilePOJO().getUsername());
        if(passwordPOJO.getNewPass().equals(passwordPOJO.getRenewPass())){
            try {
                String query="update login_tbl set password=?,flag=? where user_name=?";
                con=DBPool.getInstance().getConn();
                ps=con.prepareStatement(query);
                ps.setString(1, passwordPOJO.getNewPass().trim());
                ps.setInt(2, 1);
                ps.setString(3, Context.getInstance().currentProfile().getProfilePOJO().getUsername());
                raw=ps.executeUpdate();
//                if(raw==1){
//                updateFlag();
//                }
            } catch (SQLException ex) {
                Logger.getLogger(ChangePasswordDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        
        }else{
        JOptionPane.showMessageDialog(null, "entered password is incorrect");
        }
        return raw;
    }

    private void updateFlag() {
       // String query="update flag"
    }

}
