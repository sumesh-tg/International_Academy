/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javafx.application.Application;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import jidefx.scene.control.field.MaskTextField;
import static jidefx.scene.control.field.MaskTextField.INPUT_MASK_LETTER;
import org.controlsfx.control.Notifications;

/**
 *
 * @author 100018
 */
public class FxSpring extends Application {

    @Override
    public void start(Stage primaryStage) {
    
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                    Notifications.create()
              .title("Title Text")
              .text("Hello World 0!")
              .showWarning();
            }
        });
        MaskTextField field = new MaskTextField("ghhgjh");
        ObservableMap<Character, Callback<Character, Boolean>> verifiers = field.getInputMaskVerifiers();
// inherit the behavior from the old one
        verifiers.put('B', verifiers.get(INPUT_MASK_LETTER));
// remove the old one Or if you want to automatically convert the entered character 
// to another one, you can use the conversions map
        verifiers.remove(INPUT_MASK_LETTER);

        StackPane root = new StackPane();
        root.getChildren().addAll(btn, field);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
