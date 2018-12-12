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
package com.zs.ina.notice;

import com.zs.ina.common.ChangeDateFormat;
import com.zs.ina.notice.dao.BranchPOJO;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.notice.dao.NoticeBEAN;
import com.zs.ina.notice.dao.NoticeIMPL;
import com.zs.ina.notice.dao.NoticeSERVICE;
import com.zs.traynotification.notification.Notification;
import com.zs.traynotification.notification.Notifications;
import com.zs.traynotification.notification.TrayNotification;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.Duration;
import org.apache.log4j.Logger;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLNoticeController implements Initializable {

    @FXML
    private TextArea txtarDescription;
    @FXML
    private TextField txtTitle;
    @FXML
    private ComboBox<BranchPOJO> cmbBranch;
    @FXML
    private DatePicker dpExpiredDate;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<NoticeBEAN> tblNotices;
    @FXML
    private TableColumn<?, ?> colNoticeTitle;
    @FXML
    private TableColumn<?, ?> colDescription;
    @FXML
    private TableColumn<?, ?> colBranch;
    @FXML
    private TableColumn<?, ?> colExpiredDate;

    NoticeBEAN noticeBEAN = new NoticeBEAN();
    NoticeSERVICE noticeSERVICE = new NoticeSERVICE(new NoticeIMPL());
    Logger logger = Logger.getLogger(NoticeIMPL.class);
    ObservableList<NoticeBEAN> noticeList = FXCollections.observableArrayList();
    ObservableList<BranchPOJO> branchList = FXCollections.observableArrayList();
    ObservableList<NoticeBEAN> noticeTableList = FXCollections.observableArrayList();
    ShowPopupMessages showPopupMessages = new ShowPopupMessages();
    String noticeId;
    String branchId;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnClear;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /* =========== code to be executed when loads the form ============ */
        ChangeDateFormat.datePickerDateFormatter(dpExpiredDate);
        branchList = noticeSERVICE.retrieveBranches();
        cmbBranch.setItems(branchList);
        dpExpiredDate.setEditable(false);
        btnDelete.disableProperty().bind(Bindings.equal(tblNotices.getSelectionModel().selectedIndexProperty(), -1));

        bindAllData();
        viewNoticeTable();

        /* =========== disable dates before today ============ */
        dpExpiredDate.setValue(LocalDate.now());
        final Callback<DatePicker, DateCell> dayCellFactory
                = new Callback<DatePicker, DateCell>() {
                    @Override
                    public DateCell call(final DatePicker datePicker) {
                        return new DateCell() {
                            @Override
                            public void updateItem(LocalDate item, boolean empty) {
                                super.updateItem(item, empty);

                                if (item.isBefore(LocalDate.now())) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                                }
                            }
                        };
                    }
                };
        dpExpiredDate.setDayCellFactory(dayCellFactory);

        /* =========== save/update button ============ */
        btnSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (dpExpiredDate.getValue() != null) {
                    noticeBEAN.setExpiredDate(dpExpiredDate.getValue().toString());
                }

                if (validateAllFields()) {

                    if (btnSave.getText().equals("Save")) {

                        int result_insert = noticeSERVICE.insertNotice(noticeBEAN);
                        if (result_insert > 0) {
                            viewNoticeTable();
                            Notification noticication = Notifications.SUCCESS;
                            TrayNotification trayNotification = new TrayNotification("Notice Details saved", "Notice Details saved successfully", noticication);
                            trayNotification.showAndDismiss(Duration.seconds(3));
                            clearAllFields();
                        } else {
                            Notification noticication = Notifications.ERROR;
                            TrayNotification trayNotification = new TrayNotification("Notice Details are not saved", "Notice Details are not saved correctly", noticication);
                            trayNotification.showAndDismiss(Duration.seconds(3));

                        }

                    }
                    if (btnSave.getText().equals("Update")) {
                        noticeBEAN.setId(noticeId);
                        // noticeBEAN.setBranchId(branchId);
                        int result_update = noticeSERVICE.updateNotice(noticeBEAN);
                        if (result_update > 0) {
                            btnSave.setText("Save");
                            btnDelete.setDisable(true);
                            viewNoticeTable();
                            Notification noticication = Notifications.SUCCESS;
                            TrayNotification trayNotification = new TrayNotification("Notice Details are updated", "Notice Details updated successfully", noticication);
                            trayNotification.showAndDismiss(Duration.seconds(3));
                            clearAllFields();

                        } else {
                            Notification noticication = Notifications.ERROR;
                            TrayNotification trayNotification = new TrayNotification("Notice Details are not updated", "Notice Details are not updated correctly", noticication);
                            trayNotification.showAndDismiss(Duration.seconds(3));

                        }

                    }
                }

            }

            private boolean validateAllFields() {
                boolean flag = true;
                if (noticeBEAN.getTitle() == null || noticeBEAN.getTitle().equals("")) {
                    showPopupMessages.showError("Enter notice title", txtTitle);
                    flag = false;
                } else if (noticeBEAN.getDescription() == null || noticeBEAN.getDescription().equals("")) {
                    showPopupMessages.showError("Enter sender mail address", txtarDescription);
                    flag = false;
                } else if (noticeBEAN.getBranchId() == null || noticeBEAN.getBranchId().equals("")) {
                    showPopupMessages.showError("Select branch", cmbBranch);
                    flag = false;
                } else if (noticeBEAN.getExpiredDate() == null || noticeBEAN.getExpiredDate().equals("")) {
                    showPopupMessages.showError("Select expiry date", cmbBranch);
                    flag = false;
                }

                return flag;

            }

        });

        /* =========== retrieve branch id from branch ============ */
        cmbBranch.valueProperty().addListener(new ChangeListener<BranchPOJO>() {

            @Override
            public void changed(ObservableValue<? extends BranchPOJO> observable, BranchPOJO oldValue, BranchPOJO newValue) {
                if (newValue != null) {
                    noticeBEAN.setBranchId(newValue.getBranchId());
                }
            }
        });

        /* =========== delete button ============ */
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                int result_delete = noticeSERVICE.deleteNotice(noticeId);
                if (result_delete > 0) {
                    btnSave.setText("Save");
                    //  btnDelete.setDisable(true);
                    clearAllFields();
                    viewNoticeTable();
                    Notification noticication = Notifications.SUCCESS;
                    TrayNotification trayNotification = new TrayNotification("Notice deleted", "Notice deleted successfully", noticication);
                    trayNotification.showAndDismiss(Duration.seconds(3));

                } else {
                    Notification noticication = Notifications.ERROR;
                    TrayNotification trayNotification = new TrayNotification("Notice not deleted", "Notice not deleted successfully", noticication);
                    trayNotification.showAndDismiss(Duration.seconds(3));

                }
            }
        });

        /* =========== clear button ============ */
        btnClear.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                clearAllFields();

            }
        });

        /* =========== notice table(tblNotices) RowFactory============ */
        tblNotices.setRowFactory(new Callback<TableView<NoticeBEAN>, TableRow<NoticeBEAN>>() {

            @Override
            public TableRow<NoticeBEAN> call(TableView<NoticeBEAN> param) {
                final TableRow<NoticeBEAN> row = new TableRow<NoticeBEAN>() {
                    @Override
                    protected void updateItem(NoticeBEAN noticeBEAN, boolean empty) {
                        super.updateItem(noticeBEAN, empty);
                        if (noticeBEAN != null) {

                        }
                    }

                };
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            if (noticeBEAN != null) {
                                viewNoticeDetails(row.getItem());
                                btnSave.setText("Update");

                            }
                        }
                    }

                    private void viewNoticeDetails(NoticeBEAN noticeBEAN) {

                        if (noticeBEAN != null) {
                            noticeId = noticeBEAN.getId();
                            txtTitle.setText(noticeBEAN.getTitle());
                            txtarDescription.setText(noticeBEAN.getDescription());
                            dpExpiredDate.setValue(LocalDate.parse(noticeBEAN.getExpiredDate()));
                            bindAllData();

                            for (BranchPOJO bpojo : cmbBranch.getItems()) {
                                if (bpojo.getBranch().equals(noticeBEAN.getBranchId())) {
                                    cmbBranch.getSelectionModel().select(bpojo);
                                }

                            }
                        }
                    }
                });
                return row;
            }

        });

    }

    private void bindAllData() {
        Bindings.bindBidirectional(txtTitle.textProperty(), noticeBEAN.titleProperty());
        Bindings.bindBidirectional(txtarDescription.textProperty(), noticeBEAN.descriptionProperty());

    }

    private void viewNoticeTable() {
        noticeTableList.clear();
        noticeTableList = noticeSERVICE.retrieveNotices();
        addNoticeIntoTable(noticeTableList);
    }

    private void addNoticeIntoTable(ObservableList<NoticeBEAN> noticeTableList) {
        colNoticeTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colBranch.setCellValueFactory(new PropertyValueFactory<>("branchId"));
        colExpiredDate.setCellValueFactory(new PropertyValueFactory<>("expiredDate"));
        ObservableList<NoticeBEAN> noticeBEANs = FXCollections.observableList(noticeTableList);
        FilteredList<NoticeBEAN> filteredData = new FilteredList<NoticeBEAN>(noticeBEANs, notice -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(notice -> {
                // If filter text is empty, display all events.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare all records with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (notice.getTitle().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (notice.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (notice.getBranchId().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (notice.getExpiredDate().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                } // Does not match.
            });
        });

        SortedList<NoticeBEAN> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblNotices.comparatorProperty());
        tblNotices.setItems(sortedData);
    }

    /**
     *
     */
    public void clearAllFields() {
        btnSave.setText("Save");
        noticeBEAN = new NoticeBEAN();
        bindAllData();
        txtSearch.setText("");
        cmbBranch.getSelectionModel().clearSelection();
        tblNotices.getSelectionModel().clearSelection();
        dpExpiredDate.setValue(null);

    }

}
