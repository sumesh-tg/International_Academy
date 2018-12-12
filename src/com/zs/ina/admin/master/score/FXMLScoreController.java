/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.score;

import com.zs.ina.admin.master.excelupload.ExcelUpload;
import com.zs.ina.admin.master.score.dao.ScoreBEAN;
import com.zs.ina.admin.master.score.dao.ScoreDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLScoreController implements Initializable {

    @FXML
    private HBox scoreHBox;
    @FXML
    private TextField scoreTxt;
    @FXML
    private Button excelUploadBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private Button saveBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private TextField searchTxt;
    @FXML
    private TableView<ScoreBEAN> scoreTbl;
    @FXML
    private TableColumn<?, ?> scoreClmn;
    @FXML
    private TableColumn<?, ?> idClmn;
    private String scoreId = "";
    final ContextMenu usernameValidator = new ContextMenu();
    private ScoreBEAN scoreBEAN;
    private ObservableList<ScoreBEAN> masterSearchData = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        autoCompletion();
        setTableValues();
        serchValues();
        addChangeEvents();
    }

    @FXML
    private void handleBranchTxt(MouseEvent event) {
    }

    @FXML
    private void handlebtnExcelUpload(ActionEvent event) throws SQLException {
        ExcelUpload excelUpload = new ExcelUpload();
        excelUpload.exelFileChooser("mastertbl_score_management");
        setTableValues();
    }

    @FXML
    private void handleClearBtn(ActionEvent event) {
        clear();
    }

    @FXML
    private void handleSaveBtn(ActionEvent event) {
        scoreBEAN = new ScoreBEAN();
        scoreBEAN.setScore(scoreTxt.getText());
        scoreBEAN.setId(scoreId);
        if (validation()) {
            if (!scoreId.equals("")) {
                int row = ScoreDAO.updateScore(scoreBEAN);
                if (row > 0) {
                    setTableValues();
                    clear();
                }
            } else {
                if (!ScoreDAO.checkDuplicateEntry(scoreTxt.getText())) {
                    int row = ScoreDAO.insertScore(scoreBEAN);
                    if (row > 0) {
                        setTableValues();
                        clear();
                    }
                } else {
                    usernameValidator.getItems().clear();
                    usernameValidator.getItems().add(
                            new MenuItem("Score already inserted! "));
                    usernameValidator.show(scoreTxt, Side.RIGHT, 10, 0);
                }
            }
        }
    }

    /**
     *
     */
    public void addChangeEvents() {
        scoreTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue.matches("[0-9.]*")) {
                    scoreTxt.setText(newValue);
                } else {
                    scoreTxt.setText(oldValue);
                }
            }
        });
    }

    @FXML
    private void handlebtnDelete(ActionEvent event) {
        ScoreDAO.deleteScore(scoreBEAN.getId());
        setTableValues();
        searchTxt.setText("");
        clear();
    }

    @FXML
    private void handleScoreTblMouseClicked(MouseEvent event) {
        saveBtn.setText("Update");
        scoreBEAN = (ScoreBEAN) scoreTbl.getSelectionModel().getSelectedItem();
        scoreTxt.setText(scoreBEAN.getScore());
        scoreId = scoreBEAN.getId();
    }

    @FXML
    private void handleScoreblReleased(KeyEvent event) {
        saveBtn.setText("Update");
        scoreBEAN = (ScoreBEAN) scoreTbl.getSelectionModel().getSelectedItem();
        scoreTxt.setText(scoreBEAN.getScore());
        scoreId = scoreBEAN.getId();
    }

    private void clear() {
        scoreTxt.setText("");
        scoreId = "";
        saveBtn.setText("Save");
    }

    /**
     *
     * @return
     */
    public boolean validation() {
        boolean f = true;
        if (scoreTxt.getText().equals("")) {
            usernameValidator.getItems().clear();
            usernameValidator.getItems().add(
                    new MenuItem("Please enter score field "));
            usernameValidator.show(scoreTxt, Side.RIGHT, 10, 0);
            f = false;
        }
        return f;
    }

    /**
     *
     */
    public void autoCompletion() {
        List<String> allScores = ScoreDAO.getAllScores();
        scoreTxt = TextFields.createClearableTextField();
        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(scoreTxt, allScores);
        scoreTxt.setPrefWidth(200);
        scoreHBox.getChildren().remove(0);
        scoreHBox.getChildren().add(scoreTxt);
    }

    /**
     *
     */
    public void setTableValues() {
        ObservableList<ScoreBEAN> scoreBEANs = ScoreDAO.getScoreDetails();
        scoreClmn.setCellValueFactory(new PropertyValueFactory<>("score"));
        idClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        masterSearchData.removeAll(masterSearchData);
        for (ScoreBEAN bp : scoreBEANs) {
            masterSearchData.add(bp);
        }
        scoreTbl.setItems(masterSearchData);
    }

    /**
     *
     */
    public void serchValues() {
        ObservableList<ScoreBEAN> scoreBEANs = ScoreDAO.getScoreDetails();
        scoreClmn.setCellValueFactory(new PropertyValueFactory<>("score"));
        idClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        masterSearchData.removeAll(masterSearchData);
        for (ScoreBEAN bp : scoreBEANs) {
            masterSearchData.add(bp);
        }

        FilteredList<ScoreBEAN> filteredData = new FilteredList<>(masterSearchData, p -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (enquiry.getScore().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches contact name.
                }
                return false; // Does not match.
            });
            SortedList<ScoreBEAN> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(scoreTbl.comparatorProperty());
            scoreTbl.setItems(sortedData);
        });
    }

}
