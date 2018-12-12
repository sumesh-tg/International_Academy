/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.profession;

import com.zs.ina.admin.master.excelupload.ExcelUpload;
import com.zs.ina.admin.profession.dao.ProfessionBean;
import com.zs.ina.admin.profession.dao.ProfessionDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 * FXML Controller class
 *
 * @author zoft
 */
public class FXMLProfessionController implements Initializable {

    @FXML
    private TextField profession;
    @FXML
    private TextField searchTxt;
    @FXML
    private TableColumn<?, ?> professionClmn;
    @FXML
    private TableColumn<?, ?> idClmn;
    @FXML
    private TableView<ProfessionBean> professionTbl;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private Button saveBtn;
    private String qualificationId = "";
    final ContextMenu usernameValidator = new ContextMenu();
    private ProfessionBean qualificationBean;
    private ObservableList<ProfessionBean> masterSearchData = FXCollections.observableArrayList();
    @FXML
    private Button exelUploadBtn;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboInit();
        tablemainInit();
        tableInit();
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        int row = ProfessionDAO.delete(qualificationBean.getId());

        tablemainInit();
        clear();

    }

    @FXML
    private void clearHandle(ActionEvent event) {
        clear();
    }

    @FXML
    private void saveHandle(ActionEvent event) {
        qualificationBean = new ProfessionBean();
        qualificationBean.setProfession(profession.getText());
        qualificationBean.setId(qualificationId);
//        Context.getInstance().currentProfile().setProfessionBean(qualificationBean);
        if (validation()) {
            if (!qualificationId.equals("")) {
                int row = ProfessionDAO.update(qualificationBean);
                if (row > 0) {
                    tablemainInit();
                    clear();
                }
            } else {
                if (!ProfessionDAO.checkDuplicateEntry(profession.getText())) {
                    int row = ProfessionDAO.insert(qualificationBean);
                    if (row > 0) {
                        tablemainInit();
                        clear();
                    }
                } else {
                    usernameValidator.getItems().clear();
                    usernameValidator.getItems().add(
                            new MenuItem("Profession already inserted! "));
                    usernameValidator.show(profession, Side.RIGHT, 10, 0);
                }
            }
        }
    }

    @FXML
    private void qualFieldTblMouseClicked(MouseEvent event) {
        saveBtn.setText("Update");
        qualificationBean = (ProfessionBean) professionTbl.getSelectionModel().getSelectedItem();
        profession.setText(qualificationBean.getProfession());
        qualificationId = qualificationBean.getId();
    }

    private void comboInit() {
    }

    private void tableInit() {
        ObservableList<ProfessionBean> instituteBeans = ProfessionDAO.getProfessiondetails();
        professionClmn.setCellValueFactory(new PropertyValueFactory<>("profession"));
        idClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        masterSearchData.removeAll(masterSearchData);
        for (ProfessionBean bp : instituteBeans) {
            masterSearchData.add(bp);
        }
        FilteredList<ProfessionBean> filteredData = new FilteredList<>(masterSearchData, p -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (enquiry.getProfession().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                return false;
            });
            SortedList<ProfessionBean> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(professionTbl.comparatorProperty());
            professionTbl.setItems(sortedData);
        });

    }

    /**
     *
     * @return
     */
    public boolean validation() {
        boolean f = true;
        if (profession.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter qualification field "));
            usernameValidator.show(profession, Side.RIGHT, 10, 0);
            f = false;
        }
        return f;
    }

    private void clear() {
        searchTxt.setText("");
        profession.setText("");
        qualificationId = "";
        saveBtn.setText("Save");
    }

    private void tablemainInit() {
        ObservableList<ProfessionBean> instituteBeans = ProfessionDAO.getProfessiondetails();
        professionClmn.setCellValueFactory(new PropertyValueFactory<>("profession"));
        idClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        masterSearchData.removeAll(masterSearchData);
        for (ProfessionBean bp : instituteBeans) {
            masterSearchData.add(bp);
        }
        professionTbl.setItems(masterSearchData);
    }

    @FXML
    private void handleExelUploadBtn(ActionEvent event) {

        try {
            ExcelUpload excelUpload = new ExcelUpload();
            excelUpload.exelFileChooser("mastertbl_profession");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLProfessionController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
