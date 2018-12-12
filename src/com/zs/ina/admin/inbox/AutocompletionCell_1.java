package com.zs.ina.admin.inbox;

import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

class AutocompletionCell_1 extends TableCell<CounselorDetailsBEAN, String> {

    private TextField textField;
    private final String[] assesment_status = {"Register Now", "Call Not Picking",
        "Call Attended & Call Later", "Not Completed Call Later", "Will Contact Future",
        "Not Interested", "Fake Caller", "Number Not Exist", "Confirm Soon"};

    public AutocompletionCell_1() {
    }

    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            createTextField();
            setText(null);
            setGraphic(textField);
            if (textField.isFocused() && !textField.getText().isEmpty()) {
                textField.selectAll();
            }
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
            if (textField != null) {
                textField.setText(getString());
            }
            setText(null);
            setGraphic(textField);
        } else {
            setText(getString());
            setGraphic(null);
        }
    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(textField, assesment_status);
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0,
                    Boolean arg1, Boolean arg2) {
                if (!arg2) {
                    commitEdit(textField.getText());
                }
            }
        });
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}
