/*
 * Copyright ZoftSolutions(C) 2016 SUMESH T.G <ZoftSolutions>
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
 *  Developed by ZoftSolutions (2015) Company.
 */
package com.zs.ina.enquiry.search.editing;

import com.zs.ina.admin.master.retrieve.RetrieveStaticMasterDAO;
import com.zs.ina.assesment.dao.AssesmentDAO;
import com.zs.ina.context.Context;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.enquiry.search.editing.dao.InlineEditingDAO;
import com.zs.ina.enquiry.search.editing.dao.InlineEditingIMPL;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class FXMLTrainingBatchController implements Initializable {

    @FXML
    private ComboBox<String> cmbBatch;
    @FXML
    private Button btnSave;
    CounselorDetailsBEAN counselorDetailsBEAN;
    InlineEditingDAO editingDAO = new InlineEditingIMPL();
    ObservableList obsListBatches = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obsListBatches = RetrieveStaticMasterDAO.getBatches();
        cmbBatch.setItems(obsListBatches);
        counselorDetailsBEAN = Context.getInstance().currentProfile().getCounselorDetailsBEAN();
        Bindings.bindBidirectional(cmbBatch.valueProperty(), counselorDetailsBEAN.batchProperty());
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (cmbBatch.getSelectionModel().getSelectedIndex() >= 0) {
                    editingDAO.editTrainingRequired(counselorDetailsBEAN);
                    Context.getInstance().currentProfile().getPopOver().hide();
                }
            }
        });
    }

}
