package com.zs.ina.enquiry.search;

import com.zs.ina.context.Context;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.enquiry.search.editing.FXMLWorkRequiredController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
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
public class EditWorkRequiredCell extends TableCell<CounselorDetailsBEAN, String> {

    private final PopOver popmsg = new PopOver();
    private TextField textField;

    /**
     *
     */
    public EditWorkRequiredCell() {
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/enquiry/search/editing/FXMLWorkRequired.fxml"));
                try {
                    Parent root = (Parent) loader.load();
                    // System.out.println(loader.getController());
                    FXMLWorkRequiredController controller = (FXMLWorkRequiredController) loader.getController();
                    Context.getInstance().currentProfile().setPopOver(popmsg);
                    //    controller.initData(pojoo, popmsg);
                    myPane.getChildren().add(root);
                    popmsg.setContentNode(myPane);
                    popmsg.setArrowLocation(PopOver.ArrowLocation.LEFT_TOP);
                    popmsg.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_TOP_LEFT);
                    popmsg.setArrowSize(12.0);
                    popmsg.setHideOnEscape(true);
                    popmsg.setAutoFix(true);
                    popmsg.setAutoHide(true);
                    popmsg.setTitle("View/Edit Work Required Details ");
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
                } else if (event.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                } else if (event.getCode() == KeyCode.TAB) {
                    commitEdit(textField.getText());
                    TableColumn nextColumn = getNextColumn(!event.isShiftDown());
                    if (nextColumn != null) {
                        getTableView().edit(getTableRow().getIndex(), nextColumn);
                    }
                }
            }
        });
    }

    private TableColumn<CounselorDetailsBEAN, ?> getNextColumn(boolean forward) {
        List<TableColumn<CounselorDetailsBEAN, ?>> columns = new ArrayList<>();
        for (TableColumn<CounselorDetailsBEAN, ?> column : getTableView().getColumns()) {
            columns.addAll(getLeaves(column));
        }
        //There is no other column that supports editing.
        if (columns.size() < 2) {
            return null;
        }
        int currentIndex = columns.indexOf(getTableColumn());
        int nextIndex = currentIndex;
        if (forward) {
            nextIndex++;
            if (nextIndex > columns.size() - 1) {
                nextIndex = 0;
            }
        } else {
            nextIndex--;
            if (nextIndex < 0) {
                nextIndex = columns.size() - 1;
            }
        }
        return columns.get(nextIndex);
    }

    private List<TableColumn<CounselorDetailsBEAN, ?>> getLeaves(TableColumn<CounselorDetailsBEAN, ?> root) {
        List<TableColumn<CounselorDetailsBEAN, ?>> columns = new ArrayList<>();
        if (root.getColumns().isEmpty()) {
            //We only want the leaves that are editable.
            if (root.isEditable()) {
                columns.add(root);
            }
            return columns;
        } else {
            for (TableColumn<CounselorDetailsBEAN, ?> column : root.getColumns()) {
                columns.addAll(getLeaves(column));
            }
            return columns;
        }
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}
