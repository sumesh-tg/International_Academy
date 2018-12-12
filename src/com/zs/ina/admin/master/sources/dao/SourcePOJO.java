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
package com.zs.ina.admin.master.sources.dao;

/**
 *
 * @author 100035
 */
public class SourcePOJO {

    String sourceId;
    String source;

    /**
     *
     * @return
     */
    public String getSourceId() {
        return sourceId;
    }

    /**
     *
     * @param sourceId
     */
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    /**
     *
     * @return
     */
    public String getSource() {
        return source;
    }

    /**
     *
     * @param source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     *
     */
    public SourcePOJO() {
    }

    /**
     *
     * @param sourceId
     * @param source
     */
    public SourcePOJO(String sourceId, String source) {
        this.sourceId = sourceId;
        this.source = source;
    }

    @Override
    public String toString() {
        return source;
    }

}
