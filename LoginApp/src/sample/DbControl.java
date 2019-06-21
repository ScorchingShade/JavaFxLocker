package sample;

import java.sql.*;

public class DbControl {
    private static final String url="jdbc:oracle:thin:@localhost:1521:XE";
    private static final String user="System";
    private static final String pass="Newuser123";

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

    void Insertion(Connection conn,String  Name, String Password) throws SQLException,ClassNotFoundException{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn=Connections(conn);
        //before table there should be sequence in database
        String seq="create sequence ltiSequence start with 1 increment by 1;";
        String query1="create table LoginAppLog1(id number(20) primary key,name varchar2(30), password varchar2(40))";



        String query="Insert into LoginAppLogId values(ltiSequence.nextval,Name,Password)";


        Statement st= conn.createStatement();
        int i=st.executeUpdate(query);
        System.out.println("Query is updated "+i);

        String query2="Select * from product";

        ResultSet rs= st.executeQuery(query2);

        while(rs.next()){

            System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(1));

        }


    }


}
