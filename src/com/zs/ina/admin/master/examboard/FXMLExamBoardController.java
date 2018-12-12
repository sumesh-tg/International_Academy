/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.examboard;

import com.zs.ina.admin.master.examboard.dao.ExamBoardBean;
import com.zs.ina.admin.master.examboard.dao.ExamBoardDao;
import com.zs.ina.admin.master.excelupload.ExcelUpload;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author zoft
 */
public class FXMLExamBoardController implements Initializable {

    @FXML
    private TextField examBoardtxt;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private Button saveBtn;
    @FXML
    private Button exelUploadBtn;
    @FXML
    private TextField searchTxt;
    @FXML
    private TableColumn<?, ?> boardClmn;
    final ContextMenu usernameValidator = new ContextMenu();
    @FXML
    private HBox timingHbox;
    @FXML
    private TableView<ExamBoardBean> examBoardtbl;
    @FXML
    private TableColumn<?, ?> idClmn;
    private ObservableList<ExamBoardBean> masterSearchData = FXCollections.observableArrayList();
    private ExamBoardBean qualificationBean;
    private String qualificationId = "";

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        autoCompletion();
        addChangeEvents();
        tableInitMain();
        tableInit();
        examBoardtxt.focusedProperty().addListener(
                new ChangeListener<Boolean>() {
                    @Override
                    public void changed(
                            ObservableValue<? extends Boolean> arg0,
                            Boolean oldPropertyValue, Boolean newPropertyValue) {
                                if (newPropertyValue) {
                                    usernameValidator.hide();
                                }
                            }
                });
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        int row = ExamBoardDao.delete(qualificationId);
        if (row > 0) {

            tableInitMain();
            tableInit();
            searchTxt.setText("");
            clear();
        }
    }

    /**
     *
     */
    public void addChangeEvents() {
        examBoardtxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue.matches("[a-zA-Z ]*")) {
                    examBoardtxt.setText(newValue);
                } else {
                    examBoardtxt.setText(oldValue);
                }
            }
        });
    }

    @FXML
    private void clearHandle(ActionEvent event) {
        clear();
    }

    @FXML
    private void saveHandle(ActionEvent event) {
        qualificationBean = new ExamBoardBean();
        qualificationBean.setExamBoard(examBoardtxt.getText());
        // Context.getInstance().currentProfile().setBatchTimeBean(qualificationBean);
        if (validation()) {
            if (!qualificationId.equals("")) {
                int row = ExamBoardDao.update(qualificationId, qualificationBean);
                if (row > 0) {
                    tableInitMain();

                    clear();
                }
            } else {
                if (!ExamBoardDao.checkDuplicateEntry(examBoardtxt.getText())) {
                    int row = ExamBoardDao.insert(qualificationBean);
                    if (row > 0) {
                        tableInitMain();

                        clear();
                    }
                } else {
                    usernameValidator.getItems().clear();
                    usernameValidator.getItems().add(
                            new MenuItem("Exam board already inserted! "));
                    usernameValidator.show(examBoardtxt, Side.RIGHT, 10, 0);
                }
            }
        }
    }

    @FXML
    private void handleExelUpload(ActionEvent event) {
        try {
            ExcelUpload excelUpload = new ExcelUpload();
            excelUpload.exelFileChooser("mastertbl_exam_board");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLExamBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void qualFieldTblMouseClicked(MouseEvent event) {
        saveBtn.setText("Update");
        ExamBoardBean qualificationBean = (ExamBoardBean) examBoardtbl.getSelectionModel().getSelectedItem();
        examBoardtxt.setText(qualificationBean.getExamBoard());
        qualificationId = qualificationBean.getId();
        deleteBtn.setVisible(true);
    }

    private void autoCompletion() {
        List<String> allBoards = ExamBoardDao.getAllBoards();
        examBoardtxt = TextFields.createClearableTextField();
        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(examBoardtxt, allBoards);
        examBoardtxt.setPrefWidth(200);
        timingHbox.getChildren().remove(0);
        timingHbox.getChildren().add(examBoardtxt);
        deleteBtn.setVisible(false);
    }

    private void tableInitMain() {
        List<ExamBoardBean> branchList = ExamBoardDao.getBoarddetails();
        idClmn.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        boardClmn.setCellValueFactory(
                new PropertyValueFactory<>("examBoard"));
//     batchTimeTbl.getItems().setAll(branchList);
        masterSearchData.removeAll(masterSearchData);
        for (ExamBoardBean b : branchList) {
            masterSearchData.add(b);
        }
        examBoardtbl.setItems(masterSearchData);
    }

    private void tableInit() {
        List<ExamBoardBean> branchList = ExamBoardDao.getBoarddetails();
        idClmn.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        boardClmn.setCellValueFactory(
                new PropertyValueFactory<>("examBoard"));

        masterSearchData.removeAll(masterSearchData);
        for (ExamBoardBean bp : branchList) {
            masterSearchData.add(bp);
        }
        FilteredList<ExamBoardBean> filteredData = new FilteredList<>(masterSearchData, p -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                // NoticePOJO noticePOJO=new NoticePOJO();
                if (enquiry.getExamBoard().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches contact name.
                }
//                else if (enquiry.getAddress().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter phone.
//                }
                return false; // Does not match.
            });
            SortedList<ExamBoardBean> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(examBoardtbl.comparatorProperty());
            examBoardtbl.setItems(sortedData);
        });
    }

    private boolean validation() {
        boolean f = true;
        if (examBoardtxt.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter board name "));
            usernameValidator.show(examBoardtxt, Side.RIGHT, 10, 0);
            f = false;
        }
        return f;
    }

    private void clear() {
        saveBtn.setText("Save");
        examBoardtxt.setText("");
        searchTxt.setText("");
    }

}
