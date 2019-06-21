package sample;

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
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.lingala.zip4j.exception.ZipException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class Home {
    String source,zipPath = "";
    File file=null;
    Cipher ci;
    byte[] iv;
    SecureRandom srandom;
    SecretKey skey;
    IvParameterSpec ivspec;


    @FXML
    private javafx.scene.control.Button closeButton;
    @FXML
    private javafx.scene.control.Button back;
    @FXML
    private AnchorPane Hpane;
    @FXML
    private ImageView image;
    @FXML
    private javafx.scene.control.Button browse, decrypt;
    private javafx.scene.control.Button encrypt;

    @FXML
    private javafx.scene.control.TextField bro;
    @FXML
    public javafx.scene.control.Label info, info1;


    @FXML
    private void closeButtonAction() {
        // get a handle to the stage


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close");
        alert.setHeaderText(null);

        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");

        alert.setContentText("Are you sure you want to exit?");

        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(yes, no);


        Optional<ButtonType> option = alert.showAndWait();


        if (option.get() == yes) {
            //do stuff
            Stage stage = (Stage) closeButton.getScene().getWindow();
            // do what you have to do
            stage.close();
        }
    }


    @FXML
    private void SignOut() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sign Out");
        alert.setHeaderText(null);

        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");

        alert.setContentText("Are you sure you want to logout?");

        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(yes, no);


        Optional<ButtonType> option = alert.showAndWait();


        if (option.get() == yes) {
            image.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        GridPane Hp1 = FXMLLoader.load(this.getClass().getResource("/sample/sample.fxml"));
                        Hpane.getChildren().setAll(Hp1);
                    } catch (IOException i) {
                        i.printStackTrace();
                    }
                }
            });
        }


    }

    @FXML
    private void Browse(ActionEvent event) {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        final FileChooser fileChooser=new FileChooser();

        Stage stage = (Stage) Hpane.getScene().getWindow();

        this.file = directoryChooser.showDialog(stage);


        if (file != null) {
            System.out.println("Path: " + file.getAbsolutePath());
            bro.setText(file.getAbsolutePath());
            info1.setText("Ready for Operation!");

            this.source = file.getAbsolutePath();
            System.out.println(source);

        }

    }

    @FXML
    private void Encrypt(ActionEvent event) {


        Crypto c1 = new Crypto();

        /**
        boolean z= false;
        try {
            c1 = new Crypto();
            z = false;

           iv = new byte[128/8];
           srandom=new SecureRandom();
            srandom.nextBytes(iv);

           ivspec = new IvParameterSpec(iv);

            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            skey = kgen.generateKey();
           ci = Cipher.getInstance("AES/CBC/PKCS5Padding");

            ci.init(Cipher.ENCRYPT_MODE, skey, ivspec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        if (source.equals(null)) {
            info1.setText("Can't encrypt without a path");
        } else {
            zipPath = source + "zipper.zip";

            System.out.println(zipPath);
            try {
                c1.pack(source, zipPath);
                c1.encryptFile(ci,zipPath,zipPath);


                z=c1.deleteDirectory(file);
                info1.setText("Encrypted");
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
         */

        boolean z=false;
        if(source.equals(null)){
            info1.setText("Can't encrypt without a source file!");
        }
        else {
            try {
                c1.encryptzi(source);
                info1.setText("Encrypted");
                z=c1.deleteDirectory(file);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ZipException e) {
                e.printStackTrace();
            }
        }




    }

    @FXML
    private void Decrypt (ActionEvent event){
        Crypto c1 = new Crypto();
        System.out.println(source);
        boolean z=true;



/**

        if (zipPath.equals(null)) {
            info1.setText("Can't decrypt without a path");
        } else {

            try {
                try {
                    Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
                    ci.init(Cipher.DECRYPT_MODE, skey, ivspec);
                    c1.encryptFile(ci, zipPath, zipPath);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (InvalidAlgorithmParameterException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                }
               // c1.unzip(zipPath,source);
                //file=new File(zipPath);
                //z=c1.deleteDirectory(file);
                info1.setText("Decrypted");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
**/

        System.out.println(source);
        String DecryptP=bro.getText().toString()+".zip";

        if(bro==null){
            info1.setText("Please choose from source!");
        }
        else {
            try {
                c1.decryptzi(DecryptP);
                info1.setText("Decrypted");
                file=new File(source+".zip");
                z=c1.deleteDirectory(file);

            }
             catch (ZipException e) {
                e.printStackTrace();
            }
        }




    }






}

