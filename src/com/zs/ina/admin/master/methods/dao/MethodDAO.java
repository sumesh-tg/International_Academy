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
package com.zs.ina.admin.master.methods.dao;

import com.zs.ina.admin.master.sources.dao.SourceBEAN;
import com.zs.ina.admin.master.sources.dao.SourcePOJO;
import javafx.collections.ObservableList;

/**
 *
 * @author 100035
 */
public interface MethodDAO {

    /**
     *
     * @param methodBEAN
     * @return
     */
    public int insertMethod(MethodBEAN methodBEAN);

    /**
     *
     * @param methodBEAN
     * @return
     */
    public int updateMethod(MethodBEAN methodBEAN);

    /**
     *
     * @param methodBEAN
     * @return
     */
    public int updateMethodType(MethodBEAN methodBEAN);

    /**
     *
     * @return
     */
    public ObservableList<MethodBEAN> retrieveMethods();

    /**
     *
     * @return
     */
    public ObservableList<MethodPOJO> retrieveMethodsOnly();
}
