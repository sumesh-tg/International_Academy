/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.common;

import com.sun.javafx.scene.control.skin.ProgressIndicatorSkin;
import com.zs.traynotification.notification.Notification;
import com.zs.traynotification.notification.Notifications;
import com.zs.traynotification.notification.TrayNotification;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.css.PseudoClass;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author zsuser1
 */
public class ShowProgress {

    /**
     *
     * @param makeParent
     * @param message
     */
    public void showProgressIndicator(Node makeParent, String message) {
        //indicator

        VBox root = new VBox();

        ProgressIndicator pi = new ProgressIndicator();
        Task<Void> counter = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                for (int i = 1; i <= 108; i++) {
                    Thread.sleep(15);
                    updateProgress(i, 100);
                }
                return null;
            }
        };
        pi.progressProperty().bind(counter.progressProperty());
        ProgressIndicatorSkin indicatorSkin = new ProgressIndicatorSkin(pi);

        pi.progressProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number newValue) -> {
            PseudoClass warning = PseudoClass.getPseudoClass("warning");
            PseudoClass critical = PseudoClass.getPseudoClass("critical");
            if (newValue.doubleValue() >= 1) {
                pi.applyCss();
                Text text = (Text) pi.lookup(".text.percentage");
                text.setStyle("-fx-text-fill:green");
                text.setText(message);
            }
            if (newValue.doubleValue() < 0.3) {
                pi.pseudoClassStateChanged(warning, false);
                pi.pseudoClassStateChanged(critical, true);
            } else if (newValue.doubleValue() < 0.65) {
                pi.pseudoClassStateChanged(warning, true);
                pi.pseudoClassStateChanged(critical, false);
            } else {
                pi.pseudoClassStateChanged(warning, false);
                pi.pseudoClassStateChanged(critical, false);
            }

        });
        // pi.setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
        pi.setPrefWidth(100);
        pi.setPrefHeight(100);
        pi.skinProperty().set(indicatorSkin);
        root.setStyle(" -fx-border-color:#004A96;");
        root.getChildren().add(pi);
        Scene scene = new Scene(root, Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(getClass().getResource("progress.css").toExternalForm());

        Stage secondStage = new Stage();
        secondStage.setScene(scene);
        secondStage.initStyle(StageStyle.TRANSPARENT);
        secondStage.initModality(Modality.WINDOW_MODAL);
        secondStage.initOwner(makeParent.getScene().getWindow());
        secondStage.show();

        new Thread(counter).start();
        counter.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent event) {
                Stage closestage = (Stage) pi.getScene().getWindow();
                closestage.close();
            }
        });

        //end indicator
    }

    /**
     *
     * @param makeParent
     * @param message
     * @param notification1
     * @param notification2
     */
    public void showProgressIndicator(Node makeParent, String message, String notification1, String notification2) {
        //indicator

        VBox root = new VBox();

        ProgressIndicator pi = new ProgressIndicator();
        Task<Void> counter = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                for (int i = 1; i <= 108; i++) {
                    Thread.sleep(15);
                    updateProgress(i, 100);
                }
                return null;
            }
        };
        pi.progressProperty().bind(counter.progressProperty());
        ProgressIndicatorSkin indicatorSkin = new ProgressIndicatorSkin(pi);

        pi.progressProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number newValue) -> {
            PseudoClass warning = PseudoClass.getPseudoClass("warning");
            PseudoClass critical = PseudoClass.getPseudoClass("critical");
            if (newValue.doubleValue() >= 1) {
                pi.applyCss();
                Text text = (Text) pi.lookup(".text.percentage");
                text.setStyle("-fx-text-fill:green");
                text.setText(message);
            }
            if (newValue.doubleValue() < 0.3) {
                pi.pseudoClassStateChanged(warning, false);
                pi.pseudoClassStateChanged(critical, true);
            } else if (newValue.doubleValue() < 0.65) {
                pi.pseudoClassStateChanged(warning, true);
                pi.pseudoClassStateChanged(critical, false);
            } else {
                pi.pseudoClassStateChanged(warning, false);
                pi.pseudoClassStateChanged(critical, false);
            }

        });
        // pi.setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
        pi.setPrefWidth(100);
        pi.setPrefHeight(100);
        pi.skinProperty().set(indicatorSkin);
        root.setStyle(" -fx-border-color:#004A96;");
        root.getChildren().add(pi);
        Scene scene = new Scene(root, Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(getClass().getResource("progress.css").toExternalForm());

        Stage secondStage = new Stage();
        secondStage.setScene(scene);
        secondStage.initStyle(StageStyle.TRANSPARENT);
        secondStage.initModality(Modality.WINDOW_MODAL);
        secondStage.initOwner(makeParent.getScene().getWindow());
        secondStage.show();

        new Thread(counter).start();
        counter.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent event) {
                Stage closestage = (Stage) pi.getScene().getWindow();
                closestage.close();
                Notification notification = Notifications.SUCCESS;
                TrayNotification tray = new TrayNotification(notification1, notification2, notification);
                tray.showAndDismiss(Duration.seconds(3), makeParent);
            }
        });

        //end indicator
    }
}
