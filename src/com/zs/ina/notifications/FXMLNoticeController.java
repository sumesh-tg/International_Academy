/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.notifications;

import com.zs.ina.admin.dao.AdminDAO;
import com.zs.ina.admin.master.excelupload.ExcelUpload;
import com.zs.ina.context.Context;
import com.zs.ina.notifications.dao.NoticePOJO;
import com.zs.ina.notifications.dao.NotificationsDAO;
import com.zs.ina.notifications.dao.NotificationsDAO.NoticeDetails;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLNoticeController implements Initializable {

    @FXML
    private TextField noticeTxt;
    @FXML
    private TextArea descriptionTxtArea;
    @FXML
    private ComboBox<?> branchCmb;
    @FXML
    private DatePicker expireDatePicker;
    @FXML
    private Button saveBtn;
    @FXML
    private Button deleteBtn;
    ObservableList branches = FXCollections.observableArrayList();
    @FXML
    private HBox noticeHbox;
    final ContextMenu usernameValidator = new ContextMenu();
    String  notice_id = null;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button exelUploadBtn;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        autoCompletion();
        getNoticeDetails();
        List<String> branch = AdminDAO.getAllBranches();
        for (String s : branch) {
            branches.add(s);
        }
        branches.add("ALL");
        branchCmb.setItems(branches);
        branchCmb.getSelectionModel().selectLast();
        noticeTxt.focusedProperty().addListener(
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
    private void handleSaveBtn(ActionEvent event) {

        if (offerFormValidation()) {
            LocalDate dt = expireDatePicker.getValue();
            Date date = Date.from(dt.atStartOfDay(ZoneId.systemDefault()).toInstant());
            NoticePOJO noticePOJO = new NoticePOJO();
            noticePOJO.setTitle(noticeTxt.getText());
            noticePOJO.setDescription(descriptionTxtArea.getText());
            noticePOJO.setExpiration_date(date);
            noticePOJO.setBranch(branchCmb.getSelectionModel().getSelectedItem().toString());
            Context.getInstance().currentProfile().setNoticePOJO(noticePOJO);
            if (notice_id !=null) {
                int row = NotificationsDAO.updateNewNotice(notice_id);
                if (row > 0) {
                    clearScreen();
                }
            } else {

                int row = NotificationsDAO.insertNewNotice();
                if (row > 0) {
                    clearScreen();
                }
            }
        }
    }

    /**
     *
     */
    public void autoCompletion() {
        List<String> allNotices = NotificationsDAO.getAllNotice();
        noticeTxt = TextFields.createClearableTextField();
        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(noticeTxt, allNotices);
        noticeTxt.setPrefWidth(215);
        noticeHbox.getChildren().remove(0);
        noticeHbox.getChildren().add(noticeTxt);
    }

    @FXML
    private void handleDeleteBtn(ActionEvent event) {
        int row = NotificationsDAO.deleteNotice(notice_id);
        if (row > 0) {
            clearScreen();
        }
    }

    /**
     *
     */
    public void clearScreen() {
        noticeTxt.setText("");
        descriptionTxtArea.setText("");
        expireDatePicker.setValue(null);
        branchCmb.getSelectionModel().selectLast();
        notice_id = null;
    }

    /**
     *
     */
    public void getNoticeDetails() {
        noticeTxt.focusedProperty().addListener(
                new ChangeListener<Boolean>() {
                    @Override
                    public void changed(
                            ObservableValue<? extends Boolean> arg0,
                            Boolean oldPropertyValue, Boolean newPropertyValue) {
                                if (!noticeTxt.getText().equals("")) {
                                    List<NoticeDetails> noticeList = NotificationsDAO.getNoticeDetails(noticeTxt.getText());
                                    for (NoticeDetails n : noticeList) {
                                        notice_id = n.getNoticeId();
                                        noticeTxt.setText(n.getNoticeTitle());
                                        descriptionTxtArea.setText(n.getDescription());
                                        branches.remove(n.getBranch());
                                    }
                                }
                            }
                });
    }
//Method for notice form validation

    /**
     *
     * @return
     */
    public boolean offerFormValidation() {
        boolean f = true;
        if (noticeTxt.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter notice Title! "));
            usernameValidator.show(noticeTxt, Side.RIGHT, 10, 0);
            f = false;
        } else if (descriptionTxtArea.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter description! "));
            usernameValidator.show(descriptionTxtArea, Side.RIGHT, 10, 0);
            f = false;
        } else if (expireDatePicker.getValue() == null) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please select valid date "));
            usernameValidator.show(expireDatePicker, Side.RIGHT, 10, 0);
            f = false;
        }
        return f;
    }

    @FXML
    private void handleCancelBtn(ActionEvent event) {
        clearScreen();
    }

    @FXML
    private void handleExelUpload(ActionEvent event) {
        try {
            ExcelUpload excelUpload = new ExcelUpload();
            excelUpload.exelFileChooser("notice_table");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLNoticeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleexpireDatePicker(ActionEvent event) {
        if (expireDatePicker.getValue() != null) {
            int a = NotificationsDAO.checkExpireDate(java.sql.Date.valueOf(expireDatePicker.getValue()));
            if (a < 0) {
                expireDatePicker.setValue(null);
            }
        }
    }
}
