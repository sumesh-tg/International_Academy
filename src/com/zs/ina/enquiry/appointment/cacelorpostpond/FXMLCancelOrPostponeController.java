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
package com.zs.ina.enquiry.appointment.cacelorpostpond;

import com.zs.ina.context.Context;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleBEAN;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleIMPL;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleSERVICE;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import org.apache.log4j.Logger;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLCancelOrPostponeController implements Initializable {

    @FXML
    private ListView<AppointmentScheduleBEAN> listViewScheduledEvents;
    @FXML
    private Button btnClose;

    String CUR_USERNAME = "";
    String CUR_BRANCH = "";
    Logger logger = Logger.getLogger(FXMLCancelOrPostponeController.class);
    AppointmentScheduleSERVICE appointmentScheduleSERVICE = new AppointmentScheduleSERVICE(new AppointmentScheduleIMPL());
    ObservableList<AppointmentScheduleBEAN> scheduledList = FXCollections.observableArrayList();
    PopOver popOver = new PopOver();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        CUR_USERNAME = Context.getInstance().currentProfile().getProfilePOJO().getUsername();
        CUR_BRANCH = Context.getInstance().currentProfile().getProfilePOJO().getBranch();
        scheduledList.clear();
        scheduledList = appointmentScheduleSERVICE.retrieveScheduledEvents(CUR_USERNAME, CUR_BRANCH);
        System.out.println("Test List :: " + scheduledList.toString());
        addScheduledIntoListViewScheduledEvents(scheduledList);
        /* =================== Close button ================== */
        btnClose.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                popOver.hide();
            }
        });

    }

    /**
     *
     * @param popOver
     */
    public void passPopOverObject(PopOver popOver) {
        this.popOver = popOver;
        popOver.setAutoHide(false);
    }

    /**
     *
     * @param scheduledList
     */
    public void addScheduledIntoListViewScheduledEvents(ObservableList<AppointmentScheduleBEAN> scheduledList) {
        listViewScheduledEvents.setCellFactory(new Callback<ListView<AppointmentScheduleBEAN>, ListCell<AppointmentScheduleBEAN>>() {

            @Override
            public ListCell<AppointmentScheduleBEAN> call(ListView<AppointmentScheduleBEAN> param) {

                final ListCell<AppointmentScheduleBEAN> cell = new ListCell<AppointmentScheduleBEAN>() {
                    @Override
                    protected void updateItem(AppointmentScheduleBEAN appointmentScheduleBEAN, boolean empty) {
                        super.updateItem(appointmentScheduleBEAN, empty);
                        if (appointmentScheduleBEAN != null) {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/enquiry/appointment/cacelorpostpond/FXMLSingleEventCancelView.fxml"));
                                Parent root = (Parent) loader.load();
                                FXMLSingleEventCancelViewController controller = (FXMLSingleEventCancelViewController) loader.getController();
                                appointmentScheduleBEAN.setParticipantId(CUR_USERNAME);
                                controller.passEventDetails(appointmentScheduleBEAN);
                                AnchorPane anchorPane = new AnchorPane();
                                anchorPane.getChildren().add(root);

                                setGraphic(anchorPane);
                                appointmentScheduleBEAN.eventCancelProperty().addListener(new ChangeListener<String>() {

                                    @Override
                                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                        if (newValue != null) {
                                             System.out.println("inside change eventCancelProperty()");
                                            listViewScheduledEvents.getItems().removeAll(appointmentScheduleBEAN);

                                        }

                                    }
                                });
//                                appointmentScheduleBEAN.eventRejectProperty().addListener(new ChangeListener<String>() {
//
//                                    @Override
//                                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                                        //    System.out.println("inside change reject_property");
//                                        listViewScheduledEvents.getItems().removeAll(appointmentScheduleBEAN);
//                                    }
//                                });

                            } catch (IOException sqle) {
                                logger.info(sqle.toString());
                                sqle.printStackTrace();

                            }

                        } else {
                            setGraphic(null);
                        }
                    }
                };
                return cell;
            }

        });
        listViewScheduledEvents.getItems().addAll(scheduledList);
    }

}
