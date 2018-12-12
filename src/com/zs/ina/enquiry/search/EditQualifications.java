package com.zs.ina.enquiry.search;

import com.zs.ina.context.Context;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.enquiry.search.editing.FXMLQualificationsController;
import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.PopupWindow;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;

/**
 *
 * @author 100018
 */
public class EditQualifications extends TableCell<CounselorDetailsBEAN, String> {

    private final PopOver popmsg = new PopOver();
    private TextField textField;

    /**
     *
     */
    public EditQualifications() {
    }

    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            createTextField();
            setText(null);
            setGraphic(textField);
            textField.selectAll();
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
        textField.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                Pane myPane = new Pane();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/enquiry/search/editing/FXMLQualifications.fxml"));
                try {
                    Parent root = (Parent) loader.load();
                    // System.out.println(loader.getController());
                    FXMLQualificationsController controller = (FXMLQualificationsController) loader.getController();
                    Context.getInstance().currentProfile().setPopOver(popmsg);
                    myPane.getChildren().add(root);
                    popmsg.setContentNode(myPane);
                    popmsg.setArrowLocation(PopOver.ArrowLocation.LEFT_TOP);
                    popmsg.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_TOP_LEFT);
                    popmsg.setArrowSize(12.0);
                    popmsg.setHideOnEscape(true);
                    popmsg.setAutoFix(true);
                    popmsg.setAutoHide(true);
                    popmsg.setTitle("View/Edit Qualification Details ");
                    popmsg.setOnHiding(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent event) {
                            commitEdit(textField.getText());
                        }
                    });
                    if (popmsg.isShowing()) {
                        popmsg.hide();
                        popmsg.show(textField, textField.localToScreen(0, 0).getX() + 60, textField.localToScreen(0, 0).getY() + 10, Duration.millis(100));
                        popmsg.detach();
                    } else {
                        popmsg.show(textField, textField.localToScreen(0, 0).getX() + 60, textField.localToScreen(0, 0).getY() + 10, Duration.millis(100));
                        popmsg.detach();
                    }
                    //  popmsg.show(textField, 300, 400 + 30, Duration.millis(500));
                } catch (IOException exception) {
                    exception.printStackTrace();
                }

            }
        });
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0,
                    Boolean arg1, Boolean arg2) {
                if (!arg2) {
                    //    commitEdit(textField.getText());

                }
            }
        });
        textField.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    System.out.println("Enter key pressed !");
                    commitEdit(textField.getText());
                }
            }
        });
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}
