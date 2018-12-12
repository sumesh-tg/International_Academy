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
package com.zs.ina.search.home;

import com.zs.ina.context.Context;
import com.zs.ina.login.INALoginForm;
import com.zs.ina.search.colleges.courses.FXMLCollegeCoursesController;
import com.zs.ina.search.colleges.dao.CourseReqmtBasicDAO;
import com.zs.ina.search.colleges.dao.EduProviderBEAN;
import com.zs.ina.search.colleges.dao.EduProviderDAO;
import com.zs.ina.search.employer.dao.EmployerBEAN;
import com.zs.ina.search.employer.dao.EmployerDAO;
import com.zs.ina.search.employer.dao.EmployerVacancyDAO;
import com.zs.ina.search.employer.vacancy.FXMLEmployerVacancyController;
import com.zs.ina.search.migration.FXMLMigrationController;
import com.zs.ina.search.migration.dao.MigrationBasicBEAN;
import com.zs.ina.search.migration.dao.MigrationBasicDAO;
import com.zs.ina.search.training.courses.FXMLTrainingCourseRequirementsController;
import com.zs.ina.search.training.courses.TrainReqmtBasicDAO;
import com.zs.ina.search.training.dao.TrainingBEAN;
import com.zs.ina.search.training.dao.TrainingDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import org.apache.log4j.Logger;
import org.controlsfx.control.table.TableFilter;
import org.controlsfx.control.table.TableRowExpanderColumn;
import org.springframework.context.ApplicationContext;

/**
 * FXML Controller class
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class FXMLSearchHomeController implements Initializable {

    @FXML
    private TabPane tabPaneSearchHome;
    /* ======================== Dynamic Tabs ==================== */
    Tab tabEmployer = new Tab("Add Employer");
    @FXML
    private MenuItem itemEmployerView;
    static Logger logger = Logger.getLogger(FXMLSearchHomeController.class);
    @FXML
    private MenuItem itemEmplpyerAddCountryLoc;
    /* ======================== Spring Context ==================== */
    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    /* ======================== Search Beans ==================== */
    EmployerDAO employerDAO = springAppContext.getBean(EmployerDAO.class);
    EmployerVacancyDAO employerVacancyDAO = springAppContext.getBean(EmployerVacancyDAO.class);
    EduProviderDAO eduProviderDAO = springAppContext.getBean(EduProviderDAO.class);
    TrainingDAO trainingDAO = springAppContext.getBean(TrainingDAO.class);
    TrainReqmtBasicDAO trainReqmtBasicDAO = springAppContext.getBean(TrainReqmtBasicDAO.class);

    CourseReqmtBasicDAO courseReqmtBasicDAO = springAppContext.getBean(CourseReqmtBasicDAO.class);
    MigrationBasicDAO migrationBasicDAO = springAppContext.getBean(MigrationBasicDAO.class);

    @FXML
    private MenuItem itemAddEmployer;
    @FXML
    private MenuItem itemAddVacancies;
    @FXML
    private Button btnEmployer;
    @FXML
    private Button btnEduProvider;
    @FXML
    private MenuItem itemAddEduProvider;
    @FXML
    private MenuItem itemAddEduCourseRequirements;
    @FXML
    private MenuItem itemEduProviderView;
    @FXML
    private MenuItem itemMigrationProgramsView;
    @FXML
    private MenuItem itemMigrationProgramsAdd;
    @FXML
    private MenuItem itemEmpCurrency;
    @FXML
    private MenuItem itemEmpFeeType;
    @FXML
    private MenuItem itemEmpJobTitle;
    @FXML
    private MenuItem itemEmpAge;
    @FXML
    private MenuItem itemEmpBoard;
    @FXML
    private MenuItem itemEmpMediumInstruction;
    @FXML
    private MenuItem itemEmpProgramLevel;
    @FXML
    private MenuItem itemEmpProgramField;
    @FXML
    private MenuItem itemEmpDuration;
    @FXML
    private MenuItem itemEmpProfession;
    @FXML
    private MenuItem itemEmpTestScore;
    @FXML
    private MenuItem itemEmpTechnology;
    @FXML
    private MenuItem itemKnowledgeLevel;
    @FXML
    private MenuItem itemEmpSkill;
    @FXML
    private MenuItem itemEmpSkillType;
    @FXML
    private MenuItem itemEmpDocument;
    @FXML
    private MenuItem itemEmpSalary;
    @FXML
    private MenuItem itemEmpAccomodation;
    @FXML
    private MenuItem itemEmpLangTest;
    @FXML
    private MenuItem itemAddTrainingCourse;
    @FXML
    private MenuItem itemTrainingCourseReqmts;
    @FXML
    private MenuItem itemTrainingCourseView;
    @FXML
    private ToolBar toolBarSearchHome;
    @FXML
    private Button btnTrainingPrograms;
    @FXML
    private Button btnMigration;
    @FXML
    private MenuItem itemMigSubClass;
    @FXML
    private MenuItem itemMIgOccuptnCat;
    @FXML
    private MenuItem itemMigExperience;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /* ======================== Clear Default Tabs ==================== */
        tabPaneSearchHome.getTabs().removeAll(tabPaneSearchHome.getTabs());
        tabPaneSearchHome.tabClosingPolicyProperty().set(TabPane.TabClosingPolicy.SELECTED_TAB);
        addEmployerMenuListeners();
        addToolBarButtonActions();
        addAllMasterForms();
        addEduProviderMenuListeners();
        addMigrationMenuListners();

        /* ======================== Add Glyph ==================== */
//        btnEmployer.setGraphic(new Glyph("FontAwesome", FontAwesome.Glyph.DOLLAR));
        addTrainingMenuListners();

        /* ======================== Load A Default Data ==================== */
        Task task = new Task<Object>() {
            @Override
            protected Object call() throws Exception {
                btnEmployer.fire();
                return null;
            }
        };
        task.run();
    }

    public void addToolBarButtonActions() {

        btnEmployer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                itemEmployerView.fire();
                for (Node node : toolBarSearchHome.getItems()) {
                    if (node instanceof Button) {
                        node.setBlendMode(null);
                        node.getStyleClass().remove("add-left-border");
                    }
                }
                btnEmployer.setBlendMode(BlendMode.GREEN);
                btnEmployer.getStyleClass().add("add-left-border");
            }
        });
        btnEduProvider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                itemEduProviderView.fire();
                for (Node node : toolBarSearchHome.getItems()) {
                    if (node instanceof Button) {
                        node.setBlendMode(null);
                        node.getStyleClass().remove("add-left-border");
                    }
                }
                btnEduProvider.setBlendMode(BlendMode.GREEN);
                btnEduProvider.getStyleClass().add("add-left-border");
            }
        });
        btnTrainingPrograms.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                itemTrainingCourseView.fire();
                for (Node node : toolBarSearchHome.getItems()) {
                    if (node instanceof Button) {
                        node.setBlendMode(null);
                        node.getStyleClass().remove("add-left-border");
                    }
                }
                btnTrainingPrograms.setBlendMode(BlendMode.GREEN);
                btnTrainingPrograms.getStyleClass().add("add-left-border");
            }
        });

        btnMigration.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                itemMigrationProgramsView.fire();
                for (Node node : toolBarSearchHome.getItems()) {
                    if (node instanceof Button) {
                        node.setBlendMode(null);
                        node.getStyleClass().remove("add-left-border");
                    }
                }
                btnMigration.setBlendMode(BlendMode.GREEN);
                btnMigration.getStyleClass().add("add-left-border");
            }
        });
    }

    public void addAllMasterForms() {
        itemEmpCurrency.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    boolean tabExists = false;
                    for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                        if (tabAdded.getUserData() != null) {
                            if (tabAdded.getUserData().toString().equalsIgnoreCase("add_currency")) {
                                tabExists = true;
                                tabPaneSearchHome.getSelectionModel().select(tabAdded);
                            }
                        }
                    }
                    if (!tabExists) {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/search/master/currency/FXMLCurrency.fxml"));
                        StackPane stackPaneRoot = new StackPane(root);
                        stackPaneRoot.setAlignment(Pos.CENTER);
//                        stackPaneRoot.getChildren().add(root);
                        ScrollPane scrollPaneRoot = new ScrollPane(stackPaneRoot);
                        scrollPaneRoot.setFitToWidth(true);
                        Tab tabAddCurrency = new Tab("Currency");
                        tabAddCurrency.setContent(scrollPaneRoot);
                        tabAddCurrency.setUserData("add_currency");
                        tabAddCurrency.setClosable(true);
                        tabPaneSearchHome.getTabs().add(tabAddCurrency);
                        tabPaneSearchHome.getSelectionModel().select(tabAddCurrency);
                    }

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
        itemEmpFeeType.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    boolean tabExists = false;
                    for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                        if (tabAdded.getUserData() != null) {
                            if (tabAdded.getUserData().toString().equalsIgnoreCase("add_fee_type")) {
                                tabExists = true;
                                tabPaneSearchHome.getSelectionModel().select(tabAdded);
                            }
                        }
                    }
                    if (!tabExists) {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/search/master/feetype/FXMLFeeType.fxml"));
                        StackPane stackPaneRoot = new StackPane(root);
                        stackPaneRoot.setAlignment(Pos.CENTER);
                        ScrollPane scrollPaneRoot = new ScrollPane(stackPaneRoot);
                        scrollPaneRoot.setFitToWidth(true);
                        Tab tabAddFeeType = new Tab("Fee Type");
                        tabAddFeeType.setContent(scrollPaneRoot);
                        tabAddFeeType.setUserData("add_fee_type");
                        tabAddFeeType.setClosable(true);
                        tabPaneSearchHome.getTabs().add(tabAddFeeType);
                        tabPaneSearchHome.getSelectionModel().select(tabAddFeeType);
                    }

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
        itemEmpJobTitle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    boolean tabExists = false;
                    for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                        if (tabAdded.getUserData() != null) {
                            if (tabAdded.getUserData().toString().equalsIgnoreCase("add_job_title")) {
                                tabExists = true;
                                tabPaneSearchHome.getSelectionModel().select(tabAdded);
                            }
                        }
                    }
                    if (!tabExists) {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/search/master/jobtitle/FXMLJobTitle.fxml"));
                        StackPane stackPaneRoot = new StackPane(root);
                        stackPaneRoot.setAlignment(Pos.CENTER);
                        ScrollPane scrollPaneRoot = new ScrollPane(stackPaneRoot);
                        scrollPaneRoot.setFitToWidth(true);
                        Tab tabAddFeeType = new Tab("Job Title");
                        tabAddFeeType.setContent(scrollPaneRoot);
                        tabAddFeeType.setUserData("add_job_title");
                        tabAddFeeType.setClosable(true);
                        tabPaneSearchHome.getTabs().add(tabAddFeeType);
                        tabPaneSearchHome.getSelectionModel().select(tabAddFeeType);
                    }

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
        itemEmpAge.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    boolean tabExists = false;
                    for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                        if (tabAdded.getUserData() != null) {
                            if (tabAdded.getUserData().toString().equalsIgnoreCase("add_age")) {
                                tabExists = true;
                                tabPaneSearchHome.getSelectionModel().select(tabAdded);
                            }
                        }
                    }
                    if (!tabExists) {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/age/FXMLAddAge.fxml"));
                        StackPane stackPaneRoot = new StackPane(root);
                        stackPaneRoot.setAlignment(Pos.CENTER);
                        ScrollPane scrollPaneRoot = new ScrollPane(stackPaneRoot);
                        scrollPaneRoot.setFitToWidth(true);
                        Tab tabAddAge = new Tab("Age");
                        tabAddAge.setContent(scrollPaneRoot);
                        tabAddAge.setUserData("add_age");
                        tabAddAge.setClosable(true);
                        tabPaneSearchHome.getTabs().add(tabAddAge);
                        tabPaneSearchHome.getSelectionModel().select(tabAddAge);
                    }

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
        itemEmpBoard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    boolean tabExists = false;
                    for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                        if (tabAdded.getUserData() != null) {
                            if (tabAdded.getUserData().toString().equalsIgnoreCase("add_board")) {
                                tabExists = true;
                                tabPaneSearchHome.getSelectionModel().select(tabAdded);
                            }
                        }
                    }
                    if (!tabExists) {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/examboard/FXMLExamBoard.fxml"));
                        StackPane stackPaneRoot = new StackPane(root);
                        stackPaneRoot.setAlignment(Pos.CENTER);
                        ScrollPane scrollPaneRoot = new ScrollPane(stackPaneRoot);
                        scrollPaneRoot.setFitToWidth(true);
                        Tab tabAddBoard = new Tab("Exam Boards");
                        tabAddBoard.setContent(scrollPaneRoot);
                        tabAddBoard.setUserData("add_board");
                        tabAddBoard.setClosable(true);
                        tabPaneSearchHome.getTabs().add(tabAddBoard);
                        tabPaneSearchHome.getSelectionModel().select(tabAddBoard);
                    }

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
        itemEmpMediumInstruction.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    boolean tabExists = false;
                    for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                        if (tabAdded.getUserData() != null) {
                            if (tabAdded.getUserData().toString().equalsIgnoreCase("add_medium_ins")) {
                                tabExists = true;
                                tabPaneSearchHome.getSelectionModel().select(tabAdded);
                            }
                        }
                    }
                    if (!tabExists) {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/language/FXMLLanguage.fxml"));
                        StackPane stackPaneRoot = new StackPane(root);
                        stackPaneRoot.setAlignment(Pos.CENTER);
                        ScrollPane scrollPaneRoot = new ScrollPane(stackPaneRoot);
                        scrollPaneRoot.setFitToWidth(true);
                        Tab tabAddBoard = new Tab("Medium Of Instructions");
                        tabAddBoard.setContent(scrollPaneRoot);
                        tabAddBoard.setUserData("add_medium_ins");
                        tabAddBoard.setClosable(true);
                        tabPaneSearchHome.getTabs().add(tabAddBoard);
                        tabPaneSearchHome.getSelectionModel().select(tabAddBoard);
                    }

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
        itemEmpProgramLevel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    boolean tabExists = false;
                    for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                        if (tabAdded.getUserData() != null) {
                            if (tabAdded.getUserData().toString().equalsIgnoreCase("add_program_level")) {
                                tabExists = true;
                                tabPaneSearchHome.getSelectionModel().select(tabAdded);
                            }
                        }
                    }
                    if (!tabExists) {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/qualificationlevel/FXMLQualificationLevel.fxml"));
                        StackPane stackPaneRoot = new StackPane(root);
                        stackPaneRoot.setAlignment(Pos.CENTER);
                        ScrollPane scrollPaneRoot = new ScrollPane(stackPaneRoot);
                        scrollPaneRoot.setFitToWidth(true);
                        Tab tabAddProgramLevel = new Tab("Program Level");
                        tabAddProgramLevel.setContent(scrollPaneRoot);
                        tabAddProgramLevel.setUserData("add_program_level");
                        tabAddProgramLevel.setClosable(true);
                        tabPaneSearchHome.getTabs().add(tabAddProgramLevel);
                        tabPaneSearchHome.getSelectionModel().select(tabAddProgramLevel);
                    }

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
        itemEmpProgramField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    boolean tabExists = false;
                    for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                        if (tabAdded.getUserData() != null) {
                            if (tabAdded.getUserData().toString().equalsIgnoreCase("add_program_field")) {
                                tabExists = true;
                                tabPaneSearchHome.getSelectionModel().select(tabAdded);
                            }
                        }
                    }
                    if (!tabExists) {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/qualification/FXMLQualification.fxml"));
                        StackPane stackPaneRoot = new StackPane(root);
                        stackPaneRoot.setAlignment(Pos.CENTER);
                        ScrollPane scrollPaneRoot = new ScrollPane(stackPaneRoot);
                        scrollPaneRoot.setFitToWidth(true);
                        Tab tabAddProgramField = new Tab("Program Field");
                        tabAddProgramField.setContent(scrollPaneRoot);
                        tabAddProgramField.setUserData("add_program_field");
                        tabAddProgramField.setClosable(true);
                        tabPaneSearchHome.getTabs().add(tabAddProgramField);
                        tabPaneSearchHome.getSelectionModel().select(tabAddProgramField);
                    }

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
        itemEmpDuration.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    boolean tabExists = false;
                    for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                        if (tabAdded.getUserData() != null) {
                            if (tabAdded.getUserData().toString().equalsIgnoreCase("add_duration")) {
                                tabExists = true;
                                tabPaneSearchHome.getSelectionModel().select(tabAdded);
                            }
                        }
                    }
                    if (!tabExists) {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/qualificationduration/FXMLQualificationDuration.fxml"));
                        StackPane stackPaneRoot = new StackPane(root);
                        stackPaneRoot.setAlignment(Pos.CENTER);
                        ScrollPane scrollPaneRoot = new ScrollPane(stackPaneRoot);
                        scrollPaneRoot.setFitToWidth(true);
                        Tab tabAddProgramField = new Tab("Duration");
                        tabAddProgramField.setContent(scrollPaneRoot);
                        tabAddProgramField.setUserData("add_duration");
                        tabAddProgramField.setClosable(true);
                        tabPaneSearchHome.getTabs().add(tabAddProgramField);
                        tabPaneSearchHome.getSelectionModel().select(tabAddProgramField);
                    }

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
        itemEmpProfession.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    boolean tabExists = false;
                    for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                        if (tabAdded.getUserData() != null) {
                            if (tabAdded.getUserData().toString().equalsIgnoreCase("add_profession")) {
                                tabExists = true;
                                tabPaneSearchHome.getSelectionModel().select(tabAdded);
                            }
                        }
                    }
                    if (!tabExists) {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/profession/FXMLProfession.fxml"));
                        StackPane stackPaneRoot = new StackPane(root);
                        stackPaneRoot.setAlignment(Pos.CENTER);
                        ScrollPane scrollPaneRoot = new ScrollPane(stackPaneRoot);
                        scrollPaneRoot.setFitToWidth(true);
                        Tab tabAddProfession = new Tab("Profession");
                        tabAddProfession.setContent(scrollPaneRoot);
                        tabAddProfession.setUserData("add_profession");
                        tabAddProfession.setClosable(true);
                        tabPaneSearchHome.getTabs().add(tabAddProfession);
                        tabPaneSearchHome.getSelectionModel().select(tabAddProfession);
                    }

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
        itemEmpLangTest.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    boolean tabExists = false;
                    for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                        if (tabAdded.getUserData() != null) {
                            if (tabAdded.getUserData().toString().equalsIgnoreCase("add_lang_test")) {
                                tabExists = true;
                                tabPaneSearchHome.getSelectionModel().select(tabAdded);
                            }
                        }
                    }
                    if (!tabExists) {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/admissiontest/AdmissionTestFXML.fxml"));
                        StackPane stackPaneRoot = new StackPane(root);
                        stackPaneRoot.setAlignment(Pos.CENTER);
                        ScrollPane scrollPaneRoot = new ScrollPane(stackPaneRoot);
                        scrollPaneRoot.setFitToWidth(true);
                        Tab tabAddProfession = new Tab("Language Test");
                        tabAddProfession.setContent(scrollPaneRoot);
                        tabAddProfession.setUserData("add_lang_test");
                        tabAddProfession.setClosable(true);
                        tabPaneSearchHome.getTabs().add(tabAddProfession);
                        tabPaneSearchHome.getSelectionModel().select(tabAddProfession);
                    }

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
        itemEmpTestScore.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    boolean tabExists = false;
                    for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                        if (tabAdded.getUserData() != null) {
                            if (tabAdded.getUserData().toString().equalsIgnoreCase("add_test_score")) {
                                tabExists = true;
                                tabPaneSearchHome.getSelectionModel().select(tabAdded);
                            }
                        }
                    }
                    if (!tabExists) {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/score/FXMLScore.fxml"));
                        StackPane stackPaneRoot = new StackPane(root);
                        stackPaneRoot.setAlignment(Pos.CENTER);
                        ScrollPane scrollPaneRoot = new ScrollPane(stackPaneRoot);
                        scrollPaneRoot.setFitToWidth(true);
                        Tab tabAddScore = new Tab("Language Test Score");
                        tabAddScore.setContent(scrollPaneRoot);
                        tabAddScore.setUserData("add_test_score");
                        tabAddScore.setClosable(true);
                        tabPaneSearchHome.getTabs().add(tabAddScore);
                        tabPaneSearchHome.getSelectionModel().select(tabAddScore);
                    }

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
        /* ======================== not completed ......... ==================== */
        itemEmpTechnology.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    boolean tabExists = false;
                    for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                        if (tabAdded.getUserData() != null) {
                            if (tabAdded.getUserData().toString().equalsIgnoreCase("add_technology")) {
                                tabExists = true;
                                tabPaneSearchHome.getSelectionModel().select(tabAdded);
                            }
                        }
                    }
                    if (!tabExists) {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/score/FXMLScore.fxml"));
                        StackPane stackPaneRoot = new StackPane(root);
                        stackPaneRoot.setAlignment(Pos.CENTER);
                        ScrollPane scrollPaneRoot = new ScrollPane(stackPaneRoot);
                        scrollPaneRoot.setFitToWidth(true);
                        Tab tabAddScore = new Tab("Technology");
                        tabAddScore.setContent(scrollPaneRoot);
                        tabAddScore.setUserData("add_technology");
                        tabAddScore.setClosable(true);
                        tabPaneSearchHome.getTabs().add(tabAddScore);
                        tabPaneSearchHome.getSelectionModel().select(tabAddScore);
                    }

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
        itemEmpSkill.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    boolean tabExists = false;
                    for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                        if (tabAdded.getUserData() != null) {
                            if (tabAdded.getUserData().toString().equalsIgnoreCase("add_technical_skill")) {
                                tabExists = true;
                                tabPaneSearchHome.getSelectionModel().select(tabAdded);
                            }
                        }
                    }
                    if (!tabExists) {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/technicalskills/technicalSkillsFXML.fxml"));
                        StackPane stackPaneRoot = new StackPane(root);
                        stackPaneRoot.setAlignment(Pos.CENTER);
                        ScrollPane scrollPaneRoot = new ScrollPane(stackPaneRoot);
                        scrollPaneRoot.setFitToWidth(true);
                        Tab tabAddScore = new Tab("Technical Skills");
                        tabAddScore.setContent(scrollPaneRoot);
                        tabAddScore.setUserData("add_technical_skill");
                        tabAddScore.setClosable(true);
                        tabPaneSearchHome.getTabs().add(tabAddScore);
                        tabPaneSearchHome.getSelectionModel().select(tabAddScore);
                    }

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
        itemEmpSkillType.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    boolean tabExists = false;
                    for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                        if (tabAdded.getUserData() != null) {
                            if (tabAdded.getUserData().toString().equalsIgnoreCase("add_technical_skill")) {
                                tabExists = true;
                                tabPaneSearchHome.getSelectionModel().select(tabAdded);
                            }
                        }
                    }
                    if (!tabExists) {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/technicalskills/technicalSkillsFXML.fxml"));
                        StackPane stackPaneRoot = new StackPane(root);
                        stackPaneRoot.setAlignment(Pos.CENTER);
                        ScrollPane scrollPaneRoot = new ScrollPane(stackPaneRoot);
                        scrollPaneRoot.setFitToWidth(true);
                        Tab tabAddScore = new Tab("Technical Skills");
                        tabAddScore.setContent(scrollPaneRoot);
                        tabAddScore.setUserData("add_technical_skill");
                        tabAddScore.setClosable(true);
                        tabPaneSearchHome.getTabs().add(tabAddScore);
                        tabPaneSearchHome.getSelectionModel().select(tabAddScore);
                    }

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
        itemEmpDocument.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    boolean tabExists = false;
                    for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                        if (tabAdded.getUserData() != null) {
                            if (tabAdded.getUserData().toString().equalsIgnoreCase("add_document")) {
                                tabExists = true;
                                tabPaneSearchHome.getSelectionModel().select(tabAdded);
                            }
                        }
                    }
                    if (!tabExists) {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/documents/FXMLDocuments.fxml"));
                        StackPane stackPaneRoot = new StackPane(root);
                        stackPaneRoot.setAlignment(Pos.CENTER);
                        ScrollPane scrollPaneRoot = new ScrollPane(stackPaneRoot);
                        scrollPaneRoot.setFitToWidth(true);
                        Tab tabAddDocument = new Tab("Document Name");
                        tabAddDocument.setContent(scrollPaneRoot);
                        tabAddDocument.setUserData("add_document");
                        tabAddDocument.setClosable(true);
                        tabPaneSearchHome.getTabs().add(tabAddDocument);
                        tabPaneSearchHome.getSelectionModel().select(tabAddDocument);
                    }

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
        itemEmpSalary.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    boolean tabExists = false;
                    for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                        if (tabAdded.getUserData() != null) {
                            if (tabAdded.getUserData().toString().equalsIgnoreCase("add_salary")) {
                                tabExists = true;
                                tabPaneSearchHome.getSelectionModel().select(tabAdded);
                            }
                        }
                    }
                    if (!tabExists) {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/salary/FXMLSalary.fxml"));
                        StackPane stackPaneRoot = new StackPane(root);
                        stackPaneRoot.setAlignment(Pos.CENTER);
                        ScrollPane scrollPaneRoot = new ScrollPane(stackPaneRoot);
                        scrollPaneRoot.setFitToWidth(true);
                        Tab tabAddSalary = new Tab("Salary");
                        tabAddSalary.setContent(scrollPaneRoot);
                        tabAddSalary.setUserData("add_salary");
                        tabAddSalary.setClosable(true);
                        tabPaneSearchHome.getTabs().add(tabAddSalary);
                        tabPaneSearchHome.getSelectionModel().select(tabAddSalary);
                    }

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
        itemEmpSalary.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    boolean tabExists = false;
                    for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                        if (tabAdded.getUserData() != null) {
                            if (tabAdded.getUserData().toString().equalsIgnoreCase("add_salary")) {
                                tabExists = true;
                                tabPaneSearchHome.getSelectionModel().select(tabAdded);
                            }
                        }
                    }
                    if (!tabExists) {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/salary/FXMLSalary.fxml"));
                        StackPane stackPaneRoot = new StackPane(root);
                        stackPaneRoot.setAlignment(Pos.CENTER);
                        ScrollPane scrollPaneRoot = new ScrollPane(stackPaneRoot);
                        scrollPaneRoot.setFitToWidth(true);
                        Tab tabAddSalary = new Tab("Salary");
                        tabAddSalary.setContent(scrollPaneRoot);
                        tabAddSalary.setUserData("add_salary");
                        tabAddSalary.setClosable(true);
                        tabPaneSearchHome.getTabs().add(tabAddSalary);
                        tabPaneSearchHome.getSelectionModel().select(tabAddSalary);
                    }

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
        itemEmpAccomodation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    boolean tabExists = false;
                    for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                        if (tabAdded.getUserData() != null) {
                            if (tabAdded.getUserData().toString().equalsIgnoreCase("add_accomodation")) {
                                tabExists = true;
                                tabPaneSearchHome.getSelectionModel().select(tabAdded);
                            }
                        }
                    }
                    if (!tabExists) {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/search/master/accomodation/FXMLAccomodation.fxml"));
                        StackPane stackPaneRoot = new StackPane(root);
                        stackPaneRoot.setAlignment(Pos.CENTER);
                        ScrollPane scrollPaneRoot = new ScrollPane(stackPaneRoot);
                        scrollPaneRoot.setFitToWidth(true);
                        Tab tabAddSalary = new Tab("Accomodation");
                        tabAddSalary.setContent(scrollPaneRoot);
                        tabAddSalary.setUserData("add_accomodation");
                        tabAddSalary.setClosable(true);
                        tabPaneSearchHome.getTabs().add(tabAddSalary);
                        tabPaneSearchHome.getSelectionModel().select(tabAddSalary);
                    }

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
        itemMigSubClass.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    boolean tabExists = false;
                    for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                        if (tabAdded.getUserData() != null) {
                            if (tabAdded.getUserData().toString().equalsIgnoreCase("add_sub_class")) {
                                tabExists = true;
                                tabPaneSearchHome.getSelectionModel().select(tabAdded);
                            }
                        }
                    }
                    if (!tabExists) {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/search/master/subclass/FXMLSubClass.fxml"));
                        StackPane stackPaneRoot = new StackPane(root);
                        stackPaneRoot.setAlignment(Pos.CENTER);
                        ScrollPane scrollPaneRoot = new ScrollPane(stackPaneRoot);
                        scrollPaneRoot.setFitToWidth(true);
                        Tab tabSubClass = new Tab("Sub Class");
                        tabSubClass.setContent(scrollPaneRoot);
                        tabSubClass.setUserData("add_sub_class");
                        tabSubClass.setClosable(true);
                        tabPaneSearchHome.getTabs().add(tabSubClass);
                        tabPaneSearchHome.getSelectionModel().select(tabSubClass);
                    }

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
        itemMIgOccuptnCat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    boolean tabExists = false;
                    for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                        if (tabAdded.getUserData() != null) {
                            if (tabAdded.getUserData().toString().equalsIgnoreCase("add_occ_cat")) {
                                tabExists = true;
                                tabPaneSearchHome.getSelectionModel().select(tabAdded);
                            }
                        }
                    }
                    if (!tabExists) {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/search/master/occupation/category/FXMLOccupationCategory.fxml"));
                        StackPane stackPaneRoot = new StackPane(root);
                        stackPaneRoot.setAlignment(Pos.CENTER);
                        ScrollPane scrollPaneRoot = new ScrollPane(stackPaneRoot);
                        scrollPaneRoot.setFitToWidth(true);
                        Tab tabSubClass = new Tab("Occupation Categories");
                        tabSubClass.setContent(scrollPaneRoot);
                        tabSubClass.setUserData("add_occ_cat");
                        tabSubClass.setClosable(true);
                        tabPaneSearchHome.getTabs().add(tabSubClass);
                        tabPaneSearchHome.getSelectionModel().select(tabSubClass);
                    }

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
        itemMigExperience.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    boolean tabExists = false;
                    for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                        if (tabAdded.getUserData() != null) {
                            if (tabAdded.getUserData().toString().equalsIgnoreCase("add_experience")) {
                                tabExists = true;
                                tabPaneSearchHome.getSelectionModel().select(tabAdded);
                            }
                        }
                    }
                    if (!tabExists) {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/experience/FXMLExperience.fxml"));
                        StackPane stackPaneRoot = new StackPane(root);
                        stackPaneRoot.setAlignment(Pos.CENTER);
                        ScrollPane scrollPaneRoot = new ScrollPane(stackPaneRoot);
                        centerNodeInScrollPane(scrollPaneRoot, stackPaneRoot);
                        scrollPaneRoot.setFitToWidth(true);
                        Tab tabSubClass = new Tab("Experience");
                        tabSubClass.setContent(scrollPaneRoot);
                        tabSubClass.setUserData("add_experience");
                        tabSubClass.setClosable(true);
                        tabPaneSearchHome.getTabs().add(tabSubClass);
                        tabPaneSearchHome.getSelectionModel().select(tabSubClass);
                    }

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });

    }

    public void centerNodeInScrollPane(ScrollPane scrollPane, Node node) {
        double h = scrollPane.getContent().getBoundsInLocal().getHeight();
        double y = (node.getBoundsInParent().getMaxY()
                + node.getBoundsInParent().getMinY()) / 2.0;
        double v = scrollPane.getViewportBounds().getHeight();
        scrollPane.setVvalue(scrollPane.getVmax() * ((y - 0.5 * v) / (h - v)));
    }

    private void addMigrationMenuListners() {
        itemMigrationProgramsAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/search/migration/FXMLMigration.fxml"));
                    Parent root = (Parent) loader.load();
                    FXMLMigrationController controller = (FXMLMigrationController) loader.getController();
                    controller.initData(new MigrationBasicBEAN());
                    Stage stageInbox = new Stage();
                    Scene scene = new Scene(root);
                    stageInbox.setTitle("ADD/EDIT MIGRATION PROGRAM DETAILS");
                    stageInbox.initModality(Modality.APPLICATION_MODAL);
                    stageInbox.setWidth(900);
                    stageInbox.setHeight(700);
                    stageInbox.initOwner(tabPaneSearchHome.getScene().getWindow());
                    stageInbox.setScene(scene);
                    stageInbox.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageInbox.show();
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
        itemMigrationProgramsView.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean tabExists = false;
                for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                    if (tabAdded.getUserData() != null) {
                        if (tabAdded.getUserData().toString().equalsIgnoreCase("all_migration_program")) {
                            tabExists = true;
                            tabPaneSearchHome.getSelectionModel().select(tabAdded);
                        }
                    }
                }
                if (!tabExists) {
                    /* ======================== Show All Added Employer View ==================== */
                    TableView<MigrationBasicBEAN> tblAllMigration = new TableView();
                    tblAllMigration.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    tblAllMigration.getStyleClass().add("table-employer-main");
                    Tab tabAllMigrationPrograms = new Tab("All Migration Programs");
                    tabAllMigrationPrograms.setContent(tblAllMigration);
                    tabAllMigrationPrograms.setUserData("all_migration_program");
                    TableColumn<MigrationBasicBEAN, String> colCountry = new TableColumn("Country");
                    TableColumn<MigrationBasicBEAN, String> colSubClass = new TableColumn("Sub Class");
                    TableColumn<MigrationBasicBEAN, String> colOccCat = new TableColumn("Occupation Category");
                    TableColumn<MigrationBasicBEAN, String> colLastUpdate = new TableColumn("Last Update");
                    colCountry.setCellValueFactory((TableColumn.CellDataFeatures<MigrationBasicBEAN, String> param) -> param.getValue().countryProperty());
                    colSubClass.setCellValueFactory((TableColumn.CellDataFeatures<MigrationBasicBEAN, String> param) -> param.getValue().subClassProperty());
                    colOccCat.setCellValueFactory((TableColumn.CellDataFeatures<MigrationBasicBEAN, String> param) -> param.getValue().ocupationCatProperty());
                    colLastUpdate.setCellValueFactory((TableColumn.CellDataFeatures<MigrationBasicBEAN, String> param) -> param.getValue().updatedDateProperty());

                    tblAllMigration.setRowFactory(new Callback<TableView<MigrationBasicBEAN>, TableRow<MigrationBasicBEAN>>() {
                        @Override
                        public TableRow<MigrationBasicBEAN> call(TableView<MigrationBasicBEAN> param) {
                            final ContextMenu contextMenu = new ContextMenu();
                            final MenuItem itemEdit = new MenuItem("View/Edit Details");
                            final MenuItem itemNew = new MenuItem("Add New");
                            final TableRow<MigrationBasicBEAN> row = new TableRow<MigrationBasicBEAN>() {
                                @Override
                                protected void updateItem(MigrationBasicBEAN employerBEAN, boolean empty) {
                                    super.updateItem(employerBEAN, empty);
                                    if (!empty) {

                                    }
                                }
                            };
                            itemEdit.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    try {
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/search/migration/FXMLMigration.fxml"));
                                        Parent root = (Parent) loader.load();
                                        FXMLMigrationController controller = (FXMLMigrationController) loader.getController();
                                        controller.initData(row.getItem());
                                        Stage stageInbox = new Stage();
                                        Scene scene = new Scene(root);
                                        stageInbox.setTitle("ADD/EDIT MIGRATION PROGRAM DETAILS");
                                        stageInbox.initModality(Modality.APPLICATION_MODAL);
                                        stageInbox.setWidth(900);
                                        stageInbox.setHeight(700);
                                        stageInbox.initOwner(tabPaneSearchHome.getScene().getWindow());
                                        stageInbox.setScene(scene);
                                        stageInbox.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                                        stageInbox.show();
                                    } catch (IOException ex) {
                                        logger.error(ex.getMessage());
                                        ex.printStackTrace();
                                    }
                                }
                            });
                            itemNew.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    try {
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/search/migration/FXMLMigration.fxml"));
                                        Parent root = (Parent) loader.load();
                                        FXMLMigrationController controller = (FXMLMigrationController) loader.getController();
                                        controller.initData(new MigrationBasicBEAN());
                                        Stage stageInbox = new Stage();
                                        Scene scene = new Scene(root);
                                        stageInbox.setTitle("ADD/EDIT MIGRATION PROGRAM DETAILS");
                                        stageInbox.initModality(Modality.APPLICATION_MODAL);
                                        stageInbox.setWidth(900);
                                        stageInbox.setHeight(700);
                                        stageInbox.initOwner(tabPaneSearchHome.getScene().getWindow());
                                        stageInbox.setScene(scene);
                                        stageInbox.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                                        stageInbox.show();
                                    } catch (IOException ex) {
                                        logger.error(ex.getMessage());
                                        ex.printStackTrace();
                                    }
                                }
                            });
                            contextMenu.getItems().addAll(itemEdit, itemNew);

                            row.contextMenuProperty().bind(
                                    Bindings.when(row.emptyProperty())
                                            .then((ContextMenu) null)
                                            .otherwise(contextMenu));
                            return row;
                        }
                    });

                    tblAllMigration.getColumns().addAll(colCountry, colSubClass, colOccCat, colLastUpdate);
                    tblAllMigration.getItems().addAll(migrationBasicDAO.retrieveBasicInfo());
                    TableFilter filter = new TableFilter(tblAllMigration);
                    tabPaneSearchHome.getTabs().add(tabAllMigrationPrograms);
                    tabPaneSearchHome.getSelectionModel().select(tabAllMigrationPrograms);
                }
            }
        });
    }

    private void addEmployerMenuListeners() {
        itemAddVacancies.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/search/employer/vacancy/FXMLEmployerVacancy.fxml"));
                    Parent root = (Parent) loader.load();
                    FXMLEmployerVacancyController controller = (FXMLEmployerVacancyController) loader.getController();
                    controller.initData(new EmployerBEAN());
                    Stage stageInbox = new Stage();
                    Scene scene = new Scene(root);
                    stageInbox.setTitle("ADD/EDIT EMPLOYER VACANCY DETAILS");
                    stageInbox.initModality(Modality.APPLICATION_MODAL);
                    stageInbox.setWidth(900);
                    stageInbox.setHeight(700);
                    stageInbox.initOwner(tabPaneSearchHome.getScene().getWindow());
                    stageInbox.setScene(scene);
                    stageInbox.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageInbox.show();
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

        itemAddEmployer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    boolean tabExists = false;
                    for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                        if (tabAdded.getUserData() != null) {
                            if (tabAdded.getUserData().toString().equalsIgnoreCase("add_employer")) {
                                tabExists = true;
                                tabPaneSearchHome.getSelectionModel().select(tabAdded);
                            }
                        }
                    }
                    if (!tabExists) {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/search/employer/FXMLEmployer.fxml"));
                        StackPane stackPaneRoot = new StackPane(root);
                        stackPaneRoot.setAlignment(Pos.CENTER);
//                        stackPaneRoot.getChildren().add(root);
                        ScrollPane scrollPaneRoot = new ScrollPane(stackPaneRoot);
                        scrollPaneRoot.setFitToWidth(true);
                        Tab tabAddEmployer = new Tab("Add Employer");
                        tabAddEmployer.setContent(scrollPaneRoot);
                        tabAddEmployer.setUserData("add_employer");
                        tabAddEmployer.setClosable(true);
                        tabPaneSearchHome.getTabs().add(tabAddEmployer);
                        tabPaneSearchHome.getSelectionModel().select(tabAddEmployer);
                    }

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
        itemEmplpyerAddCountryLoc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    boolean tabExists = false;
                    for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                        if (tabAdded.getUserData() != null) {
                            if (tabAdded.getUserData().toString().equalsIgnoreCase("country_locations")) {
                                tabExists = true;
                                tabPaneSearchHome.getSelectionModel().select(tabAdded);
                            }
                        }
                    }
                    if (!tabExists) {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/admin/master/locations/FXMLLocations.fxml"));
                        StackPane stackPaneRoot = new StackPane(root);
                        stackPaneRoot.setAlignment(Pos.CENTER);
//                        stackPaneRoot.getChildren().add(root);
                        ScrollPane scrollPaneRoot = new ScrollPane(stackPaneRoot);
                        scrollPaneRoot.setFitToWidth(true);
                        Tab tabMasterCountry = new Tab("Add Countries & Locations");
                        tabMasterCountry.setContent(scrollPaneRoot);
                        tabMasterCountry.setUserData("country_locations");
                        tabMasterCountry.setClosable(true);
                        tabPaneSearchHome.getTabs().add(tabMasterCountry);
                        tabPaneSearchHome.getSelectionModel().select(tabMasterCountry);
                    }

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }
            }
        });
        itemEmployerView.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean tabExists = false;
                for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                    if (tabAdded.getUserData() != null) {
                        if (tabAdded.getUserData().toString().equalsIgnoreCase("all_employer")) {
                            tabExists = true;
                            tabPaneSearchHome.getSelectionModel().select(tabAdded);
                        }
                    }
                }
                if (!tabExists) {
                    /* ======================== Show All Added Employer View ==================== */
                    TableView<EmployerBEAN> tblAllEmployers = new TableView();
                    tblAllEmployers.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    tblAllEmployers.getStyleClass().add("table-employer-main");
                    Tab tabAllEmployer = new Tab("All Employers");
                    tabAllEmployer.setContent(tblAllEmployers);
                    tabAllEmployer.setUserData("all_employer");
                    TableColumn<EmployerBEAN, String> colEmpName = new TableColumn("Employer Name");
                    TableColumn<EmployerBEAN, String> colEmpType = new TableColumn("Employer Type");
                    TableColumn<EmployerBEAN, String> colEmpCommision = new TableColumn("Commission");
                    TableColumn<EmployerBEAN, String> colEmpCurency = new TableColumn("Currency Used");
                    TableColumn<EmployerBEAN, String> colEmpContractFrom = new TableColumn("Contract From");
                    TableColumn<EmployerBEAN, String> colEmpContractTo = new TableColumn("Contract To");
                    colEmpName.setCellValueFactory((TableColumn.CellDataFeatures<EmployerBEAN, String> param) -> param.getValue().employerProperty());
                    colEmpType.setCellValueFactory((TableColumn.CellDataFeatures<EmployerBEAN, String> param) -> param.getValue().employerTypeProperty());
                    colEmpCommision.setCellValueFactory((TableColumn.CellDataFeatures<EmployerBEAN, String> param) -> param.getValue().commissionProperty());
                    colEmpCurency.setCellValueFactory((TableColumn.CellDataFeatures<EmployerBEAN, String> param) -> param.getValue().currencyUsedProperty());
                    colEmpContractFrom.setCellValueFactory((TableColumn.CellDataFeatures<EmployerBEAN, String> param) -> param.getValue().contractFromProperty());
                    colEmpContractTo.setCellValueFactory((TableColumn.CellDataFeatures<EmployerBEAN, String> param) -> param.getValue().contractToProperty());
                    TableRowExpanderColumn<EmployerBEAN> expander = new TableRowExpanderColumn<>(param -> {
                        VBox hboxExpandedView = new VBox(10);
                        HBox hboxButtons = new HBox(10);
                        Button btnAddNew = new Button("Add New Vacancy");
                        Button btnEdit = new Button("Edit");
                        /* ======================== Create Vacancy Details Table ==================== */
                        TableView<EmployerBEAN> tblAllVacancies = createVacancyTableView(param.getValue().getEmployerId());
                        /* ======================== Bind Edit Button Visibility ==================== */
                        btnEdit.disableProperty().bind(Bindings.equal(tblAllVacancies.getSelectionModel().selectedIndexProperty(), -1));
                        /* ======================== Load Vacancy Form ==================== */
                        btnAddNew.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {

                                try {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/search/employer/vacancy/FXMLEmployerVacancy.fxml"));
                                    Parent root = (Parent) loader.load();
                                    FXMLEmployerVacancyController controller = (FXMLEmployerVacancyController) loader.getController();
                                    controller.initData(param.getValue());
                                    Stage stageInbox = new Stage();
                                    Scene scene = new Scene(root);
                                    stageInbox.setTitle("ADD/REMOVE EMPLOYER VCANCY DETAILS");
                                    stageInbox.initModality(Modality.APPLICATION_MODAL);
                                    stageInbox.setWidth(900);
                                    stageInbox.setHeight(700);
                                    stageInbox.initOwner(tabPaneSearchHome.getScene().getWindow());
                                    stageInbox.setScene(scene);
                                    stageInbox.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                                    stageInbox.show();
                                } catch (IOException ex) {
                                    logger.error(ex.getMessage());
                                    ex.printStackTrace();
                                }
                            }
                        });
                        /* ======================== Edit Vacancy Details ==================== */
                        btnEdit.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {

                                try {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/search/employer/vacancy/FXMLEmployerVacancy.fxml"));
                                    Parent root = (Parent) loader.load();
                                    FXMLEmployerVacancyController controller = (FXMLEmployerVacancyController) loader.getController();
                                    controller.initData(tblAllVacancies.getSelectionModel().getSelectedItem());
                                    Stage stageInbox = new Stage();
                                    Scene scene = new Scene(root);
                                    stageInbox.setTitle("ADD/REMOVE EMPLOYER VCANCY DETAILS");
                                    stageInbox.initModality(Modality.APPLICATION_MODAL);
                                    stageInbox.setWidth(900);
                                    stageInbox.setHeight(700);
                                    stageInbox.initOwner(tabPaneSearchHome.getScene().getWindow());
                                    stageInbox.setScene(scene);
                                    stageInbox.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                                    stageInbox.show();
                                } catch (IOException ex) {
                                    logger.error(ex.getMessage());
                                    ex.printStackTrace();
                                }
                            }
                        });
                        hboxButtons.getChildren().addAll(btnAddNew, btnEdit);
                        hboxExpandedView.setStyle("-fx-padding:10px");

                        hboxExpandedView.setMaxWidth(Double.MAX_VALUE);
                        hboxExpandedView.setPrefHeight(Region.USE_COMPUTED_SIZE);
                        hboxExpandedView.getChildren().addAll(hboxButtons, tblAllVacancies);
                        return hboxExpandedView;
                    });
                    expander.setText("Vacancies(Press '+')");
                    createContextMenuInTable(tblAllEmployers);
                    tblAllEmployers.getColumns().addAll(expander, colEmpName, colEmpType, colEmpCommision, colEmpCurency, colEmpContractFrom, colEmpContractTo);
                    tblAllEmployers.getItems().addAll(employerDAO.retrieveEmployerBasics());
                    TableFilter filter = new TableFilter(tblAllEmployers);
                    tabPaneSearchHome.getTabs().add(tabAllEmployer);
                    tabPaneSearchHome.getSelectionModel().select(tabAllEmployer);
                }
            }
        });
    }

    public void createContextMenuInTable(TableView<EmployerBEAN> tblAllEmployers) {
        tblAllEmployers.setRowFactory(new Callback<TableView<EmployerBEAN>, TableRow<EmployerBEAN>>() {
            @Override
            public TableRow<EmployerBEAN> call(TableView<EmployerBEAN> param) {
                final ContextMenu contextMenu = new ContextMenu();
                final MenuItem itemOpenEnquiry = new MenuItem("Open Assesment Form");
                final MenuItem itemOpenRegistration = new MenuItem("Open Registration Form");
                final MenuItem itemForwardEnquiry = new MenuItem("Forward Enquiries");
                final MenuItem itemLockEnquiry = new MenuItem("Lock Enquiry");
                final TableRow<EmployerBEAN> row = new TableRow<EmployerBEAN>() {
                    @Override
                    protected void updateItem(EmployerBEAN employerBEAN, boolean empty) {
                        super.updateItem(employerBEAN, empty);
                        if (!empty) {

                        }
                    }
                };
                contextMenu.getItems().addAll(itemOpenEnquiry, itemForwardEnquiry, itemOpenRegistration);

                row.contextMenuProperty().bind(
                        Bindings.when(row.emptyProperty())
                                .then((ContextMenu) null)
                                .otherwise(contextMenu));
                return row;
            }
        });
    }

    public TableView createVacancyTableView(String employerId) {
        TableView<EmployerBEAN> tblVacancies = new TableView();
//        tblVacancies.setPrefHeight(Region.USE_COMPUTED_SIZE);
        tblVacancies.setPrefHeight(110);
        tblVacancies.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tblVacancies.getStyleClass().add("table-employer-vacancy");
        HBox.setHgrow(tblVacancies, Priority.ALWAYS);
        Label lblPlaceholder = new Label("No vacancies available!");
        lblPlaceholder.setStyle("-fx-text-fill:red");
        tblVacancies.setPlaceholder(lblPlaceholder);
        TableColumn<EmployerBEAN, String> colVacancyJob = new TableColumn("Job");
        TableColumn<EmployerBEAN, String> colVacancySector = new TableColumn("Employment Sector");
        TableColumn<EmployerBEAN, String> colVacancyStatus = new TableColumn("Vacancy Status");
        TableColumn<EmployerBEAN, String> colVacancyNumberOf = new TableColumn("No. Of Vacancies");
        TableColumn<EmployerBEAN, String> colVacancyGender = new TableColumn("Gender");
        TableColumn<EmployerBEAN, String> colVacancyAgeFrom = new TableColumn("Age From");
        TableColumn<EmployerBEAN, String> colVacancyAgeTo = new TableColumn("Age To");
        TableColumn<EmployerBEAN, String> colVacancyMaritalStatus = new TableColumn("Marital Status");
        TableColumn<EmployerBEAN, String> colVacancyFromCurrency = new TableColumn("Salary From Currency");
        TableColumn<EmployerBEAN, String> colVacancyToCurrency = new TableColumn("Salary To Currency");

        colVacancyJob.setCellValueFactory((TableColumn.CellDataFeatures<EmployerBEAN, String> param) -> param.getValue().jobProperty());
        colVacancySector.setCellValueFactory((TableColumn.CellDataFeatures<EmployerBEAN, String> param) -> param.getValue().employmentSectorProperty());
        colVacancyStatus.setCellValueFactory((TableColumn.CellDataFeatures<EmployerBEAN, String> param) -> param.getValue().vacancyStatusProperty());
        colVacancyNumberOf.setCellValueFactory((TableColumn.CellDataFeatures<EmployerBEAN, String> param) -> param.getValue().noOfVacanciesProperty());
        colVacancyGender.setCellValueFactory((TableColumn.CellDataFeatures<EmployerBEAN, String> param) -> param.getValue().genderProperty());
        colVacancyAgeFrom.setCellValueFactory((TableColumn.CellDataFeatures<EmployerBEAN, String> param) -> param.getValue().ageFromProperty());
        colVacancyAgeTo.setCellValueFactory((TableColumn.CellDataFeatures<EmployerBEAN, String> param) -> param.getValue().ageToProperty());
        colVacancyMaritalStatus.setCellValueFactory((TableColumn.CellDataFeatures<EmployerBEAN, String> param) -> param.getValue().maritalStatusProperty());
        colVacancyFromCurrency.setCellValueFactory((TableColumn.CellDataFeatures<EmployerBEAN, String> param) -> param.getValue().salaryFromAmountProperty());
        colVacancyToCurrency.setCellValueFactory((TableColumn.CellDataFeatures<EmployerBEAN, String> param) -> param.getValue().salaryToCurrencyProperty());

        tblVacancies.getColumns().addAll(colVacancyJob, colVacancySector, colVacancyStatus, colVacancyNumberOf, colVacancyGender, colVacancyAgeFrom, colVacancyAgeTo, colVacancyMaritalStatus, colVacancyFromCurrency, colVacancyToCurrency);
        tblVacancies.getItems().addAll(employerVacancyDAO.retrieveEmployerVacancy(employerId));
        return tblVacancies;
    }

    public void addEduProviderMenuListeners() {

        itemAddEduProvider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    boolean tabExists = false;
                    for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                        if (tabAdded.getUserData() != null) {
                            if (tabAdded.getUserData().toString().equalsIgnoreCase("add_eduprovider")) {
                                tabExists = true;
                                tabPaneSearchHome.getSelectionModel().select(tabAdded);
                            }
                        }
                    }
                    if (!tabExists) {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/search/colleges/FXMLCollegeRegistration.fxml"));
                        StackPane stackPaneRoot = new StackPane(root);
                        stackPaneRoot.setAlignment(Pos.CENTER);
//                        stackPaneRoot.getChildren().add(root);
                        ScrollPane scrollPaneRoot = new ScrollPane(stackPaneRoot);
                        scrollPaneRoot.setFitToWidth(true);
                        Tab tabAddEduProvider = new Tab("Add Educational Provider");
                        tabAddEduProvider.setContent(scrollPaneRoot);
                        tabAddEduProvider.setUserData("add_eduprovider");
                        tabAddEduProvider.setClosable(true);
                        tabPaneSearchHome.getTabs().add(tabAddEduProvider);
                        tabPaneSearchHome.getSelectionModel().select(tabAddEduProvider);
                    }

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
        itemAddEduCourseRequirements.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/search/colleges/courses/FXMLCollegeCourses.fxml"));
                    Parent root = (Parent) loader.load();
                    FXMLCollegeCoursesController controller = (FXMLCollegeCoursesController) loader.getController();
                    controller.initData(new EduProviderBEAN());
                    Stage stageInbox = new Stage();
                    Scene scene = new Scene(root);
                    stageInbox.setTitle("ADD/REMOVE COURSE REQUIREMENT DETAILS");
                    stageInbox.initModality(Modality.APPLICATION_MODAL);
                    stageInbox.setWidth(900);
                    stageInbox.setHeight(700);
                    stageInbox.initOwner(tabPaneSearchHome.getScene().getWindow());
                    stageInbox.setScene(scene);
                    stageInbox.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageInbox.show();
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
        itemEduProviderView.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean tabExists = false;
                for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                    if (tabAdded.getUserData() != null) {
                        if (tabAdded.getUserData().toString().equalsIgnoreCase("all_eduprovider")) {
                            tabExists = true;
                            tabPaneSearchHome.getSelectionModel().select(tabAdded);
                        }
                    }
                }
                if (!tabExists) {
                    /* ======================== Show All Added Employer View ==================== */
                    TableView<EduProviderBEAN> tblAllEduProviders = new TableView();
                    tblAllEduProviders.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    tblAllEduProviders.getStyleClass().add("table-employer-main");
                    Tab tabAllEduProvider = new Tab("All Educational Providers");
                    tabAllEduProvider.setContent(tblAllEduProviders);
                    tabAllEduProvider.setUserData("all_eduprovider");
                    TableColumn<EduProviderBEAN, String> colCollegeName = new TableColumn("College Name");
                    TableColumn<EduProviderBEAN, String> colUniversity = new TableColumn("University");
                    TableColumn<EduProviderBEAN, String> colEduCommision = new TableColumn("Commission (%)");
                    TableColumn<EduProviderBEAN, String> colEduCurrency = new TableColumn("Currency Used");
                    TableColumn<EduProviderBEAN, String> colEduContractFrom = new TableColumn("Contract From");
                    TableColumn<EduProviderBEAN, String> colEduContractTo = new TableColumn("Contract To");
                    colCollegeName.setCellValueFactory((TableColumn.CellDataFeatures<EduProviderBEAN, String> param) -> param.getValue().collegeProperty());
                    colUniversity.setCellValueFactory((TableColumn.CellDataFeatures<EduProviderBEAN, String> param) -> param.getValue().universityProperty());
                    colEduCommision.setCellValueFactory((TableColumn.CellDataFeatures<EduProviderBEAN, String> param) -> param.getValue().commissionProperty());
                    colEduCurrency.setCellValueFactory((TableColumn.CellDataFeatures<EduProviderBEAN, String> param) -> param.getValue().currencyProperty());
                    colEduContractFrom.setCellValueFactory((TableColumn.CellDataFeatures<EduProviderBEAN, String> param) -> param.getValue().contractFromProperty());
                    colEduContractTo.setCellValueFactory((TableColumn.CellDataFeatures<EduProviderBEAN, String> param) -> param.getValue().contractToProperty());
                    TableRowExpanderColumn<EduProviderBEAN> expanderEduProvider = new TableRowExpanderColumn<>(param -> {
                        VBox hboxExpandedView = new VBox(10);
                        HBox hboxButtons = new HBox(10);
                        Button btnAddNew = new Button("Add Course Requirements");
                        Button btnEdit = new Button("Edit");
                        /* ======================== Create Vacancy Details Table ==================== */
                        TableView<EduProviderBEAN> tblAllCourseReqmts = createCourseReqmtTableView(param.getValue().getCollegeId());
                        /* ======================== Bind Edit Button Visibility ==================== */
                        btnEdit.disableProperty().bind(Bindings.equal(tblAllCourseReqmts.getSelectionModel().selectedIndexProperty(), -1));
                        /* ======================== Load Vacancy Form ==================== */
                        btnAddNew.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {

                                try {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/search/colleges/courses/FXMLCollegeCourses.fxml"));
                                    Parent root = (Parent) loader.load();
                                    FXMLCollegeCoursesController controller = (FXMLCollegeCoursesController) loader.getController();
                                    controller.initData(param.getValue());
                                    Stage stageInbox = new Stage();
                                    Scene scene = new Scene(root);
                                    stageInbox.setTitle("ADD/REMOVE EDUCATIONAL PROVIDER DETAILS");
                                    stageInbox.initModality(Modality.APPLICATION_MODAL);
                                    stageInbox.setWidth(900);
                                    stageInbox.setHeight(700);
                                    stageInbox.initOwner(tabPaneSearchHome.getScene().getWindow());
                                    stageInbox.setScene(scene);
                                    stageInbox.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                                    stageInbox.show();
                                } catch (IOException ex) {
                                    logger.error(ex.getMessage());
                                    ex.printStackTrace();
                                }
                            }
                        });
                        /* ======================== Edit Vacancy Details ==================== */
                        btnEdit.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {

                                try {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/search/colleges/courses/FXMLCollegeCourses.fxml"));
                                    Parent root = (Parent) loader.load();
                                    FXMLCollegeCoursesController controller = (FXMLCollegeCoursesController) loader.getController();
                                    controller.initData(tblAllCourseReqmts.getSelectionModel().getSelectedItem());
                                    Stage stageInbox = new Stage();
                                    Scene scene = new Scene(root);
                                    stageInbox.setTitle("ADD/REMOVE COURSE REQUIREMENT DETAILS");
                                    stageInbox.initModality(Modality.APPLICATION_MODAL);
                                    stageInbox.setWidth(900);
                                    stageInbox.setHeight(700);
                                    stageInbox.initOwner(tabPaneSearchHome.getScene().getWindow());
                                    stageInbox.setScene(scene);
                                    stageInbox.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                                    stageInbox.show();
                                    stageInbox.setOnHiding(new EventHandler<WindowEvent>() {
                                        @Override
                                        public void handle(WindowEvent event) {
                                        
                                        itemEduProviderView.fire();
                                            System.out.println("FIRE btnEduProvider ************");
                                        }
                                    });
                                    
                                } catch (IOException ex) {
                                    logger.error(ex.getMessage());
                                    ex.printStackTrace();
                                }
                            }
                        });
                        hboxButtons.getChildren().addAll(btnAddNew, btnEdit);
                        hboxExpandedView.setStyle("-fx-padding:10px");

                        hboxExpandedView.setMaxWidth(Double.MAX_VALUE);
                        hboxExpandedView.setPrefHeight(Region.USE_COMPUTED_SIZE);
                        hboxExpandedView.getChildren().addAll(hboxButtons, tblAllCourseReqmts);
                        return hboxExpandedView;
                    });
                    expanderEduProvider.setText("Requirements(Press '+')");
                    //  createContextMenuInTables(tblAllEduProviders);
                    tblAllEduProviders.getColumns().addAll(expanderEduProvider, colCollegeName, colUniversity, colEduCommision, colEduCurrency, colEduContractFrom, colEduContractTo);
                    tblAllEduProviders.getItems().addAll(eduProviderDAO.retrieveEduProviderBasics());
                    TableFilter filter = new TableFilter(tblAllEduProviders);
                    tabPaneSearchHome.getTabs().add(tabAllEduProvider);
                    tabPaneSearchHome.getSelectionModel().select(tabAllEduProvider);

                }
            }

        });

    }

    public TableView<EduProviderBEAN> createCourseReqmtTableView(String collegeId) {

        TableView<EduProviderBEAN> tblCourseReq = new TableView();
//        tblVacancies.setPrefHeight(Region.USE_COMPUTED_SIZE);
        tblCourseReq.setPrefHeight(110);
        tblCourseReq.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tblCourseReq.getStyleClass().add("table-employer-vacancy");
        HBox.setHgrow(tblCourseReq, Priority.ALWAYS);
        Label lblPlaceholder = new Label("No Course Requirement Details available!");
        lblPlaceholder.setStyle("-fx-text-fill:red");
        tblCourseReq.setPlaceholder(lblPlaceholder);
        TableColumn<EduProviderBEAN, String> colCourse = new TableColumn("Course");
        TableColumn<EduProviderBEAN, String> colCourseCollege = new TableColumn("College");
        TableColumn<EduProviderBEAN, String> colCourseGender = new TableColumn("Gender");
        TableColumn<EduProviderBEAN, String> colCourseAgeFrom = new TableColumn("Age From");
        TableColumn<EduProviderBEAN, String> colCourseAgeTo = new TableColumn("Age To");
        TableColumn<EduProviderBEAN, String> colCourseCurrency = new TableColumn("Fee Currency");
        TableColumn<EduProviderBEAN, String> colCourseApplicationFee = new TableColumn("Application Fee");
        TableColumn<EduProviderBEAN, String> colCourseAnnualFee = new TableColumn("Annual Fee");

        //    colCourse.setCellValueFactory((TableColumn.CellDataFeatures<EduProviderBEAN, String> param) -> param.getValue().courseLevelProperty().concat(param.getValue().courseFieldProperty()));
        colCourse.setCellValueFactory((TableColumn.CellDataFeatures<EduProviderBEAN, String> param) -> param.getValue().levelAndFieldProperty());
        colCourseCollege.setCellValueFactory((TableColumn.CellDataFeatures<EduProviderBEAN, String> param) -> param.getValue().collegeProperty());
        colCourseGender.setCellValueFactory((TableColumn.CellDataFeatures<EduProviderBEAN, String> param) -> param.getValue().genderProperty());
        colCourseAgeFrom.setCellValueFactory((TableColumn.CellDataFeatures<EduProviderBEAN, String> param) -> param.getValue().ageFromProperty());
        colCourseAgeTo.setCellValueFactory((TableColumn.CellDataFeatures<EduProviderBEAN, String> param) -> param.getValue().ageToProperty());
        colCourseCurrency.setCellValueFactory((TableColumn.CellDataFeatures<EduProviderBEAN, String> param) -> param.getValue().feeCurrencyProperty());
        colCourseApplicationFee.setCellValueFactory((TableColumn.CellDataFeatures<EduProviderBEAN, String> param) -> param.getValue().applicationFeeProperty());
        colCourseAnnualFee.setCellValueFactory((TableColumn.CellDataFeatures<EduProviderBEAN, String> param) -> param.getValue().annualFeeProperty());
        tblCourseReq.getColumns().addAll(colCourse, colCourseCollege, colCourseGender, colCourseAgeFrom, colCourseAgeTo, colCourseCurrency, colCourseApplicationFee, colCourseAnnualFee);
        tblCourseReq.getItems().addAll(courseReqmtBasicDAO.retrieveEduProviderCourseReqmtBasics(collegeId));
        return tblCourseReq;

    }

    public void createContextMenuInTables(TableView<EduProviderBEAN> tblAllEduProviders) {
        tblAllEduProviders.setRowFactory(new Callback<TableView<EduProviderBEAN>, TableRow<EduProviderBEAN>>() {
            @Override
            public TableRow<EduProviderBEAN> call(TableView<EduProviderBEAN> param) {
                final ContextMenu contextMenu = new ContextMenu();
                final MenuItem itemOpenEnquiry = new MenuItem("Open Assesment Form");
                final MenuItem itemOpenRegistration = new MenuItem("Open Registration Form");
                final MenuItem itemForwardEnquiry = new MenuItem("Forward Enquiries");
                final MenuItem itemLockEnquiry = new MenuItem("Lock Enquiry");
                final TableRow<EduProviderBEAN> row = new TableRow<EduProviderBEAN>() {
                    @Override
                    protected void updateItem(EduProviderBEAN eduProviderBEAN, boolean empty) {
                        super.updateItem(eduProviderBEAN, empty);
                        if (!empty) {

                        }
                    }
                };
                contextMenu.getItems().addAll(itemOpenEnquiry, itemForwardEnquiry, itemOpenRegistration);

                row.contextMenuProperty().bind(
                        Bindings.when(row.emptyProperty())
                                .then((ContextMenu) null)
                                .otherwise(contextMenu));
                return row;
            }
        });

    }

    public void addTrainingMenuListners() {

        itemAddTrainingCourse.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    boolean tabExists = false;
                    for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                        if (tabAdded.getUserData() != null) {
                            if (tabAdded.getUserData().toString().equalsIgnoreCase("add_training_course")) {
                                tabExists = true;
                                tabPaneSearchHome.getSelectionModel().select(tabAdded);
                            }
                        }
                    }
                    if (!tabExists) {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/zs/ina/search/training/FXMLTraining.fxml"));
                        StackPane stackPaneRoot = new StackPane(root);
                        stackPaneRoot.setAlignment(Pos.CENTER);
//                        stackPaneRoot.getChildren().add(root);
                        ScrollPane scrollPaneRoot = new ScrollPane(stackPaneRoot);
                        scrollPaneRoot.setFitToWidth(true);
                        Tab tabAddTrainingCourse = new Tab("Add Training Course");
                        tabAddTrainingCourse.setContent(scrollPaneRoot);
                        tabAddTrainingCourse.setUserData("add_training_course");
                        tabAddTrainingCourse.setClosable(true);
                        tabPaneSearchHome.getTabs().add(tabAddTrainingCourse);
                        tabPaneSearchHome.getSelectionModel().select(tabAddTrainingCourse);
                    }

                } catch (IOException ex) {
                    logger.error(ex.toString());
                    ex.printStackTrace();
                }

            }
        });
        itemTrainingCourseReqmts.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/search/training/courses/FXMLTrainingCourseRequirements.fxml"));
                    Parent root = (Parent) loader.load();
                    FXMLTrainingCourseRequirementsController controller = (FXMLTrainingCourseRequirementsController) loader.getController();
                    controller.initData(new TrainingBEAN());
                    Stage stageInbox = new Stage();
                    Scene scene = new Scene(root);
                    stageInbox.setTitle("ADD/EDIT TRAINING COURSE REQUIREMENT DETAILS");
                    stageInbox.initModality(Modality.APPLICATION_MODAL);
                    stageInbox.setWidth(900);
                    stageInbox.setHeight(700);
                    stageInbox.initOwner(tabPaneSearchHome.getScene().getWindow());
                    stageInbox.setScene(scene);
                    stageInbox.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stageInbox.show();
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
        itemTrainingCourseView.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean tabExists = false;
                for (Tab tabAdded : tabPaneSearchHome.getTabs()) {
                    if (tabAdded.getUserData() != null) {
                        if (tabAdded.getUserData().toString().equalsIgnoreCase("all_training_courses")) {
                            tabExists = true;
                            tabPaneSearchHome.getSelectionModel().select(tabAdded);
                        }
                    }
                }
                if (!tabExists) {
                    /* ======================== Show All Added Employer View ==================== */
                    TableView<TrainingBEAN> tblAllTrainingCourses = new TableView();
                    tblAllTrainingCourses.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    tblAllTrainingCourses.getStyleClass().add("table-employer-main");
                    Tab tabAllTrainingCourses = new Tab("All Training Courses");
                    tabAllTrainingCourses.setContent(tblAllTrainingCourses);
                    tabAllTrainingCourses.setUserData("all_training_courses");
                    TableColumn<TrainingBEAN, String> colCourseName = new TableColumn("Course Name");
                    //    TableColumn<TrainingBEAN, String> colUniversity = new TableColumn("University");
                    TableColumn<TrainingBEAN, String> colDuration = new TableColumn("Duration");
                    TableColumn<TrainingBEAN, String> colCouseFee = new TableColumn("Course Fee");
                    TableColumn<TrainingBEAN, String> colExamFee = new TableColumn("Exam Fee");
                    TableColumn<TrainingBEAN, String> colTrainCommision = new TableColumn("Commission (%)");
                    TableColumn<TrainingBEAN, String> colTrainContractFrom = new TableColumn("Contract From");
                    TableColumn<TrainingBEAN, String> colTrainContractTo = new TableColumn("Contract To");
                    colCourseName.setCellValueFactory((TableColumn.CellDataFeatures<TrainingBEAN, String> param) -> param.getValue().trainingCourseProperty());
                    //    colUniversity.setCellValueFactory((TableColumn.CellDataFeatures<TrainingBEAN, String> param) -> param.getValue().affiliationProperty());
                    colDuration.setCellValueFactory((TableColumn.CellDataFeatures<TrainingBEAN, String> param) -> param.getValue().durationProperty());
                    colCouseFee.setCellValueFactory((TableColumn.CellDataFeatures<TrainingBEAN, String> param) -> param.getValue().courseFeeProperty());
                    colExamFee.setCellValueFactory((TableColumn.CellDataFeatures<TrainingBEAN, String> param) -> param.getValue().examFeeProperty());
                    colTrainCommision.setCellValueFactory((TableColumn.CellDataFeatures<TrainingBEAN, String> param) -> param.getValue().commissionProperty());
                    //          colTrainCourseFee.setCellValueFactory((TableColumn.CellDataFeatures<TrainingBEAN, String> param) -> param.getValue().courseFeeProperty());
                    colTrainContractFrom.setCellValueFactory((TableColumn.CellDataFeatures<TrainingBEAN, String> param) -> param.getValue().contractFromProperty());
                    colTrainContractTo.setCellValueFactory((TableColumn.CellDataFeatures<TrainingBEAN, String> param) -> param.getValue().contractToProperty());
                    TableRowExpanderColumn<TrainingBEAN> expanderTrainingCourse = new TableRowExpanderColumn<>(param -> {
                        VBox hboxExpandedView = new VBox(10);
                        HBox hboxButtons = new HBox(10);
                        //   Button btnAddNew = new Button("Add Training Course Requirements");
                        Button btnEdit = new Button("Edit");
                        /* ======================== Create All Training Course Requirement Basic Details Table ==================== */
                        TableView<TrainingBEAN> tblAllTrainCourseReqmts = createTrainCourseReqmtTableView(param.getValue().getTrainingId());
                        /* ======================== Bind Edit Button Visibility ==================== */
                        btnEdit.disableProperty().bind(Bindings.equal(tblAllTrainCourseReqmts.getSelectionModel().selectedIndexProperty(), -1));
                        /* ======================== Load Vacancy Form ==================== */
//                        btnAddNew.setOnAction(new EventHandler<ActionEvent>() {
//                            @Override
//                            public void handle(ActionEvent event) {
//
//                                try {
//                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/search/training/courses/FXMLTrainingCourseRequirements.fxml"));
//                                    Parent root = (Parent) loader.load();
//                                    FXMLTrainingCourseRequirementsController controller = (FXMLTrainingCourseRequirementsController) loader.getController();
//                                    controller.initData(param.getValue());
//                                    Stage stageInbox = new Stage();
//                                    Scene scene = new Scene(root);
//                                    stageInbox.setTitle("ADD TRAINING COURSE REQUIREMENT DETAILS");
//                                    stageInbox.initModality(Modality.APPLICATION_MODAL);
//                                    stageInbox.setWidth(900);
//                                    stageInbox.setHeight(700);
//                                    stageInbox.initOwner(tabPaneSearchHome.getScene().getWindow());
//                                    stageInbox.setScene(scene);
//                                    stageInbox.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
//                                    stageInbox.show();
//                                } catch (IOException ex) {
//                                    logger.error(ex.getMessage());
//                                    ex.printStackTrace();
//                                }
//                            }
//                        });
                        /* ======================== Edit Vacancy Details ==================== */
                        btnEdit.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {

                                try {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/search/training/courses/FXMLTrainingCourseRequirements.fxml"));
                                    Parent root = (Parent) loader.load();
                                    FXMLTrainingCourseRequirementsController controller = (FXMLTrainingCourseRequirementsController) loader.getController();
                                    controller.initData(tblAllTrainCourseReqmts.getSelectionModel().getSelectedItem());
                                    Stage stageInbox = new Stage();
                                    Scene scene = new Scene(root);
                                    stageInbox.setTitle("EDIT TRAINING COURSE REQUIREMENT DETAILS");
                                    stageInbox.initModality(Modality.APPLICATION_MODAL);
                                    stageInbox.setWidth(900);
                                    stageInbox.setHeight(700);
                                    stageInbox.initOwner(tabPaneSearchHome.getScene().getWindow());
                                    stageInbox.setScene(scene);
                                    stageInbox.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                                    stageInbox.show();
                                } catch (IOException ex) {
                                    logger.error(ex.getMessage());
                                    ex.printStackTrace();
                                }
                            }
                        });
                        hboxButtons.getChildren().add(btnEdit);
                        hboxExpandedView.setStyle("-fx-padding:10px");

                        hboxExpandedView.setMaxWidth(Double.MAX_VALUE);
                        hboxExpandedView.setPrefHeight(Region.USE_COMPUTED_SIZE);
                        hboxExpandedView.getChildren().addAll(hboxButtons, tblAllTrainCourseReqmts);
                        return hboxExpandedView;
                    });
                    expanderTrainingCourse.setText("Requirements(Press '+')");
                    //  createContextMenuInTables(tblAllEduProviders);
                    tblAllTrainingCourses.getColumns().addAll(expanderTrainingCourse, colCourseName, colDuration, colCouseFee, colExamFee, colTrainCommision, colTrainContractFrom, colTrainContractTo);
                    tblAllTrainingCourses.getItems().addAll(trainingDAO.retrieveTrainingCourseBasics());
                    TableFilter filter = new TableFilter(tblAllTrainingCourses);
                    tabPaneSearchHome.getTabs().add(tabAllTrainingCourses);
                    tabPaneSearchHome.getSelectionModel().select(tabAllTrainingCourses);

                }
            }

        });

    }

    /**
     *
     * @param trainingId
     * @return
     */
    public TableView<TrainingBEAN> createTrainCourseReqmtTableView(String trainingId) {
        TableView<TrainingBEAN> tblTrainCourseReq = new TableView();
//        tblVacancies.setPrefHeight(Region.USE_COMPUTED_SIZE);
        tblTrainCourseReq.setPrefHeight(110);
        tblTrainCourseReq.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tblTrainCourseReq.getStyleClass().add("table-employer-vacancy");
        HBox.setHgrow(tblTrainCourseReq, Priority.ALWAYS);
        Label lblPlaceholder = new Label("No Course Requirement Details available!");
        lblPlaceholder.setStyle("-fx-text-fill:red");
        tblTrainCourseReq.setPlaceholder(lblPlaceholder);
        TableColumn<TrainingBEAN, String> colCourse = new TableColumn("Course");
        TableColumn<TrainingBEAN, String> colCourseGender = new TableColumn("Gender");
        TableColumn<TrainingBEAN, String> colCourseAgeFrom = new TableColumn("Age From");
        TableColumn<TrainingBEAN, String> colCourseAgeTo = new TableColumn("Age To");

        //    colCourse.setCellValueFactory((TableColumn.CellDataFeatures<EduProviderBEAN, String> param) -> param.getValue().courseLevelProperty().concat(param.getValue().courseFieldProperty()));
        colCourse.setCellValueFactory((TableColumn.CellDataFeatures<TrainingBEAN, String> param) -> param.getValue().trainingCourseProperty());
        colCourseGender.setCellValueFactory((TableColumn.CellDataFeatures<TrainingBEAN, String> param) -> param.getValue().genderProperty());
        colCourseAgeFrom.setCellValueFactory((TableColumn.CellDataFeatures<TrainingBEAN, String> param) -> param.getValue().ageFromProperty());
        colCourseAgeTo.setCellValueFactory((TableColumn.CellDataFeatures<TrainingBEAN, String> param) -> param.getValue().ageToProperty());
        tblTrainCourseReq.getColumns().addAll(colCourse, colCourseGender, colCourseAgeFrom, colCourseAgeTo);
        tblTrainCourseReq.getItems().addAll(trainReqmtBasicDAO.retrieveSelectedTrainingCourseReqmtBasics(trainingId));
        return tblTrainCourseReq;

    }

}
