package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
    private javafx.scene.layout.AnchorPane pane1;

    @FXML
    public javafx.scene.control.PasswordField pass;

    @FXML
    public javafx.scene.control.TextField name;

    @FXML
    public javafx.scene.control.Label lbl;

    @FXML
    public javafx.scene.control.Label oracle;




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
   private void loginButtonAction(ActionEvent event) throws IOException, SQLException,ClassNotFoundException {
        DbControl dbControl=new DbControl();
        String Uname=name.getText().toString();
        String Upass=pass.getText().toString();

        if(dbControl.check(Uname,Upass)){
            AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("/sample/Home.fxml"));
            pane1.getChildren().setAll(pnlOne);
        }
        else {
          lbl.setText("Sorry invalid credentials!");
          lbl.setTextFill(Color.RED);
        }


    }

    @FXML
    private void registerButtonAction(ActionEvent event) throws IOException{
        GridPane pnlOne = FXMLLoader.load(this.getClass().getResource("/sample/register.fxml"));
        pane1.getChildren().setAll(pnlOne);

    }


    @FXML void moveToOracle()throws IOException{
        GridPane pn1OPane = FXMLLoader.load(this.getClass().getResource("/sample/Db_intial.fxml"));
        Scene scene =new Scene(pn1OPane);

        pane1.getChildren().setAll(pn1OPane);
    }


}
