package application;

import java.sql.Connection;
import java.sql.DriverManager;


public class Database {

    public static Connection connectDb(){

        try{

            Class.forName("com.mysql.jdbc.Driver");

            Connection connect = 
                DriverManager.getConnection("jdbc:mysql://localhost/projet","root","");

            return connect;

        }catch(Exception e){e.printStackTrace();} 

        return null;
    }

}
