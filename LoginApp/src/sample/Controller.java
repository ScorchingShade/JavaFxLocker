package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    private javafx.scene.control.Button loginButton;

    @FXML
    private javafx.scene.control.Button registerButton;

    @FXML
    private javafx.scene.layout.AnchorPane pane;


    @FXML
    private void closeButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
   private void loginButtonAction(ActionEvent event) throws IOException{
        AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("/sample/Home.fxml"));
        pane.getChildren().setAll(pnlOne);

    }

    @FXML
    private void registerButtonAction(ActionEvent event) throws IOException{
        GridPane pnlOne = FXMLLoader.load(this.getClass().getResource("/sample/register.fxml"));
        pane.getChildren().setAll(pnlOne);

    }

}
