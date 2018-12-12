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
package com.zs.ina.admin.master.documents;

import com.zs.ina.admin.master.documents.dao.DocumentBEAN;
import com.zs.ina.admin.master.payment.dao.PaymentBEAN;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLSingleDocumentController implements Initializable {

    @FXML
    private CheckBox chkDocument;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     *
     * @param documentBEAN
     * @param masterDocumentsList
     */
    public void passDocumentDetails(DocumentBEAN documentBEAN, ObservableList<DocumentBEAN> masterDocumentsList) {

        chkDocument.setText(documentBEAN.getDocument());
        chkDocument.selectedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    chkDocument.setSelected(true);
                    if (!masterDocumentsList.contains(documentBEAN)) {
                    masterDocumentsList.add(documentBEAN);
                    }
                } else {
                    chkDocument.setSelected(false);
                    masterDocumentsList.remove(documentBEAN);
                }
            }
        });
        if (documentBEAN.getDocumentId() != null && documentBEAN.getDocument_id() != null) {
            if (documentBEAN.getDocumentId().equalsIgnoreCase(documentBEAN.getDocument_id())) {
                chkDocument.setSelected(true);

            }
        }

    }

}