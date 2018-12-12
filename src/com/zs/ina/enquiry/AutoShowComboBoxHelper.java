/*
 * Copyright ZoftSolutions(C) 2015 100018
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
package com.zs.ina.enquiry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ComboBox;
import javafx.util.Callback;

/**
 *
 * @author 100018
 */
public class AutoShowComboBoxHelper {

    /**
     *
     * @param comboBox
     * @param textBuilder
     */
    public AutoShowComboBoxHelper(final ComboBox<String> comboBox, final Callback<String, String> textBuilder) {
        final ObservableList<String> items = FXCollections.observableArrayList(comboBox.getItems());

        comboBox.getEditor().textProperty().addListener((ov, o, n) -> {
            if (n.equals(comboBox.getSelectionModel().getSelectedItem())) {
                return;
            }

            comboBox.hide();
            final FilteredList<String> filtered = items.filtered(s -> textBuilder.call(s).toLowerCase().contains(n.toLowerCase()));
            if (filtered.isEmpty()) {
                comboBox.getItems().setAll(items);
            } else {
                comboBox.getItems().setAll(filtered);
                comboBox.show();
            }
        });
    }
}