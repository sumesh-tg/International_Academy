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
package com.zs.ina.search.master.intake.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100035
 */
public class IntakeBEAN {

    private final StringProperty intakeId = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getIntakeId() {
        return intakeId.get();
    }

    /**
     *
     * @param value
     */
    public void setIntakeId(String value) {
        intakeId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty intakeIdProperty() {
        return intakeId;
    }
    private final StringProperty intake = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getIntake() {
        return intake.get();
    }

    /**
     *
     * @param value
     */
    public void setIntake(String value) {
        intake.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty intakeProperty() {
        return intake;
    }
    
}
