package com.zs.ina.enquiry.search;

import com.zs.ina.common.ChangeDateFormat;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import java.time.LocalDate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author 100018
 */
public class EditDateOfJoin extends TableCell<CounselorDetailsBEAN, String> {

    private DatePicker datePicker;

    /**
     *
     */
    public EditDateOfJoin() {
    }

    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            createTextField();
            setText(null);
            setGraphic(datePicker);
            // datePicker.selectAll();
        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();

        setText((String) getItem());
        setGraphic(null);
    }

    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else if (isEditing()) {
            if (datePicker != null) {
                datePicker.setValue(LocalDate.parse(getString()));
            }
            setText(null);
            setGraphic(datePicker);
        } else {
            setText(item);
            setGraphic(null);
        }
    }

    private void createTextField() {
        datePicker = new DatePicker(LocalDate.parse(getString()));
        datePicker.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        ChangeDateFormat.datePickerDateFormatter(datePicker);
        datePicker.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0,
                    Boolean arg1, Boolean arg2) {
                if (!arg2) {
                    //    commitEdit(textField.getText());

                }
            }
        });
        datePicker.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    System.out.println("Enter key pressed !");
                    commitEdit(datePicker.getValue().toString());
                }
            }
        });
        datePicker.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println("hiiiii");
            }
        });
        datePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {

            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if (newValue != null) {
                    commitEdit(datePicker.getValue().toString());
                }
            }
        });
    }

    private String getString() {
        return getItem() == null ? "" : getItem();
    }
}
