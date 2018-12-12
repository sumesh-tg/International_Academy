/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.common;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class TextAreaGrowShrink extends Application {

    private Text textHolder = new Text();
    private double oldHeight = 0;

    @Override
    public void start(Stage primaryStage) {
        final TextArea textArea = new TextArea();
        textArea.setPrefSize(200, 40);
        textArea.setWrapText(true);
        textArea.setMaxHeight(200);
        textHolder.textProperty().bind(textArea.textProperty());
        textHolder.layoutBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
                if (oldHeight != newValue.getHeight()) {
                    System.out.println("newValue = " + newValue.getHeight());
                    oldHeight = newValue.getHeight();
                    textArea.setPrefHeight(textHolder.getLayoutBounds().getHeight() + 20); // +20 is for paddings
                }
            }
        });

        Group root = new Group(textArea);
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();

        //  See the explanation below of the following line. 
        //  textHolder.setWrappingWidth(textArea.getWidth() - 10);  // -10 for left-right padding. Exact value can be obtained from caspian.css
    }

    /**
     *
     * @param textArea
     */
    public static void makeGrow(TextArea textArea) {
        Text textHolderInner = new Text();
        double oldHeightInner = 0;
        textArea.setPrefSize(200, 40);
        textArea.setWrapText(true);
        textArea.setMaxHeight(200);
        textHolderInner.textProperty().bind(textArea.textProperty());
        textHolderInner.layoutBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
                if (oldHeightInner != newValue.getHeight()) {
                    System.out.println("newValue = " + newValue.getHeight());
                    assign(oldHeightInner, newValue.getHeight());
                    textArea.setPrefHeight(textHolderInner.getLayoutBounds().getHeight() + 20); // +20 is for paddings
                }
            }
        });

    }

    public static void assign(double oldHeightInner, double newHeight) {
        oldHeightInner = newHeight;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
