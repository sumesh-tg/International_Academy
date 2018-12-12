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
package com.zs.ina.notice.dao;

/**
 *
 * @author 100035
 */
public class BranchPOJO {

    String branchId;
    String branch;

    /**
     *
     * @return
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     *
     * @param branchId
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    /**
     *
     * @return
     */
    public String getBranch() {
        return branch;
    }

    /**
     *
     * @param branch
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     *
     */
    public BranchPOJO() {
    }

    /**
     *
     * @param branchId
     * @param branch
     */
    public BranchPOJO(String branchId, String branch) {
        this.branchId = branchId;
        this.branch = branch;
    }

    @Override
    public String toString() {
        return branch;
    }

}
