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
package com.zs.ina.registration.documents;

import com.zs.ina.registration.documents.dao.DocumentVerifyBEAN;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLSingleLogDocumentVerificationController implements Initializable {

    @FXML
    private Label lblMailTo;
    @FXML
    private Label lblSubject;
    @FXML
    private Button btnResend;
    @FXML
    private Label lblContent;

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
     * @param documentVerifyBEAN
     */
    public void passLogDocumentDetails(DocumentVerifyBEAN documentVerifyBEAN) {
        if (documentVerifyBEAN != null) {
            lblMailTo.setText(documentVerifyBEAN.getMailTo());
            lblSubject.setText(documentVerifyBEAN.getSubject());
            lblContent.setText(documentVerifyBEAN.getContent());
        }

    }

}
