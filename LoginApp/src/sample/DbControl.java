package sample;

import java.io.IOException;
import java.sql.*;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import oracle.jdbc.driver.OracleDriver;

public class DbControl {

    public String Oracle_User="";
    public  String Oracle_Pass="";
    static{
        try {
            Class.forName ("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static final String url="jdbc:oracle:thin:@localhost:1521:XE";
    private String user="System";
    private String pass="Newuser123";

    @FXML
    public javafx.scene.control.TextField OrUser;

    @FXML
    public javafx.scene.control.PasswordField OrPass;

    @FXML
    public javafx.scene.control.Button Orset;

    @FXML
    private javafx.scene.layout.AnchorPane pane;

    @FXML
    private javafx.scene.control.Button closeButton;



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
    private void setDb (ActionEvent event) throws IOException {

        this.user=OrUser.getText().toString();
        this.pass=OrPass.getText().toString();

        AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("/sample/Home.fxml"));
        pane.getChildren().setAll(pnlOne);



    }













    Connection Connections(Connection conn) throws SQLException {


        try {
            conn= DriverManager.getConnection(url,user,pass);
            Class.forName("oracle.jdbc.driver.OracleDriver");



            System.out.println("Connected");



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return conn;

        }

        return conn;

    }

   public void Insertion(String  Name, String Password) throws SQLException,ClassNotFoundException{
        Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","Newuser123");
        tableCheck();



        Statement stmt = conn.createStatement();
        String sql = "INSERT INTO tlogin " +
                "VALUES (id_seq.NEXTVAL, '"+Name+"', '"+Password+"')";
        stmt.executeUpdate(sql);


    }


    public void tableCheck() throws SQLException{
        Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","Newuser123");

        DatabaseMetaData dbm = conn.getMetaData();
        // check if "employee" table is there
        ResultSet tables = dbm.getTables(null, null, "TLOGIN", null);
        if (tables.next()) {
            // Table exists
            System.out.println("Table exists");
        }
        else {
            // Table does not exist
            Statement drop_seq=conn.createStatement();
            String a="SELECT COUNT(*) \n" +
                    "FROM user_sequences \n" +
                    "WHERE sequence_name = 'id_seq'";
            ResultSet res=drop_seq.executeQuery(a);
            if (res.next()){
               Statement drop=conn.createStatement();
               String a1="Drop sequence id_seq";
               drop.executeQuery(a1);

            }


            Statement seq=conn.createStatement();
            String s="CREATE SEQUENCE id_seq\n" +
                    " START WITH     100\n" +
                    " INCREMENT BY   1";
            seq.executeQuery(s);

            Statement stmt = conn.createStatement();

            String sql = "CREATE TABLE tlogin " +
                    "(id INTEGER PRIMARY KEY, " +
                    " name VARCHAR(255), " +
                    " pass VARCHAR(255))";

            stmt.executeUpdate(sql);
        }
        conn.close();

    }

    boolean check(String  Name, String Password) throws SQLException{
        Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","Newuser123");
        conn=Connections(conn);
        tableCheck();
        String sql="select * from tlogin where name=? and pass=?";
        System.out.println(Name+" "+Password);
        System.out.println(sql);
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setString(1,Name);
        ps.setString(2,Password);
        ResultSet rs=ps.executeQuery();

        System.out.println(rs);


       return rs.next();

    }


}
