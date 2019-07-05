package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Db_init_class {

    @FXML
    public javafx.scene.control.TextField OrUser;

    @FXML
    public javafx.scene.control.PasswordField OrPass;

    @FXML
    public javafx.scene.control.Button Orset;

    String user="",passw="";


    @FXML
    private void setDb (ActionEvent event){

        this.user=OrUser.getText().toString();
        this.passw=OrPass.getText().toString();



    }
}
