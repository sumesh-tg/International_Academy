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
package com.zs.ina.common;

import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class ComboBoxJumpToChar {

    /**
     *
     * @param comboBox
     */
    public static void jumpToChar(ComboBox comboBox) {
        comboBox.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (comboBox.getValue() != null) {
                    String s = jumpTo(event.getText(), comboBox.getValue().toString(), comboBox.getItems());
                    if (s != null) {
                        comboBox.setValue(s);
                    }
                } else {
                    String s = jumpTo(event.getText(), "", comboBox.getItems());
                    if (s != null) {
                        comboBox.setValue(s);
                    }
                }
            }
        });
    }

    static String jumpTo(String keyPressed, String currentlySelected, List<String> items) {
        String key = keyPressed.toUpperCase();
        if (key.matches("^[A-Z]$")) {
            // Only act on letters so that navigating with cursor keys does not
            // try to jump somewhere.
            boolean letterFound = false;
            boolean foundCurrent = currentlySelected == null;
            for (String s : items) {
                if (s != null) {
                    if (s.toUpperCase().startsWith(key)) {
                        letterFound = true;
                        if (foundCurrent) {
                            return s;
                        }
                        foundCurrent = s.equals(currentlySelected);
                    }
                }
            }
            if (letterFound) {
                return jumpTo(keyPressed, null, items);
            }
        } else if (key.matches("[0-9]")) {
            System.out.println("No pressed");
            boolean letterFound = false;
            boolean foundCurrent = currentlySelected == null;
            for (String s : items) {
                if (s != null) {
                    if (s.startsWith(key)) {
                        letterFound = true;
                        if (foundCurrent) {
                            return s;
                        }
                        foundCurrent = s.equals(currentlySelected);
                    }
                }
            }
            if (letterFound) {
                return jumpTo(keyPressed, null, items);
            }

        }
        return null;
    }
}
