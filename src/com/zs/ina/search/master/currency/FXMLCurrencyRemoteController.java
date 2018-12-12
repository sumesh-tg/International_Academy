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

import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.context.Context;
import com.zs.ina.search.master.currency.dao.CurrencyBEAN;
import com.zs.ina.search.master.currency.dao.CurrencyDAO;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import org.springframework.context.ApplicationContext;

/**
 * FXML Controller class
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class FXMLCurrencyRemoteController implements Initializable {
    
    @FXML
    private Button btnFetchRemoteData;
    @FXML
    private TableView<CurrencyBEAN> tbCurrencyData;
    @FXML
    private TableColumn<CurrencyBEAN, String> colSno;
    @FXML
    private TableColumn<CurrencyBEAN, String> colDate;
    @FXML
    private TableColumn<CurrencyBEAN, String> colCountry;
    @FXML
    private TableColumn<CurrencyBEAN, String> colCurrencyCode;
    @FXML
    private TableColumn<CurrencyBEAN, String> colRate;
    @FXML
    private TableColumn<CurrencyBEAN, String> colAtInr;
    @FXML
    private TableColumn<CurrencyBEAN, String> colCurrencyName;
    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    CurrencyDAO currencyDAO = springAppContext.getBean(CurrencyDAO.class);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /* ======================== List All Details ==================== */
        ObservableList<CurrencyBEAN> listCurrency = currencyDAO.retrieveCurrency();
        colCurrencyCode.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CurrencyBEAN, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CurrencyBEAN, String> param) {
                return param.getValue().currencyProperty();
            }
        });
        colCountry.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CurrencyBEAN, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CurrencyBEAN, String> param) {
                return param.getValue().countryProperty();
            }
        });
        colCurrencyName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CurrencyBEAN, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CurrencyBEAN, String> param) {
                return param.getValue().currencyNameProperty();
            }
        });
        colDate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CurrencyBEAN, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CurrencyBEAN, String> param) {
                return param.getValue().cdateProperty();
            }
        });
        colRate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CurrencyBEAN, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CurrencyBEAN, String> param) {
                return param.getValue().rateProperty();
            }
        });
        colSno.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CurrencyBEAN, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CurrencyBEAN, String> param) {
                return param.getValue().idProperty();
            }
        });
        colAtInr.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CurrencyBEAN, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CurrencyBEAN, String> param) {
                Double rate = Double.parseDouble(param.getValue().rateProperty().get());
                rate = 1 / rate;
                StringProperty rateConverted = new SimpleStringProperty();
                rateConverted.set("" + rate);
                return rateConverted;
            }
        });
        tbCurrencyData.getItems().setAll(listCurrency);
        btnFetchRemoteData.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /* ======================== Run Task For Fetching Remote Data ==================== */
                Task<CurrencyRemotePOJO> taskRemoteCurrencyRate = new Task<CurrencyRemotePOJO>() {
                    @Override
                    protected CurrencyRemotePOJO call() throws Exception {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                        }
                        return ReadDataFromWeb.request("INR");
                    }
                };
                taskRemoteCurrencyRate.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                    @Override
                    public void handle(WorkerStateEvent event) {
                        CurrencyRemotePOJO currencyRemote = taskRemoteCurrencyRate.getValue();
                        Map<String, String> mapRates = new HashMap<>();
                        mapRates = currencyRemote.getRates();
                        String baseId = "cr_" + UiiDGenerator.getUIID8();
                        CurrencyBEAN currencyBEAN = new CurrencyBEAN();
                        currencyBEAN.setBaseId(baseId);
                        currencyBEAN.setBaseCurrencyCode(currencyRemote.getBase());
                        currencyBEAN.setCdate(currencyRemote.getDate());
                        String currencyExists = currencyDAO.checkCurrencyExist(currencyBEAN);
                        if (currencyExists != null) {
                            currencyDAO.updateCurrencyRates(currencyExists, mapRates);
                        } else {
                            currencyDAO.insertBaseCurrency(currencyBEAN);
                            currencyDAO.insertCurrencyRates(baseId, mapRates);
                        }
                    }
                });
                taskRemoteCurrencyRate.run();
            }
        });
    }
    
}
