/*
 * Copyright (C) 2016 100018
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
package com.zs.ina.common.error;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author 100018
 */
public class FXMLSuccessViewerController implements Initializable {

    @FXML
    private ImageView imgSuccess;
    @FXML
    private Label lblTitleMessage;
    @FXML
    private Label lblTipz;
    @FXML
    private AnchorPane anchorMain;
    @FXML
    private VBox vboxMessages;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /**
     *
     */
    public void animateImage() {
        ScaleTransition st = new ScaleTransition(Duration.millis(2000), imgSuccess);
        st.setByX(0.3f);
        st.setByY(0.3f);
        st.setCycleCount(Timeline.INDEFINITE);
        st.cycleCountProperty().addListener(new ChangeListener<Number>() {
            
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("Test");
            }
        });
        st.setAutoReverse(true);
        
        st.play();
        
    }

    /**
     *
     * @param title
     * @param tips
     * @param popup
     */
    public void showSuccess(String title, String tips, Popup popup) {
        lblTitleMessage.setText("");
        if (title != null) {
            lblTitleMessage.setText(title);
        }
        lblTipz.setText("");
        if (tips != null) {
            lblTipz.setText(tips);
        }
        animateImage();
        imgSuccess.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                popup.hide();
            }
        });
        lblTitleMessage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                popup.hide();
            }
        });
        anchorMain.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                popup.hide();
            }
        });
        vboxMessages.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                popup.hide();
            }
        });
        lblTipz.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                popup.hide();
            }
        });
    }
}
