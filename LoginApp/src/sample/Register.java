package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class Register {

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    private javafx.scene.control.Button registerButton;

    @FXML
    private javafx.scene.layout.AnchorPane pane;

    @FXML
    private ImageView im1;

    @FXML
    public javafx.scene.control.PasswordField Regpass;

    @FXML
    public javafx.scene.control.TextField Regname;

    @FXML
    private javafx.scene.layout.AnchorPane Regpane1;

    @FXML
    private javafx.scene.control.Label reglabel;




    @FXML
    private void goBack() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Go Back");
        alert.setHeaderText(null);

        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");

        alert.setContentText("Are you sure you want to go back?");

        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(yes, no);


        Optional<ButtonType> option = alert.showAndWait();


        if (option.get() == yes) {

            try {
                GridPane Hp1 = FXMLLoader.load(this.getClass().getResource("/sample/sample.fxml"));
                Scene scene =new Scene(Hp1);

                pane.getChildren().setAll(Hp1);
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
    }



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
    private void registerButtonAction(ActionEvent event) throws IOException,ClassNotFoundException, SQLException {
        DbControl dbControl=new DbControl();
        String Uname=Regname.getText().toString();
        String Upass=Regpass.getText().toString();

        if(Uname.equals("")||Upass.equals("")){
            reglabel.setText("Invalid Credentials,try again!");
            reglabel.setTextFill(Color.RED);
        }
        else {
            dbControl.Insertion(Uname, Upass);
            AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("/sample/Home.fxml"));
            pane.getChildren().setAll(pnlOne);

        }


    }

}
