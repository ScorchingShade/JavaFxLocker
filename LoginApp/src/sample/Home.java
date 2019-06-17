package sample;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Home {
    @FXML
    private javafx.scene.control.Button closeButton;
    @FXML
    private javafx.scene.control.Button back;
    @FXML
    private AnchorPane Hpane;
    @FXML
    private ImageView image;


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
    private void SignOut(){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sign Out");
        alert.setHeaderText(null);

        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");

        alert.setContentText("Are you sure you want to logout?");

        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(yes,no);



        Optional<ButtonType> option = alert.showAndWait();




        if (option.get() == yes) {
            image.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try{
                        GridPane Hp1 = FXMLLoader.load(this.getClass().getResource("/sample/sample.fxml"));
                        Hpane.getChildren().setAll(Hp1);}
                    catch (IOException i){
                        i.printStackTrace();
                    }
                }
            });
        }



    }


}
