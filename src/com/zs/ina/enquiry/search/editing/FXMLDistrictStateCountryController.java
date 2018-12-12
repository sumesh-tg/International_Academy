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

import com.zs.ina.context.Context;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.admin.master.retrieve.MasterCountryStateDistrictDAO;
import com.zs.ina.enquiry.search.editing.dao.InlineEditingDAO;
import com.zs.ina.enquiry.search.editing.dao.InlineEditingIMPL;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
public class FXMLDistrictStateCountryController implements Initializable {

    @FXML
    private ComboBox<String> cmbDistrict;
    @FXML
    private ComboBox<String> cmbState;
    @FXML
    private ComboBox<String> cmbCountry;
    ObservableList state = FXCollections.observableArrayList();
    ObservableList country = FXCollections.observableArrayList();
    ObservableList districs = FXCollections.observableArrayList();
    CounselorDetailsBEAN counselorDetailsBEAN;
    @FXML
    private Button btnSave;
    InlineEditingDAO inlineEditingDAO = new InlineEditingIMPL();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        counselorDetailsBEAN = Context.getInstance().currentProfile().getCounselorDetailsBEAN();
        System.out.println("Date from report :: " + counselorDetailsBEAN.toString());
        initializeData();
        addComboEvents();
        Bindings.bindBidirectional(cmbCountry.valueProperty(), counselorDetailsBEAN.countryProperty());
        Bindings.bindBidirectional(cmbState.valueProperty(), counselorDetailsBEAN.stateProperty());
        Bindings.bindBidirectional(cmbDistrict.valueProperty(), counselorDetailsBEAN.districtProperty());

        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (cmbCountry.getSelectionModel().getSelectedIndex() >= 0 & cmbDistrict.getSelectionModel().getSelectedIndex() >= 0 & cmbState.getSelectionModel().getSelectedIndex() >= 0) {
                    inlineEditingDAO.updateDistrictStateCountry(counselorDetailsBEAN);
                }
                Context.getInstance().currentProfile().getPopOver().hide();
            }
        });
    }

    /**
     *
     */
    public void initializeData() {
        List<String> countrylist = MasterCountryStateDistrictDAO.getAllCountries();
        for (String s : countrylist) {
            country.add(s);
        }
        cmbCountry.setItems(country);

    }

    /**
     *
     */
    public void addComboEvents() {
        cmbState.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    districs.clear();
                    cmbDistrict.getItems().clear();
                    List<String> district = MasterCountryStateDistrictDAO.getAllDistricts(newValue);
                    for (String s : district) {
                        districs.add(s);
                    }
                    cmbDistrict.setItems(districs);
                    cmbDistrict.getSelectionModel().selectFirst();
                }
            }
        });
        cmbCountry.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {

                    state.clear();
                    cmbState.getItems().clear();
                    List<String> states = MasterCountryStateDistrictDAO.getAllStates(newValue);
                    for (String s : states) {
                        state.add(s);
                    }
                    cmbState.setItems(state);
                    cmbState.getSelectionModel().selectFirst();
                }
            }
        });
    }

}
