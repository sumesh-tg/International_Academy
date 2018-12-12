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
package com.zs.ina.visa;

import com.zs.ina.admission.*;
import com.zs.ina.common.inbox.retrieve.InboxRetrievalService;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class FXMLVisaProcessingController implements Initializable {

    @FXML
    private ListView<CounselorDetailsBEAN> lstAdmissionInbox;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /* ======================== Test Admission Inbox ==================== */
        Map<String, List<CounselorDetailsBEAN>> listData = InboxRetrievalService.getCommonPoolEnquiries("Kollam", "a", "ROLE_COUNSELOR");
        addInvitationsIntoListViewInvitations(listData.get("study"));
        addInvitationsIntoListViewInvitations(listData.get("study"));
        addInvitationsIntoListViewInvitations(listData.get("study"));
    }

    private void addInvitationsIntoListViewInvitations(List<CounselorDetailsBEAN> invitationsList) {
        System.out.println("test data size :: " + invitationsList.size());
        lstAdmissionInbox.setCellFactory(new Callback<ListView<CounselorDetailsBEAN>, ListCell<CounselorDetailsBEAN>>() {

            @Override
            public ListCell<CounselorDetailsBEAN> call(ListView<CounselorDetailsBEAN> param) {
                final ListCell<CounselorDetailsBEAN> cell = new ListCell<CounselorDetailsBEAN>() {
                    @Override
                    protected void updateItem(CounselorDetailsBEAN cdbean, boolean empty) {
                        super.updateItem(cdbean, empty);
                        if (empty) {
                            setText(null);
                            setGraphic(null);
                        } else if (cdbean != null) {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/admission/FXMLRows.fxml"));
                                Parent root = (Parent) loader.load();
                                FXMLRowsController controller = (FXMLRowsController) loader.getController();
                                controller.initData(cdbean);
                                AnchorPane anchorPane = new AnchorPane();
                                anchorPane.getChildren().add(root);
                                setGraphic(root);
                            } catch (IOException sqle) {
//                                logger.error(sqle.toString());
                                sqle.printStackTrace();
                            }
                        }
                        /* ======================== Context Menu ==================== */
                    }
                };
                final ContextMenu contextMenu = new ContextMenu();
                CounselorDetailsBEAN aN = (CounselorDetailsBEAN) cell.getItem();

                final MenuItem itemCancel = new MenuItem("Cancel this application");
//                 editItem.textProperty().bind(Bindings.format("Edit \"%s\"", cell.getItem().contactNameProperty()));
                itemCancel.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Test :: " + cell.getItem());
                    }
                });
                MenuItem forwardItem = new MenuItem("Forward");
                //   deleteItem.textProperty().bind(Bindings.format("Delete \"%s\"", cell.itemProperty()));
                forwardItem.setOnAction(event -> lstAdmissionInbox.getItems().remove(cell.getItem()));
                contextMenu.getItems().addAll(itemCancel, forwardItem);

                //    cell.setContextMenu(contextMenu);
                cell.contextMenuProperty().bind(
                        Bindings.when(cell.emptyProperty())
                        .then((ContextMenu) null)
                        .otherwise(contextMenu));
                return cell;
            }

        });
        lstAdmissionInbox.getItems().addAll(invitationsList);
    }
}
