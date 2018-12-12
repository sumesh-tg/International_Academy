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

import javafx.collections.ObservableList;

/**
 *
 * @author 100035
 */
public class DocumentSERVICE {

    DocumentDAO documentDAO = new DocumentIMPL();

    /**
     *
     * @param documentDAO
     */
    public DocumentSERVICE(DocumentIMPL documentDAO) {
        this.documentDAO = documentDAO;
    }

    /**
     *
     * @param documentBEAN
     * @return
     */
    public int insertDocument(DocumentBEAN documentBEAN) {
        return documentDAO.insertDocument(documentBEAN);
    }

    /**
     *
     * @return
     */
    public ObservableList<DocumentBEAN> retrieveDocuments() {
        return documentDAO.retrieveDocuments();
    }

    /**
     *
     * @param documentBEAN
     * @return
     */
    public int updateDocument(DocumentBEAN documentBEAN) {
        return documentDAO.updateDocument(documentBEAN);
    }

    /**
     *
     * @param programReqd
     * @return
     */
    public ObservableList<DocumentBEAN> retrieveAssignedDocuments(String programReqd) {
        return documentDAO.retrieveAssignedDocuments(programReqd);
    }

    /**
     *
     * @param masterDocumentsList
     * @param programReqd
     * @return
     */
    public int insertAssignedDocument(ObservableList<DocumentBEAN> masterDocumentsList, String programReqd) {
      return documentDAO.insertAssignedDocument(masterDocumentsList,programReqd);
    }

    /**
     *
     * @param programReqd
     * @return
     */
    public int deleteAssignedDocument(String programReqd) {
        return documentDAO.deleteAssignedDocument(programReqd);
    }
}
