/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.timing;

import com.zs.ina.admin.master.excelupload.ExcelUpload;
import com.zs.ina.admin.master.timing.dao.TimingBEAN;
import com.zs.ina.admin.master.timing.dao.TimingDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLTimingController implements Initializable {

    @FXML
    private HBox timingHBox;
    @FXML
    private TextField timingTxt;
    @FXML
    private Button excelUploadBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private Button saveBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private TextField searchTxt;
    @FXML
    private TableView<TimingBEAN> timingTbl;
    @FXML
    private TableColumn<?, ?> timingClmn;
    @FXML
    private TableColumn<?, ?> idClmn;
    private String timingId = "";
    final ContextMenu usernameValidator = new ContextMenu();
    private TimingBEAN timingBEAN;
    private ObservableList<TimingBEAN> masterSearchData = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        autoCompletion();
        setTableValues();
        serchValues();
        addChangeEvents();
    }

    @FXML
    private void handleBranchTxt(MouseEvent event) {
    }

    @FXML
    private void handlebtnExcelUpload(ActionEvent event) throws SQLException {
        ExcelUpload excelUpload = new ExcelUpload();
        excelUpload.exelFileChooser("mastertbl_timing");
        setTableValues();
    }

    /**
     *
     */
    public void addChangeEvents() {
        timingTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue.matches("[a-zA-Z0-9-. ]*")) {
                    timingTxt.setText(newValue);
                } else {
                    timingTxt.setText(oldValue);
                }
            }
        });
    }

    @FXML
    private void handleClearBtn(ActionEvent event) {
        clear();
    }

    @FXML
    private void handleSaveBtn(ActionEvent event) {
        timingBEAN = new TimingBEAN();
        timingBEAN.setTiming(timingTxt.getText());
        timingBEAN.setId(timingId);
        if (validation()) {
            if (!timingId.equals("")) {
                int row = TimingDAO.updateTiming(timingBEAN);
                if (row > 0) {
                    setTableValues();
                    clear();
                }
            } else {
                if (!TimingDAO.checkDuplicateEntry(timingTxt.getText())) {
                    int row = TimingDAO.insertTiming(timingBEAN);
                    if (row > 0) {
                        setTableValues();
                        clear();
                    }
                } else {
                    usernameValidator.getItems().clear();
                    usernameValidator.getItems().add(
                            new MenuItem("Timing already inserted! "));
                    usernameValidator.show(timingTxt, Side.RIGHT, 10, 0);
                }
            }
        }
    }

    @FXML
    private void handlebtnDelete(ActionEvent event) {
        TimingDAO.deleteTiming(timingBEAN.getId());
        setTableValues();
        searchTxt.setText("");
        clear();
    }

    @FXML
    private void handleTimingTblMouseClicked(MouseEvent event) {
        saveBtn.setText("Update");
        timingBEAN = (TimingBEAN) timingTbl.getSelectionModel().getSelectedItem();
        timingTxt.setText(timingBEAN.getTiming());
        timingId = timingBEAN.getId();
    }

    @FXML
    private void handleTimingTblReleased(KeyEvent event) {
        saveBtn.setText("Update");
        timingBEAN = (TimingBEAN) timingTbl.getSelectionModel().getSelectedItem();
        timingTxt.setText(timingBEAN.getTiming());
        timingId = timingBEAN.getId();
    }

    private void clear() {
        timingTxt.setText("");
        timingId ="";
        saveBtn.setText("Save");
    }

    /**
     *
     * @return
     */
    public boolean validation() {
        boolean f = true;
        if (timingTxt.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter timing field "));
            usernameValidator.show(timingTxt, Side.RIGHT, 10, 0);
            f = false;
        }
        return f;
    }

    /**
     *
     */
    public void autoCompletion() {
        List<String> allScores = TimingDAO.getAllTimings();
        timingTxt = TextFields.createClearableTextField();
        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(timingTxt, allScores);
        timingTxt.setPrefWidth(200);
        timingHBox.getChildren().remove(0);
        timingHBox.getChildren().add(timingTxt);
    }

    /**
     *
     */
    public void setTableValues() {
        ObservableList<TimingBEAN> timingBEANs = TimingDAO.getTimingDetails();
        timingClmn.setCellValueFactory(new PropertyValueFactory<>("timing"));
        idClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        masterSearchData.removeAll(masterSearchData);
        for (TimingBEAN bp : timingBEANs) {
            masterSearchData.add(bp);
        }
        timingTbl.setItems(masterSearchData);
    }

    /**
     *
     */
    public void serchValues() {
        ObservableList<TimingBEAN> timingBEANs = TimingDAO.getTimingDetails();
        timingClmn.setCellValueFactory(new PropertyValueFactory<>("timing"));
        idClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        masterSearchData.removeAll(masterSearchData);
        for (TimingBEAN bp : timingBEANs) {
            masterSearchData.add(bp);
        }

        FilteredList<TimingBEAN> filteredData = new FilteredList<>(masterSearchData, p -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (enquiry.getTiming().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                return false;
            });
            SortedList<TimingBEAN> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(timingTbl.comparatorProperty());
            timingTbl.setItems(sortedData);
        });
    }

}
