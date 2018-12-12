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

import com.zs.ina.admin.master.locations.dao.CountryDAO;
import com.zs.ina.admin.master.locations.dao.CountryIMPL;
import com.zs.ina.admin.master.locations.dao.LocationsDAO;
import com.zs.ina.admin.master.locations.dao.LocationsIMPL;
import com.zs.ina.context.Context;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.enquiry.search.editing.dao.InlineEditingDAO;
import com.zs.ina.enquiry.search.editing.dao.InlineEditingIMPL;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
public class FXMLCountryLocationController implements Initializable {

    @FXML
    private ComboBox<?> cmbLocation;
    @FXML
    private ComboBox<?> cmbCountry;
    @FXML
    private Button btnSave;
    CountryDAO countryDAO = new CountryIMPL();
    LocationsDAO locationsDAO = new LocationsIMPL();
    CounselorDetailsBEAN counselorDetailsBEAN;
    String currrentTab;
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
        currrentTab = Context.getInstance().currentProfile().getCurrentTab();

        System.out.println("Testing popup tab :: " + currrentTab);

        cmbCountry.setItems(countryDAO.retrieveAllCountries());
        cmbCountry.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue != null) {
                    cmbLocation.setItems(locationsDAO.retrievLocationsByCountry(newValue.toString()));
                    // cmbServiceLocation.getSelectionModel().selectFirst();
                }
            }
        });
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (cmbCountry.getSelectionModel().getSelectedIndex() >= 0 && cmbLocation.getSelectionModel().getSelectedIndex() >= 0) {

                    switch (currrentTab) {
                        case "study":
                            System.out.println("Save Study");
                            counselorDetailsBEAN.setStudyCountry(cmbCountry.getSelectionModel().getSelectedItem().toString());
                            counselorDetailsBEAN.setStudyLocation(cmbLocation.getSelectionModel().getSelectedItem().toString());
                            counselorDetailsBEAN.setStudyCountryLocation(counselorDetailsBEAN.getStudyCountry() + "-" + counselorDetailsBEAN.getStudyLocation());
                            inlineEditingDAO.updateStudyCountryLocaion(counselorDetailsBEAN);
                            Context.getInstance().currentProfile().getPopOver().hide();
                            break;
                        case "work":
                            System.out.println("Save work");
                            counselorDetailsBEAN.setWorkCountry(cmbCountry.getSelectionModel().getSelectedItem().toString());
                            counselorDetailsBEAN.setWorkLocation(cmbLocation.getSelectionModel().getSelectedItem().toString());
                            counselorDetailsBEAN.setWorkCountryLocation(counselorDetailsBEAN.getWorkCountry() + "-" + counselorDetailsBEAN.getWorkLocation());
                            inlineEditingDAO.updateWorkCountryLocaion(counselorDetailsBEAN);
                            Context.getInstance().currentProfile().getPopOver().hide();
                            break;
                        case "migration":
                            System.out.println("Save migration");
                            counselorDetailsBEAN.setMigrateCountry(cmbCountry.getSelectionModel().getSelectedItem().toString());
                            counselorDetailsBEAN.setMigrateLocation(cmbLocation.getSelectionModel().getSelectedItem().toString());
                            counselorDetailsBEAN.setMigrateCountryLocation(counselorDetailsBEAN.getMigrateCountry() + "-" + counselorDetailsBEAN.getMigrateLocation());
                            inlineEditingDAO.updateMigrateCountryLocaion(counselorDetailsBEAN);
                            Context.getInstance().currentProfile().getPopOver().hide();
                            break;
                        default:
                            System.out.println("Failed to this update");
                    }
                }
            }
        }
        );
    }

}
