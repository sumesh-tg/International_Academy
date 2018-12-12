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
public interface LoginDAO {

    /**
     *
     * @param loginBEAN
     * @return
     */
    public int insertLogin(LoginBEAN loginBEAN);

    /**
     *
     * @param loginId
     * @return
     */
    public int deleteLogin(String loginId);

    /**
     *
     * @return
     */
    public ObservableList<LoginBEAN> retrieveLogin();

    /**
     *
     * @return
     */
    public ObservableList<BranchPOJO> retrieveBranches();

    /**
     *
     * @return
     */
    public ObservableList<RolePOJO> retrieveRoles();

    /**
     *
     * @param privilages
     * @return
     */
    public ObservableList<RolePOJO> retrieveRolesUsingPrivilages(String privilages);

    /**
     *
     * @param LoginBEAN
     * @return
     */
    public int updateLogin(LoginBEAN LoginBEAN);

    /**
     *
     * @param loginBEAN
     * @return
     */
    public int resetPassword(LoginBEAN loginBEAN);
    
    /**
     *
     * @param username
     * @return
     */
    public LoginBEAN retrieveSingleUserPrivilage(String username); 

}
