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

import com.zs.ina.assesment.FXMLAssesmentFormController;
import com.zs.ina.common.error.FXMLErrorViewerController;
import com.zs.ina.common.error.FXMLSuccessViewerController;
import com.zs.ina.common.error.FXMLWarningViewerController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;

/**
 *
 * @author 100018
 */
public class ShowPopupMessages {

    Popup popup = new Popup();

    /**
     *
     * @param message
     * @param node
     */
    public void showError(String message, Node node) {
        popup = new Popup();
        try {
            popup.setX(500);
            popup.setY(200);
            Pane myPane = new Pane();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/common/error/FXMLErrorViewer.fxml"));
            Parent root = (Parent) loader.load();
            FXMLErrorViewerController controller = (FXMLErrorViewerController) loader.getController();
            controller.showError(message, popup);
            myPane.getChildren().add(root);
//            if (popup.isShowing()) {
//                popup.hide();
//            }
            popup.getContent().addAll(myPane);
            popup.show(node.getScene().getWindow(), node.localToScreen(0, 0).getX() - 6, node.localToScreen(0, 0).getY() + 30);
            popup.setAutoHide(true);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAssesmentFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @param title
     * @param tips
     * @param node
     */
    public void showSuccess(String title, String tips, Node node) {
        popup = new Popup();
        try {
            popup.setX(500);
            popup.setY(200);
            Pane myPane = new Pane();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/common/error/FXMLSuccessViewer.fxml"));
            Parent root = (Parent) loader.load();
            FXMLSuccessViewerController controller = (FXMLSuccessViewerController) loader.getController();
            controller.showSuccess(title, tips, popup);
            myPane.getChildren().add(root);
//            if (popup.isShowing()) {
//                popup.hide();
//            }
            popup.getContent().addAll(myPane);
            popup.show(node.getScene().getWindow(), node.localToScreen(0, 0).getX() - 6, node.localToScreen(0, 0).getY() + 30);
            popup.setAutoHide(true);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAssesmentFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @param title
     * @param tips
     * @param node
     */
    public void showWarning(String title, String tips, Node node) {
        popup = new Popup();
        try {
            popup.setX(500);
            popup.setY(200);
            Pane myPane = new Pane();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/common/error/FXMLWarningViewer.fxml"));
            Parent root = (Parent) loader.load();
            FXMLWarningViewerController controller = (FXMLWarningViewerController) loader.getController();
            controller.showWarning(title, tips, popup);
            myPane.getChildren().add(root);
//            if (popup.isShowing()) {
//                popup.hide();
//            }
            popup.getContent().addAll(myPane);
            popup.show(node.getScene().getWindow(), node.localToScreen(0, 0).getX() - 6, node.localToScreen(0, 0).getY() + 30);
            popup.setAutoHide(true);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAssesmentFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
