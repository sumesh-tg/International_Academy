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
package com.zs.ina.admin.master.age;

import com.zs.ina.admin.master.age.dao.AgeBEAN;
import com.zs.ina.admin.master.age.dao.AgeIMPL;
import com.zs.ina.admin.master.age.dao.AgeService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class FXMLAddAgeController implements Initializable {

    @FXML
    private HBox hboxQualificnLevel;
    @FXML
    private TextField txtAge;
    @FXML
    private TableView<AgeBEAN> tblAge;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnResetAge;
    @FXML
    private TableColumn<AgeBEAN, AgeBEAN> colAge;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnResetAge.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AgeService.resetAge();
                loadTableData();
            }
        });
        loadTableData();
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (txtAge.getText() == null || txtAge.getText().equalsIgnoreCase("")) {

                } else {
                    AgeIMPL.insertAge(txtAge.getText());
                    loadTableData();
                    txtAge.clear();
                }
            }
        });
        txtAge.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!(newValue.matches("[0-9]*")) || newValue.length() > 3) {
                    txtAge.setText(oldValue);
                } else {
                    txtAge.setText(newValue);

                }

            }
        });
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtAge.clear();
            }
        });
    }

    private void loadTableData() {
        ObservableList<AgeBEAN> ageBEANs = AgeService.retrieveAges();
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        tblAge.setItems(ageBEANs);

    }

}
