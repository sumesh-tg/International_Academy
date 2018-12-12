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
package com.zs.ina.search.master.country;

import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.search.master.country.dao.CountryBEAN;
import com.zs.ina.search.master.country.dao.CountryDAO;
import com.zs.ina.search.master.university.FXMLUniversityController;
import com.zs.ina.search.master.university.dao.UniversityBEAN;
import com.zs.ina.search.master.university.dao.UniversityDAO;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.util.Callback;
import org.apache.log4j.Logger;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLCountrySearchController implements Initializable {

    @FXML
    private HBox hboxCountry;
    @FXML
    private TextField txtCountry;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView<CountryBEAN> tblCountry;
    @FXML
    private TableColumn<?, ?> colCountry;

    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    CountryDAO countryDAO = springAppContext.getBean(CountryDAO.class);
    CountryBEAN countryBEAN = new CountryBEAN();
    Logger logger = Logger.getLogger(FXMLCountrySearchController.class);
    ShowPopupMessages showPopupMessages = new ShowPopupMessages();
    List<CountryBEAN> listCountryTable = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        autoCompletion();
        bindAllData();
        viewCountryTable();

        btnSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (validateAllFields()) {
                    if (countryBEAN.getCountryId() != null) {
                        int result_update = countryDAO.updateCountry(countryBEAN);
                        if (result_update > 0) {
                            viewCountryTable();
                            clearAllFields();
                            showPopupMessages.showSuccess("Updated Successfully !", "Country updated Successfully !", btnSave);
                        } else {
                            showPopupMessages.showError("Updation Failed ! Try again !", btnSave);
                        }
                    } else {
                        int result_insert = countryDAO.insertCountry(countryBEAN);
                        if (result_insert > 0) {
                            viewCountryTable();
                            clearAllFields();
                            showPopupMessages.showSuccess("Saved Successfully !", "Country saved Successfully !", btnSave);
                        } else {
                            showPopupMessages.showError("Save Failed ! Try again !", btnSave);
                        }
                    }

                }
            }

        });
//        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//
//                if (countryBEAN.getUniversityId() != null) {
//                    int result_delete = countryDAO.deleteUniversity(countryBEAN);
//                    if (result_delete > 0) {
//                        viewUniversityTable();
//                        clearAllFields();
//                        showPopupMessages.showSuccess("Deleted Successfully !", "University Deleted Successfully !", btnSave);
//                    } else {
//                        showPopupMessages.showError("Deletion Failed ! Try again !", btnSave);
//                    }
//
//                }
//            }
//        });
        tblCountry.setRowFactory(new Callback<TableView<CountryBEAN>, TableRow<CountryBEAN>>() {
            @Override
            public TableRow<CountryBEAN> call(TableView<CountryBEAN> param) {
                final TableRow<CountryBEAN> row = new TableRow<CountryBEAN>() {
                    @Override
                    protected void updateItem(CountryBEAN countryBEAN, boolean empty) {
                        super.updateItem(countryBEAN, empty);
                        if (countryBEAN != null) {

                        }
                    }

                };
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            if (row.getItem() != null) {
                                countryBEAN = new CountryBEAN();
                                countryBEAN.countryIdProperty().addListener(new ChangeListener<String>() {

                                    @Override
                                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                        if (newValue != null) {
                                            btnSave.setText("Update");
                                        } else {
                                            btnSave.setText("Save");
                                        }
                                    }

                                });
                                BeanUtils.copyProperties(row.getItem(), countryBEAN);
                                bindAllData();

                            }

                        }
                    }

                });
                return row;
            }
        });
        btnClear.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                clearAllFields();

            }
        });
        txtCountry.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                        Bindings.unbindBidirectional(countryBEAN.countryProperty(), txtCountry.textProperty());
                        txtCountry.setText(oldValue);
                        Bindings.bindBidirectional(countryBEAN.countryProperty(), txtCountry.textProperty());
                    } else {
                        Bindings.unbindBidirectional(countryBEAN.countryProperty(), txtCountry.textProperty());
                        txtCountry.setText(newValue);
                        Bindings.bindBidirectional(countryBEAN.countryProperty(), txtCountry.textProperty());
                    }
                }
            }

        });

    }

    /**
     *
     */
    public void bindAllData() {

        Bindings.bindBidirectional(txtCountry.textProperty(), countryBEAN.countryProperty());
    }

    /**
     *
     */
    public void viewCountryTable() {
        listCountryTable.clear();
        listCountryTable = countryDAO.retreiveCountries();
        addCountryIntoTable(listCountryTable);
    }

    public void addCountryIntoTable(List<CountryBEAN> listCountryTable) {
        colCountry.setCellValueFactory(new PropertyValueFactory<>("country"));

        ObservableList<CountryBEAN> countryBEANs = FXCollections.observableList(listCountryTable);
        FilteredList<CountryBEAN> filteredData = new FilteredList<CountryBEAN>(countryBEANs, country -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(country -> {
                // If filter text is empty, display all events.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare all records with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (country.getCountry().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                } // Does not match.
            });
        });
        SortedList<CountryBEAN> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblCountry.comparatorProperty());
        tblCountry.setItems(sortedData);
    }

    /**
     *
     */
    public void clearAllFields() {
        btnSave.setText("Save");
        countryBEAN = new CountryBEAN();
        bindAllData();
        countryBEAN.countryIdProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    btnSave.setText("Update");
                } else {
                    btnSave.setText("Save");
                }
            }

        });
        txtSearch.setText("");

    }

    /**
     *
     * @return
     */
    public boolean validateAllFields() {
        boolean flag = true;
        if (countryBEAN.getCountry() == null || countryBEAN.getCountry().equals("")) {
            showPopupMessages.showError("Enter Country", txtCountry);
            flag = false;
        }

        return flag;
    }

    /**
     *
     */
    public void autoCompletion() {
        /* ======================== Create Autocompletion Data By Overriding @toString ==================== */
        ObservableList<CountryBEAN> listCountryAuto = countryDAO.retreiveCountries();
        ObservableList<CountryBEAN> listAutoCompletion = FXCollections.observableArrayList();
        for (CountryBEAN autoBEAN : listCountryAuto) {
            CountryBEAN autoCompletionBEAN = new CountryBEAN() {
                @Override
                public String toString() {
                    return autoBEAN.getCountry();
                }
            };
            BeanUtils.copyProperties(autoBEAN, autoCompletionBEAN);
            listAutoCompletion.add(autoCompletionBEAN);
        }
        System.out.println("test auto completion list :: " + listAutoCompletion.toString());
        hboxCountry.getChildren().remove(txtCountry);
        txtCountry = TextFields.createClearableTextField();
        AutoCompletionBinding<CountryBEAN> autoCompletionBindingNumber = TextFields.bindAutoCompletion(txtCountry, listAutoCompletion);
        autoCompletionBindingNumber.setOnAutoCompleted(new EventHandler<AutoCompletionBinding.AutoCompletionEvent<CountryBEAN>>() {
            @Override
            public void handle(AutoCompletionBinding.AutoCompletionEvent<CountryBEAN> event) {
                countryBEAN = new CountryBEAN();
                BeanUtils.copyProperties(event.getCompletion(), countryBEAN);
                bindAllData();
            }

        });
        HBox.setHgrow(txtCountry, Priority.ALWAYS);
        txtCountry.setPrefWidth(261);
        hboxCountry.getChildren().add(txtCountry);

    }

}
