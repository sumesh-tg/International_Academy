/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.batchtime;

import com.zs.ina.admin.master.batchtime.dao.BatchTimeBean;
import com.zs.ina.admin.master.batchtime.dao.BatchTimeDAO;
import com.zs.ina.admin.master.excelupload.ExcelUpload;
import com.zs.ina.context.Context;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author zoft
 */
public class FXMLBatchTimingController implements Initializable {

    @FXML
    private TextField BatchTimingTxt;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button savebtn;
    @FXML
    private TextField searchTxt;
    @FXML
    private TableView<BatchTimeBean> batchTimeTbl;
    @FXML
    private TableColumn<?, ?> timingClmn;
    @FXML
    private TableColumn<?, ?> idClmn;
    final ContextMenu usernameValidator = new ContextMenu();
    private String qualificationId = "";
    private BatchTimeBean qualificationBean;
    @FXML
    private HBox timingHbox;
    @FXML
    private Button exelUploadBtn;
    private ObservableList<BatchTimeBean> masterSearchData = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        autoCompletion();
        addChangeEvents();
        tableInitMain();
        tableInit();
        BatchTimingTxt.focusedProperty().addListener(
                new ChangeListener<Boolean>() {
                    @Override
                    public void changed(
                            ObservableValue<? extends Boolean> arg0,
                            Boolean oldPropertyValue, Boolean newPropertyValue) {
                                if (newPropertyValue) {
                                    usernameValidator.hide();
                                }
                            }
                });
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        int row = BatchTimeDAO.delete(qualificationId);
        if (row > 0) {

            tableInitMain();
            searchTxt.setText("");
            clear();
        }
    }

    @FXML
    private void clearHandle(ActionEvent event) {
        clear();
    }

    @FXML
    private void saveHandle(ActionEvent event) {
        qualificationBean = new BatchTimeBean();
        qualificationBean.setBatchTime(BatchTimingTxt.getText());
        Context.getInstance().currentProfile().setBatchTimeBean(qualificationBean);
        if (validation()) {
            if (!qualificationId.equals("")) {
                int row = BatchTimeDAO.update(qualificationId);
                if (row > 0) {
                    tableInitMain();
                    clear();
                }
            } else {
                if (!BatchTimeDAO.checkDuplicateEntry(BatchTimingTxt.getText())) {
                    int row = BatchTimeDAO.insert();
                    if (row > 0) {
                        tableInitMain();
                        clear();
                    }
                } else {
                    usernameValidator.getItems().clear();
                    usernameValidator.getItems().add(
                            new MenuItem("Job type already inserted! "));
                    usernameValidator.show(BatchTimingTxt, Side.RIGHT, 10, 0);
                }
            }
        }
    }

    @FXML
    private void qualFieldTblMouseClicked(MouseEvent event) {
        savebtn.setText("Update");
        BatchTimeBean qualificationBean = (BatchTimeBean) batchTimeTbl.getSelectionModel().getSelectedItem();
        BatchTimingTxt.setText(qualificationBean.getBatchTime());
        qualificationId = qualificationBean.getId();
        deleteBtn.setVisible(true);
    }

    private void tableInit() {
        List<BatchTimeBean> branchList = BatchTimeDAO.getBatchTimedetails();
        idClmn.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        timingClmn.setCellValueFactory(
                new PropertyValueFactory<>("batchTime"));
//        phoneColumn.setCellValueFactory(
//                new PropertyValueFactory<>("phoneClmn"));

        //  agencyTable.getItems().setAll(branchList);
        masterSearchData.removeAll(masterSearchData);
        for (BatchTimeBean bp : branchList) {
            masterSearchData.add(bp);
        }

        FilteredList<BatchTimeBean> filteredData = new FilteredList<>(masterSearchData, p -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                // NoticePOJO noticePOJO=new NoticePOJO();
                if (enquiry.getBatchTime().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches contact name.
                }
//                else if (enquiry.getAddress().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter phone.
//                }
                return false; // Does not match.
            });
            SortedList<BatchTimeBean> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(batchTimeTbl.comparatorProperty());
            batchTimeTbl.setItems(sortedData);
        });

    }

    /**
     *
     */
    public void addChangeEvents() {
        BatchTimingTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue.matches("[a-zA-Z ]*")) {
                    BatchTimingTxt.setText(newValue);
                } else {
                    BatchTimingTxt.setText(oldValue);
                }
            }
        });
    }

    /**
     *
     * @return
     */
    public boolean validation() {
        boolean f = true;
        if (BatchTimingTxt.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter qualification field "));
            usernameValidator.show(BatchTimingTxt, Side.RIGHT, 10, 0);
            f = false;
        }
        return f;
    }

    private void clear() {
        BatchTimingTxt.setText("");
        qualificationId = "";
        savebtn.setText("Save");
        searchTxt.setText("");
    }

    /**
     *
     */
    public void autoCompletion() {
        List<String> allTiming = BatchTimeDAO.getAllTiming();
        BatchTimingTxt = TextFields.createClearableTextField();
        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(BatchTimingTxt, allTiming);
        BatchTimingTxt.setPrefWidth(200);
        timingHbox.getChildren().remove(0);
        timingHbox.getChildren().add(BatchTimingTxt);
        deleteBtn.setVisible(false);
    }

    @FXML
    private void handleExelUpload(ActionEvent event) {
        try {
            ExcelUpload excelUpload = new ExcelUpload();
            excelUpload.exelFileChooser("mastertbl_batch_timing");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLBatchTimingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void tableInitMain() {
        List<BatchTimeBean> branchList = BatchTimeDAO.getBatchTimedetails();
        idClmn.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        timingClmn.setCellValueFactory(
                new PropertyValueFactory<>("batchTime"));
        masterSearchData.removeAll(masterSearchData);
        for (BatchTimeBean b : branchList) {
            masterSearchData.add(b);
        }
        batchTimeTbl.setItems(masterSearchData);
    }

}
