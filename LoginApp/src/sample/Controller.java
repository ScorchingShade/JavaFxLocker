package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

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
    public javafx.scene.control.PasswordField pass;

    @FXML
    public javafx.scene.control.TextField name;

    @FXML
    public javafx.scene.control.Label lbl;



    @FXML
    private void closeButtonAction(){
        // get a handle to the stage


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close");
        alert.setHeaderText(null);

        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");

        alert.setContentText("Are you sure you want to exit?");

        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(yes,no);



        Optional<ButtonType> option = alert.showAndWait();




        if (option.get() == yes) {
            //do stuff
            Stage stage = (Stage) closeButton.getScene().getWindow();
            // do what you have to do
            stage.close();
        }
    }

    @FXML
   private void loginButtonAction(ActionEvent event) throws IOException, SQLException {
        DbControl dbControl=new DbControl();
        String Uname=name.getText().toString();
        String Upass=pass.getText().toString();
        if(dbControl.check(Uname,Upass)){
            AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("/sample/Home.fxml"));
            pane.getChildren().setAll(pnlOne);
        }
        else {
            lbl.setText("Wrong username or password! Try again!");
            lbl.setTextFill(Color.web("#ff0000", 0.8));
        }


    }

    @FXML
    private void registerButtonAction(ActionEvent event) throws IOException{
        GridPane pnlOne = FXMLLoader.load(this.getClass().getResource("/sample/register.fxml"));
        pane.getChildren().setAll(pnlOne);

    }

}
