/*
 * Copyright ZoftSolutions(C) 2015 100018
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
package com.zs.ina.admin.inbox.forward;

import com.zs.ina.admin.inbox.forward.dao.ForwardHistoryDAO;
import com.zs.ina.admin.inbox.forward.dao.ForwardHistoryIMPl;
import com.zs.ina.admin.inbox.forward.dao.ForwardHistoryPOJO;
import com.zs.ina.admin.master.purpose.dao.PurposeDAO;
import com.zs.ina.admin.master.purpose.dao.PurposeIMPL;
import com.zs.ina.admin.master.purpose.dao.PurposeModel;
import com.zs.ina.admin.master.retrieve.CounselorsListPOJO;
import com.zs.ina.admin.master.retrieve.RetrieveStaticMasterDAO;
import com.zs.ina.common.EffectsClass;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.common.inbox.commonpool.CommonPoolService;
import com.zs.ina.context.Context;
import com.zs.ina.counselor.FXMLCounselorMainViewController;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.enquiry.search.editing.dao.InlineEditingDAO;
import com.zs.ina.enquiry.search.editing.dao.InlineEditingIMPL;
import com.zs.traynotification.animations.Animations;
import com.zs.traynotification.notification.Notification;
import com.zs.traynotification.notification.Notifications;
import com.zs.traynotification.notification.TrayNotification;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author 100018
 */
public class FXMLForwardController implements Initializable {

    InlineEditingDAO inlineEditingDAO = new InlineEditingIMPL();

    @FXML
    private ComboBox<String> cmbBranch;
    @FXML
    private ComboBox<CounselorsListPOJO> cmbCounselors;
    @FXML
    private Button cmbSubmit;
    private String CUR_USERNAME = null;
    private String CUR_ROLE = null;
    private String CUR_BRANCH = null;
    ObservableList branch = FXCollections.observableArrayList();
    /* ================== Error Label =============== */
    @FXML
    Label lbleError = new Label();
    @FXML
    private ComboBox<PurposeModel> cmbPurpose;
    PurposeDAO purposeDAO = new PurposeIMPL();
    ForwardHistoryDAO forwardHistoryDAO = new ForwardHistoryIMPl();
    boolean done = false;
    ObservableList<CounselorsListPOJO> assignedCounselors = FXCollections.observableArrayList();
    @FXML
    private Label lblWarning;

    /**
     * Initializes the controller class.
     *
     * @param listCounselorDetailsBEAN
     * @param tableName
     * @param role
     * @param popOver
     * @return
     */
    public boolean forwardMultipleEnquiries(List<CounselorDetailsBEAN> listCounselorDetailsBEAN, PopOver popOver, TableView<CounselorDetailsBEAN> tableName, String role) {
        CUR_USERNAME = Context.getInstance().currentProfile().getProfilePOJO().getUsername();
        CUR_BRANCH = Context.getInstance().currentProfile().getProfilePOJO().getBranch();
        CUR_ROLE = Context.getInstance().currentProfile().getProfilePOJO().getRole();
        lbleError.setText("");
        lblWarning.setText("");
        System.out.println("Check Size In Forward Controller :: " + listCounselorDetailsBEAN.size());
        cmbPurpose.setItems(purposeDAO.listPurpose());
        List<String> branches = RetrieveStaticMasterDAO.getAllBranches();
        branches.stream().forEach((s) -> {
            branch.add(s);
        });
        cmbBranch.setItems(branch);
        cmbBranch.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue != null) {
                    assignedCounselors = RetrieveStaticMasterDAO.getAllCounselors(newValue);
                    cmbCounselors.setItems(assignedCounselors);
                    for (CounselorsListPOJO listPOJO : cmbCounselors.getItems()) {
                        if (listPOJO.getUsername().equalsIgnoreCase(CUR_USERNAME)) {

                            Platform.runLater(new Runnable() {

                                @Override
                                public void run() {
                                    cmbCounselors.getItems().remove(listPOJO);
                                }

                            });
                        }
                    }

                }

            }
        });
        cmbSubmit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                lbleError.setText("");
                if (cmbCounselors.getSelectionModel().getSelectedIndex() > -1 && cmbBranch.getSelectionModel().getSelectedIndex() > -1 && cmbPurpose.getSelectionModel().getSelectedIndex() > -1) {
                    for (CounselorDetailsBEAN counselorDetailsBEAN : listCounselorDetailsBEAN) {
                        /* ======================== Check Counselor Limits ==================== */
                        Map<String, String> mapCommonPoolConfig = CommonPoolService.retrieveCommonPoolConfig(cmbCounselors.getSelectionModel().getSelectedItem().getUsername(), cmbBranch.getSelectionModel().getSelectedItem(), "ROLE_COUNSELOR");
                        Map<String, String> mapFlagEnquiryStatus = CommonPoolService.checkFlagEnquiryStatus(cmbCounselors.getSelectionModel().getSelectedItem().getUsername(), cmbBranch.getSelectionModel().getSelectedItem(), "ROLE_COUNSELOR");

                        long flagLimitConfig = Long.parseLong(mapCommonPoolConfig.get("flag_limit"));
                        long flagLimitStatus = Long.parseLong(mapFlagEnquiryStatus.get("flag_limit"));
                        long enquiryLimitConfig = Long.parseLong(mapCommonPoolConfig.get("enquiry_limit"));
                        long enquiryLimitStatus = Long.parseLong(mapFlagEnquiryStatus.get("enquiry_limit"));
                        //  if (flagLimitConfig == 0 || flagLimitStatus < flagLimitConfig || counselorDetailsBEAN.getImportant().equalsIgnoreCase("1")) {
                        System.out.println("Current Count ::" + enquiryLimitStatus);
                        System.out.println("Config Count ::" + enquiryLimitConfig);
                        if (enquiryLimitConfig == 0 || enquiryLimitStatus <= enquiryLimitConfig) {
                            /* ======================== End Check counselor limits ==================== */
                            System.out.println("test before limit check2");
                            ForwardHistoryPOJO historyPOJO = new ForwardHistoryPOJO();
                            historyPOJO.setHid("his_" + UiiDGenerator.getUIID8());
                            historyPOJO.setAssigned_branch(CUR_BRANCH);
                            historyPOJO.setAssigned_by(CUR_USERNAME);
                            historyPOJO.setAssigned_date(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()).toString());
                            historyPOJO.setEnquiry_id(counselorDetailsBEAN.getEnquiryID());
                            historyPOJO.setAssigned_to(cmbCounselors.getSelectionModel().getSelectedItem().getUsername());
                            counselorDetailsBEAN.setEnquiryAssignedTo(cmbCounselors.getSelectionModel().getSelectedItem().getUsername());
                            historyPOJO.setAssigned_to_branch(cmbBranch.getSelectionModel().getSelectedItem());
                            counselorDetailsBEAN.setBracnch(cmbBranch.getSelectionModel().getSelectedItem());
                            historyPOJO.setHolded_by(counselorDetailsBEAN.getEnquiryAssignedTo());
                            historyPOJO.setPurpose(cmbPurpose.getSelectionModel().getSelectedItem().getPurpose());
                            historyPOJO.setRemarks(counselorDetailsBEAN.getRemarks());
                            if (counselorDetailsBEAN.getStatus() != null) {
                                historyPOJO.setCurrent_status(counselorDetailsBEAN.getStatus());
                            } else {
                                historyPOJO.setCurrent_status("Assessment Pending");
                            }
                            historyPOJO.setCompletionFlag(counselorDetailsBEAN.getCompletionflag());
                            historyPOJO.setImportantFlag("0");
                            historyPOJO.setStudy_required(counselorDetailsBEAN.getStudyRequired());
                            historyPOJO.setWork_required(counselorDetailsBEAN.getWorkRequired());
                            historyPOJO.setMigration_required(counselorDetailsBEAN.getMigrationRequired());
                            historyPOJO.setTraining_required(counselorDetailsBEAN.getTrainingRequired());
                            forwardHistoryDAO.forwardEnquiry(historyPOJO);
                            done = true;
                            cmbSubmit.setDisable(true);
                            tableName.getItems().remove(counselorDetailsBEAN);
                            lbleError.setText("Enquiry Assigned to " + cmbCounselors.getSelectionModel().getSelectedItem());
                            EffectsClass.fadeOut(lbleError, 2000.0);
//
//                            Notification notification = Notifications.SUCCESS;
//                            TrayNotification tray = new TrayNotification("Enquiries Forwarded !", "Enquiries has been forwarded successfully!", notification);
//                            tray.setAnimation(Animations.FADE);
//                            tray.showAndDismiss(Duration.seconds(2), popOver.getOwnerNode());
//                            tray.setOnDismiss(new EventHandler<ActionEvent>() {
//
//                                @Override
//                                public void handle(ActionEvent event) {
//                                    //   popOver.hide();
//                                }
//                            });
                        } else {
                            System.out.println("His Limit Reached !");
                            Notification notification = Notifications.WARNING;
                            TrayNotification tray = new TrayNotification("The user reached pending enquiry limit!", "Please try to forward enquiries to other users !", notification);
                            tray.setAnimation(Animations.FADE);
                            tray.showAndDismiss(Duration.seconds(2), popOver.getOwnerNode());
                            tray.setOnDismiss(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {
                                    //   popOver.hide();
                                }
                            });

                        }/* ======================== End Enquiry Limit ==================== */
//                        } else {
//                            System.out.println("His Flagged Some enquiries !");
//                        }/* ======================== End User Locked This Enquiry ==================== */

                    }
                    listCounselorDetailsBEAN.clear();
                    PauseTransition delay = new PauseTransition(Duration.seconds(2));
                    delay.setOnFinished((ActionEvent event1) -> {
                        if (role != null) {
                            if (role.equalsIgnoreCase("ROLE_COUNSELOR")) {
                                //    tableName.getItems().removeAll(listCounselorDetailsBEAN);
                                int c = 0;
                                for (TableColumn column : tableName.getColumns()) {
                                    c++;
                                    if (c == 2) {
                                        column.setCellFactory(new Callback<TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN>, TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN>>() {

                                            @Override
                                            public TableCell<CounselorDetailsBEAN, CounselorDetailsBEAN> call(TableColumn<CounselorDetailsBEAN, CounselorDetailsBEAN> param) {
                                                return new PriorityCell();
                                            }
                                        });
                                    }
                                }
                            }
                        }
                        popOver.hide();
                    });
                    delay.play();
                    /* ======================== End counselor Limit Check ==================== */
                } else {
                    lbleError.setText("Please Chooose Counselor First");
                    EffectsClass.fadeOut(lbleError, 2000.0);
                }

            }
        });
        return done;
    }

    /**
     *
     * @param counselorDetailsBEAN
     * @param popOver
     */
    public void initData(CounselorDetailsBEAN counselorDetailsBEAN, PopOver popOver) {
        System.out.println("data reached here :: " + counselorDetailsBEAN.getCompletionflag());
        CUR_ROLE = Context.getInstance().currentProfile().getProfilePOJO().getRole();
        lblWarning.setText("");
        if (CUR_ROLE.equalsIgnoreCase("ROLE_ADMIN")) {
            if (counselorDetailsBEAN.getImportant() != null) {
                if (counselorDetailsBEAN.getImportant().equalsIgnoreCase("1")) {
                    lblWarning.setText("Warning: This enquiry is flagged by " + counselorDetailsBEAN.getEnquiryAssignedTo());
                    lblWarning.setStyle("-fx-text-fill:red");
                }
            }
        }
        CUR_USERNAME = Context.getInstance().currentProfile().getProfilePOJO().getUsername();
        CUR_BRANCH = Context.getInstance().currentProfile().getProfilePOJO().getBranch();
        lbleError.setText("");

        /* ======================== Add Enquiry Forward Purposes ==================== */
        cmbPurpose.setItems(purposeDAO.listPurpose());
        List<String> branches = RetrieveStaticMasterDAO.getAllBranches();
        branches.stream().forEach((s) -> {
            branch.add(s);
        });
        cmbBranch.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue != null) {

                    ObservableList<CounselorsListPOJO> assignedCounselors = RetrieveStaticMasterDAO.getAllCounselors(newValue);
                    cmbCounselors.setItems(assignedCounselors);
                    for (CounselorsListPOJO listPOJO : cmbCounselors.getItems()) {
                        if (listPOJO.getUsername().equalsIgnoreCase(CUR_USERNAME)) {

                            Platform.runLater(new Runnable() {

                                @Override
                                public void run() {
                                    cmbCounselors.getItems().remove(listPOJO);
                                }

                            });
                        }
                    }
                }

            }
        });
        cmbBranch.setItems(branch);

        if (cmbBranch.getItems().contains(counselorDetailsBEAN.getBracnch())) {
            cmbBranch.getSelectionModel().select(cmbBranch.getItems().indexOf(counselorDetailsBEAN.getBracnch()));
            System.out.println("Current Counselor Branch :: " + counselorDetailsBEAN.getBracnch());
        }
        cmbSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lbleError.setText("");
                if (cmbCounselors.getSelectionModel().getSelectedIndex() > -1 && cmbBranch.getSelectionModel().getSelectedIndex() > -1 && cmbPurpose.getSelectionModel().getSelectedIndex() > -1) {
                    Map<String, String> mapCommonPoolConfig = CommonPoolService.retrieveCommonPoolConfig(cmbCounselors.getSelectionModel().getSelectedItem().getUsername(), cmbBranch.getSelectionModel().getSelectedItem(), "ROLE_COUNSELOR");
                    Map<String, String> mapFlagEnquiryStatus = CommonPoolService.checkFlagEnquiryStatus(cmbCounselors.getSelectionModel().getSelectedItem().getUsername(), cmbBranch.getSelectionModel().getSelectedItem(), "ROLE_COUNSELOR");

                    long flagLimitConfig = Long.parseLong(mapCommonPoolConfig.get("flag_limit"));
                    long flagLimitStatus = Long.parseLong(mapFlagEnquiryStatus.get("flag_limit"));
                    long enquiryLimitConfig = Long.parseLong(mapCommonPoolConfig.get("enquiry_limit"));
                    long enquiryLimitStatus = Long.parseLong(mapFlagEnquiryStatus.get("enquiry_limit"));
                    if (enquiryLimitConfig == 0 || enquiryLimitStatus <= enquiryLimitConfig) {
                        /* ======================== End Check counselor limits ==================== */
                        ForwardHistoryPOJO historyPOJO = new ForwardHistoryPOJO();
                        historyPOJO.setHid("his_" + UiiDGenerator.getUIID8());
                        historyPOJO.setAssigned_branch(CUR_BRANCH);
                        historyPOJO.setAssigned_by(CUR_USERNAME);
                        historyPOJO.setAssigned_date(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()).toString());
                        historyPOJO.setEnquiry_id(counselorDetailsBEAN.getEnquiryID());
                        historyPOJO.setAssigned_to(cmbCounselors.getSelectionModel().getSelectedItem().getUsername());
                        counselorDetailsBEAN.setEnquiryAssignedTo(cmbCounselors.getSelectionModel().getSelectedItem().getUsername());
                        historyPOJO.setAssigned_to_branch(cmbBranch.getSelectionModel().getSelectedItem());
                        counselorDetailsBEAN.setBracnch(cmbBranch.getSelectionModel().getSelectedItem());
                        historyPOJO.setHolded_by(counselorDetailsBEAN.getEnquiryAssignedTo());
                        historyPOJO.setPurpose(cmbPurpose.getSelectionModel().getSelectedItem().getPurpose());
                        historyPOJO.setRemarks(counselorDetailsBEAN.getRemarks());
                        if (counselorDetailsBEAN.getStatus() != null) {
                            historyPOJO.setCurrent_status(counselorDetailsBEAN.getStatus());
                        } else {
                            historyPOJO.setCurrent_status("Assessment Pending");
                        }
                        historyPOJO.setCompletionFlag(counselorDetailsBEAN.getCompletionflag());
                        historyPOJO.setImportantFlag("0");
                        historyPOJO.setStudy_required(counselorDetailsBEAN.getStudyRequired());
                        historyPOJO.setWork_required(counselorDetailsBEAN.getWorkRequired());
                        historyPOJO.setMigration_required(counselorDetailsBEAN.getMigrationRequired());
                        historyPOJO.setTraining_required(counselorDetailsBEAN.getTrainingRequired());
                        System.out.println("Test Forwarded Enquiry ID :: " + historyPOJO.getEnquiry_id());
                        forwardHistoryDAO.forwardEnquiry(historyPOJO);
                        cmbSubmit.setDisable(true);
                        lbleError.setText("Enquiry Assigned to " + cmbCounselors.getSelectionModel().getSelectedItem());
                        EffectsClass.fadeOut(lbleError, 2000.0);
                        PauseTransition delay = new PauseTransition(Duration.seconds(2));
                        delay.setOnFinished((ActionEvent event1) -> {
                            System.out.println("Hiding is working !!!");
                            popOver.hide();
                        });
                        delay.play();
                    } else {
                        System.out.println("His Limit Reached !");
                        Notification notification = Notifications.WARNING;
                        TrayNotification tray = new TrayNotification("The user reached pending enquiry limit!", "Please try to forward enquiries to other users !", notification);
                        tray.setAnimation(Animations.FADE);
                        tray.showAndDismiss(Duration.seconds(2), popOver.getOwnerNode());
                        tray.setOnDismiss(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                //   popOver.hide();
                            }
                        });
                    }/* ======================== End Enquiry Limit ==================== */

                }//hjkhjk
                else {
                    lbleError.setText("Please Chooose Counselor First");
                    EffectsClass.fadeOut(lbleError, 2000.0);
                }

            }
        });

    }

    /**
     *
     */
    public class PriorityCell extends TableCell {

        final VBox priorityvBox1 = new VBox();
        final HBox hboxPriority = new HBox();
        ImageView starimageView = new ImageView();
        ImageView importantimageView = new ImageView();
        Image yellowStarImage = new Image(FXMLCounselorMainViewController.class.getResourceAsStream("stary.png"));
        Image whiteStarImage = new Image(FXMLCounselorMainViewController.class.getResourceAsStream("starw.png"));
        Image yellowImrtantImage = new Image(FXMLCounselorMainViewController.class.getResourceAsStream("impy.png"));
        Image whiteImportantImage = new Image(FXMLCounselorMainViewController.class.getResourceAsStream("impw.png"));

        //   Tooltip tooltip = new Tooltip();
        {
            hboxPriority.getChildren().addAll(starimageView);
            hboxPriority.setAlignment(Pos.CENTER);
            setGraphic(hboxPriority);
        }

        @Override
        protected void updateItem(Object object, boolean empty) {
            super.updateItem(object, empty);
            if (!empty) {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                if (object != null) {
                    CounselorDetailsBEAN cpojo = (CounselorDetailsBEAN) object;
                    List<CounselorDetailsBEAN> counselorDetailsUpdateRating = new ArrayList<>();
                    counselorDetailsUpdateRating.add(cpojo);
                    inlineEditingDAO.updateStarStatus(counselorDetailsUpdateRating);
                    if (cpojo.getRating() == null || cpojo.getRating().equals("0")) {
                        starimageView.setImage(whiteStarImage);
                        starimageView.setFitWidth(16);
                        starimageView.setFitHeight(16);
                    } else {
                        starimageView.setImage(yellowStarImage);
                        starimageView.setFitWidth(16);
                        starimageView.setFitHeight(16);
                    }
//                    if (cpojo.getImportant().equals("0")) {
//                        importantimageView.setImage(whiteImportantImage);
//                        importantimageView.setFitWidth(20);
//                        importantimageView.setFitHeight(16);
//                    } else {
//                        importantimageView.setImage(yellowImrtantImage);
//                        importantimageView.setFitWidth(20);
//                        importantimageView.setFitHeight(16);
//                    }
                    //   getAllHyperlinkCounts();
                }
            } else {
                setText(null);
                setGraphic(null);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
