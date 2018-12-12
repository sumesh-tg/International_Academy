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
package com.zs.ina.admin.master.relationship.dao;

import com.zs.ina.admin.master.qualificationlevel.dao.LevelBEAN;
import com.zs.ina.admin.master.stdisdcodes.dao.StdCodesBEAN;
import javafx.collections.ObservableList;

/**
 *
 * @author 100035
 */
public interface RelationDAO {

    /**
     *
     * @param relationBEAN
     * @return
     */
    public int insertRelationship(RelationBEAN relationBEAN);

    /**
     *
     * @param relationBEAN
     * @return
     */
    public int updateRelationship(RelationBEAN relationBEAN);

    /**
     *
     * @param relationBEAN
     * @return
     */
    public int deleteRelationship(RelationBEAN relationBEAN);

    /**
     *
     * @return
     */
    public ObservableList<RelationBEAN> retrieveRelationships();
}
