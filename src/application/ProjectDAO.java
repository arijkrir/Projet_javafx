package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ProjectDAO {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    
	 Connection con = null;
	 Statement st = null;
	 PreparedStatement prepare = null;
	 public ProjectDAO() {
		 con = Database.connectDb();
			if (con != null)
				try {
					st = con.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 }
	 public ResultSet selectID(int id) {
		 String req = "select * from student where 'student id' ="+ id;
		 try {
			return st.executeQuery(req);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	 }
}