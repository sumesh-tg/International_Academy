/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

/**
 *
 * @author zsuser1
 */
public class ChangeDateFormat {

    /**
     *
     * @param datePicker
     */
    public static void datePickerDateFormatter(DatePicker datePicker) {
        String pattern = "yyyy-MM-dd";

        StringConverter converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter
                    = DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };

        datePicker.setConverter(converter);
        datePicker.setPromptText(pattern.toLowerCase());
       
    }
}
