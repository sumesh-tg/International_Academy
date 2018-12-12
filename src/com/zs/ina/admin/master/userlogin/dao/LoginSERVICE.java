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
package com.zs.ina.admin.master.userlogin.dao;

import com.zs.ina.notice.dao.BranchPOJO;
import javafx.collections.ObservableList;

/**
 *
 * @author 100035
 */
public class LoginSERVICE {

    LoginDAO loginDAO;

    /**
     *
     * @param loginDAO
     */
    public LoginSERVICE(LoginIMPL loginDAO) {
        this.loginDAO = loginDAO;

    }

    /**
     *
     * @return
     */
    public ObservableList<BranchPOJO> retrieveBranches() {
        return loginDAO.retrieveBranches();
    }

    /**
     *
     * @return
     */
    public ObservableList<RolePOJO> retrieveRoles() {
        return loginDAO.retrieveRoles();
    }

    /**
     *
     * @param privilages
     * @return
     */
    public ObservableList<RolePOJO> retrieveRolesUsingPrivilages(String privilages) {
        return loginDAO.retrieveRolesUsingPrivilages(privilages);
    }

    /**
     *
     * @param loginBEAN
     * @return
     */
    public int insertLogin(LoginBEAN loginBEAN) {
        return loginDAO.insertLogin(loginBEAN);
    }

    /**
     *
     * @param loginBEAN
     * @return
     */
    public int updateLogin(LoginBEAN loginBEAN) {
        return loginDAO.updateLogin(loginBEAN);
    }

    /**
     *
     * @param loginId
     * @return
     */
    public int deleteLogin(String loginId) {
        return loginDAO.deleteLogin(loginId);
    }

    /**
     *
     * @return
     */
    public ObservableList<LoginBEAN> retrieveLogin() {
        return loginDAO.retrieveLogin();
    }

    /**
     *
     * @param loginBEAN
     * @return
     */
    public int resetPassword(LoginBEAN loginBEAN) {
        return loginDAO.resetPassword(loginBEAN);
    }

    /**
     *
     * @param username
     * @return
     */
    public LoginBEAN retrieveSingleUserPrivilage(String username) {
        return loginDAO.retrieveSingleUserPrivilage(username);
    }

}
