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
package com.zs.ina.admin.master.emailtemplate.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100035
 */
public class EmailTempBEAN {

    private final StringProperty templateId = new SimpleStringProperty();
    private final StringProperty head = new SimpleStringProperty();
    private final StringProperty subhead = new SimpleStringProperty();
    private final StringProperty subject = new SimpleStringProperty();
    private final StringProperty fromMail = new SimpleStringProperty();
    private final StringProperty contents = new SimpleStringProperty();
    private final StringProperty salutation = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getSalutation() {
        return salutation.get();
    }

    /**
     *
     * @param value
     */
    public void setSalutation(String value) {
        salutation.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty salutationProperty() {
        return salutation;
    }

    /**
     *
     * @return
     */
    public String getSubhead() {
        return subhead.get();
    }

    /**
     *
     * @param value
     */
    public void setSubhead(String value) {
        subhead.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty subheadProperty() {
        return subhead;
    }

    /**
     *
     * @return
     */
    public String getContents() {
        return contents.get();
    }

    /**
     *
     * @param value
     */
    public void setContents(String value) {
        contents.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty contentsProperty() {
        return contents;
    }

    /**
     *
     * @return
     */
    public String getFromMail() {
        return fromMail.get();
    }

    /**
     *
     * @param value
     */
    public void setFromMail(String value) {
        fromMail.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty fromMailProperty() {
        return fromMail;
    }

    /**
     *
     * @return
     */
    public String getSubject() {
        return subject.get();
    }

    /**
     *
     * @param value
     */
    public void setSubject(String value) {
        subject.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty subjectProperty() {
        return subject;
    }

    /**
     *
     * @return
     */
    public String getHead() {
        return head.get();
    }

    /**
     *
     * @param value
     */
    public void setHead(String value) {
        head.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty headProperty() {
        return head;
    }

    /**
     *
     * @return
     */
    public String getTemplateId() {
        return templateId.get();
    }

    /**
     *
     * @param value
     */
    public void setTemplateId(String value) {
        templateId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty templateIdProperty() {
        return templateId;
    }

}
