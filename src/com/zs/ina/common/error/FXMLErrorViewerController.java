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
public class FXMLErrorViewerController implements Initializable {

    @FXML
    private Label lblError;
    @FXML
    private ImageView imgViewError;
    @FXML
    private AnchorPane anchorMain;
    @FXML
    private VBox vboxMessage;
    @FXML
    private Label lblTitle;

    /**
     * Initializes the controller class.
     *
     * @param message
     * @param popup
     */
    public void showError(String message, Popup popup) {
        lblError.setText("");
        if (message != null) {
            lblError.setText(message);
        }
        animateImage();
        imgViewError.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                popup.hide();
            }
        });
        lblError.setOnMouseClicked(new EventHandler<MouseEvent>() {

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
        vboxMessage.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                popup.hide();
            }
        });
        lblTitle.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                popup.hide();
            }
        });
    }

    /**
     *
     */
    public void animateImage() {
        ScaleTransition st = new ScaleTransition(Duration.millis(2000), imgViewError);
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
