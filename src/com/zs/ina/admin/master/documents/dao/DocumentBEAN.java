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
package com.zs.ina.admin.master.documents.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100035
 */
public class DocumentBEAN {

    private final StringProperty documentId = new SimpleStringProperty();
    private final StringProperty document = new SimpleStringProperty();
    
    
    private final StringProperty program = new SimpleStringProperty();
    private final StringProperty document_id = new SimpleStringProperty();
    private final StringProperty assignId = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getAssignId() {
        return assignId.get();
    }

    /**
     *
     * @param value
     */
    public void setAssignId(String value) {
        assignId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty assignIdProperty() {
        return assignId;
    }
    
    /**
     *
     * @return
     */
    public String getDocument_id() {
        return document_id.get();
    }

    /**
     *
     * @param value
     */
    public void setDocument_id(String value) {
        document_id.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty document_idProperty() {
        return document_id;
    }
    
    /**
     *
     * @return
     */
    public String getProgram() {
        return program.get();
    }

    /**
     *
     * @param value
     */
    public void setProgram(String value) {
        program.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty programProperty() {
        return program;
    }
    
    /**
     *
     * @return
     */
    public String getDocument() {
        return document.get();
    }

    /**
     *
     * @param value
     */
    public void setDocument(String value) {
        document.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty documentProperty() {
        return document;
    }

    /**
     *
     * @return
     */
    public String getDocumentId() {
        return documentId.get();
    }

    /**
     *
     * @param value
     */
    public void setDocumentId(String value) {
        documentId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty documentIdProperty() {
        return documentId;
    }

    @Override
    public String toString() {
        return "DocumentBEAN{" + "documentId=" + documentId + ", document=" + document + '}';
    }
    
}
