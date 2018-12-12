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
package com.zs.ina.search.master.currency;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class FXMLCurrencyController implements Initializable {

    @FXML
    private HBox timingHbox;
    @FXML
    private TextField txtCurrency;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    @FXML
    private TextField searchTxt;
    @FXML
    private TableView<?> tblCurrency;
    @FXML
    private TableColumn<?, ?> colCurrency;
    @FXML
    private TableColumn<?, ?> colId;

    /**
     *
     */
    public static JSONObject jsonObject = null;
    @FXML
    private ComboBox<Object> cmbCurrency;
    @FXML
    private Button btnRefresh;
    @FXML
    private TableView<Map.Entry<String, String>> tblCurrencyRemote;
    @FXML
    private TableColumn<Map.Entry<String, String>, String> colRemoteCurrncy;
    @FXML
    private TableColumn<Map.Entry<String, String>, String> colRemoteRate;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblBase;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblCurrencyRemote.setPlaceholder(new Label("No Internet Connection!"));
        if (ReadDataFromWeb.testInet("google.com")) {
            retrieveRemoteData("USD");
        } else {
            System.out.println("No Internet");
        }
        btnRefresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (cmbCurrency.getSelectionModel().getSelectedIndex() > -1) {
                    if (ReadDataFromWeb.testInet("google.com")) {
                        retrieveRemoteData(cmbCurrency.getSelectionModel().getSelectedItem().toString());
                    } else {
                        System.out.println("No Internet");
                    }
                }
            }
        });
    }

    /**
     *
     * @param base
     */
    public void retrieveRemoteData(String base) {
        tblCurrencyRemote.getItems().clear();
        /* ======================== Run Task For Fetching Remote Data ==================== */
        Task<CurrencyRemotePOJO> taskRemoteCurrencyRate = new Task<CurrencyRemotePOJO>() {
            @Override
            protected CurrencyRemotePOJO call() throws Exception {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
                return ReadDataFromWeb.request(base);
            }
        };
        taskRemoteCurrencyRate.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                CurrencyRemotePOJO currencyRemote = taskRemoteCurrencyRate.getValue();
                Map<String, String> mapRates = new HashMap<>();
                mapRates = currencyRemote.getRates();
                ObservableList<Map.Entry<String, String>> items = FXCollections.observableArrayList(mapRates.entrySet());
                colRemoteCurrncy.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, String>, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, String>, String> p) {
                        return new SimpleStringProperty(p.getValue().getKey());
                    }
                });
                colRemoteRate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, String>, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, String>, String> p) {
                        return new SimpleStringProperty(p.getValue().getValue());
                    }
                });
                lblBase.setText("Base :" + currencyRemote.getBase());
                lblDate.setText("Date :" + currencyRemote.getDate());
                tblCurrencyRemote.getItems().addAll(items);
                cmbCurrency.getItems().addAll(mapRates.keySet());
            }
        });
        taskRemoteCurrencyRate.run();
    }
}
