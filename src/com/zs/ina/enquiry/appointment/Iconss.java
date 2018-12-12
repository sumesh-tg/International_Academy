/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.enquiry.appointment;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.util.Duration;

/**
 *
 * @author 100018
 */
public class Iconss extends Button {

private final Node icon;

private AnimationType type;

    /**
     *
     * @param icon
     */
    public Iconss(Node icon) {
   // setText(text);
    setGraphic(icon);
    this.icon = icon;
  //  this.type = type;
    //addAnimation();
}

private void addBlinkAnimation() {
    final Timeline timeline = new Timeline();
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.setAutoReverse(true);
    final KeyValue kv = new KeyValue(icon.opacityProperty(), 0.0);
    final KeyFrame kf = new KeyFrame(Duration.millis(700), kv);
    timeline.getKeyFrames().add(kf);
    timeline.play();
}

private void addJumpAnimation() {
    final TranslateTransition translateTransition = new TranslateTransition(Duration.millis(200), icon);
    final double start = 0.0;
    final double end = start - 4.0;
    translateTransition.setFromY(start);
    translateTransition.setToY(end);
    translateTransition.setCycleCount(-1);
    translateTransition.setAutoReverse(true);
    translateTransition.setInterpolator(Interpolator.EASE_BOTH);
    translateTransition.play();
}

    /**
     *
     */
    public enum AnimationType {

    /**
     *
     */
    BLINK,

    /**
     *
     */
    JUMP,

    /**
     *
     */
    NONE;
};

private void addAnimation() {
    switch (type) {
        case BLINK:
            addBlinkAnimation();
            break;
        case JUMP:
            addJumpAnimation();
            break;
        case NONE:
            break;
        default:
            break;
    }
}}