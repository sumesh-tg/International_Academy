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
package com.zs.ina.admin.master.threaddelay;

import com.zs.ina.admin.master.threaddelay.dao.ThreadDelayIMPL;
import com.zs.ina.admin.master.threaddelay.dao.ThreadDelaySERVICE;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.traynotification.notification.Notification;
import com.zs.traynotification.notification.Notifications;
import com.zs.traynotification.notification.TrayNotification;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLThreadDelayController implements Initializable {

    @FXML
    private TextField txtSeconds;
    @FXML
    private Button btnSave;
    ThreadDelaySERVICE threadDelaySERVICE = new ThreadDelaySERVICE(new ThreadDelayIMPL());
    ShowPopupMessages showPopupMessages = new ShowPopupMessages();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        showPreviousSeconds();
        btnSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //String second=txtSeconds.getText();
                if (validateAllFields()) {
                    int seconds=Integer.parseInt(txtSeconds.getText());
                    int result_insert = threadDelaySERVICE.insertThreadDelay(seconds);
                    if (result_insert > 0) {

                        Notification notification = Notifications.SUCCESS;
                        TrayNotification trayNotification = new TrayNotification("Refresh Seconds saved", "Refresh seconds saved successfully", notification);
                        trayNotification.showAndDismiss(Duration.seconds(3),txtSeconds);

                    } else {
                        Notification notification = Notifications.ERROR;
                        TrayNotification trayNotification = new TrayNotification("Refresh Seconds not saved", "Refresh seconds not saved correctly", notification);
                        trayNotification.showAndDismiss(Duration.seconds(3),txtSeconds);

                    }

                }
            }

            private boolean validateAllFields() {
                boolean flag = true;
                if (txtSeconds.getText() == null || txtSeconds.getText().equals("")) {
                    showPopupMessages.showError("Enter refresh seconds", txtSeconds);
                    flag = false;
                }
               else if (Integer.parseInt(txtSeconds.getText()) < 5 ) {
                    showPopupMessages.showError("Enter a minimum value of 5", txtSeconds);
                    flag = false;
                }
                return flag;
            }

        });
        txtSeconds.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {

                    if ((newValue.matches("[0-9]*") && newValue.length() <= 2)) {
                        txtSeconds.setText(newValue);
                    } else {
                        txtSeconds.setText(oldValue);
                    }
                }
            }
        });
    }

    private void showPreviousSeconds() {
      
     String result_previous_second = threadDelaySERVICE.retrieveThreadDelay();
     if(result_previous_second.equalsIgnoreCase("")){
         txtSeconds.setText("");
     }
     else{
         txtSeconds.setText(result_previous_second);
     }
    }

}
