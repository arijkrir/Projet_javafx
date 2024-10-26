package application;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Controller implements Initializable {

	@FXML
    private Hyperlink create_acc;

    @FXML
    private Hyperlink login_acc;

    @FXML
    private Button login_btn;

    @FXML
    private Button parent_button;
    
    @FXML
    private AnchorPane login_form;

    @FXML
    private Label m;

    @FXML
    private Label m1;

    @FXML
    private PasswordField password;

    @FXML
    private Button signup_btn;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private TextField su_email;

    @FXML
    private PasswordField su_password;

    @FXML
    private TextField su_username;

    @FXML
    private TextField username;

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    @FXML
    void exit(ActionEvent event) {
		System.out.println("exit...");
    	System.exit(0);
    }
//
//    @FXML
//    void login(ActionEvent event) {
//
//    }
//
//    @FXML
//    void signup(ActionEvent event) {
//
//    }
//
//    @FXML
//    void textfieldDesign(MouseEvent event) {
//
//    }
//
//    @FXML
//    void textfieldDesign1(MouseEvent event) {
//
//    }
//
//    
//    public void exit() {
//    	
//    }
    
    public void textfieldDesign() {
    	if(username.isFocused()) {
    		username.setStyle("-fx-background-color:#fff;"
    				+ "-fx-border-width:2px");
    		password.setStyle("-fx-background-color:transparent;"
    				+ "-fx-border-width:1px");
    	}else if(password.isFocused()) {
    		username.setStyle("-fx-background-color:transparent;"
    				+ "-fx-border-width:1px");
    		password.setStyle("-fx-background-color:#fff;"
    				 				+"-fx-border-width:2px");
    	}
    }
    public void textfieldDesign1() {
    	if(su_username.isFocused()) {
    		su_username.setStyle("-fx-background-color:#fff;"
    				+ "-fx-border-width:2px");
    		su_password.setStyle("-fx-background-color:transparent;"
    				+ "-fx-border-width:1px");
    		su_email.setStyle("-fx-background-color:transparent;"
    				+ "-fx-border-width:1px");
    	}
    	else if(su_email.isFocused()) {
    		su_username.setStyle("-fx-background-color:transparent;"
    				+ "-fx-border-width:1px");
    		su_password.setStyle("-fx-background-color:transparent;"
    				+ "-fx-border-width:1px");
    		su_email.setStyle("-fx-background-color:#fff;"
    				+ "-fx-border-width:2px");}
    	else if(su_password.isFocused()) {
    		su_username.setStyle("-fx-background-color:transparent;"
    				+ "-fx-border-width:1px");
    		su_password.setStyle("-fx-background-color:#fff;"
    				+ "-fx-border-width:2px");;
    		su_email.setStyle("-fx-background-color:transparent;"
    				+ "-fx-border-width:1px");}
    }
    public void dropShadowEffect() {
    	DropShadow original= new DropShadow(20, Color.valueOf("#f8dae9"));
    	original.setRadius(30);
    	m.setEffect(original);
    	m1.setEffect(original);
    	m.setOnMouseEntered((MouseEvent event) -> {
    		DropShadow shadow= new DropShadow(60, Color.valueOf("#f8dae9"));
    	m.setCursor(Cursor.HAND);
    		m.setStyle("-fx-text-fill:#ebdae3");
    		m.setEffect(shadow);
    	});
    	m.setOnMouseExited((MouseEvent event)->{
    		DropShadow shadow= new DropShadow(20, Color.valueOf("#f8dae9"));
    		shadow.setRadius(30);
    		m.setStyle("-fx-text-fill:#000");
    		m.setEffect(shadow);
    	});
       	m1.setOnMouseEntered((MouseEvent event) -> {
    		DropShadow shadow= new DropShadow(60, Color.valueOf("#f8dae9"));
    	m1.setCursor(Cursor.HAND);
    		m1.setStyle("-fx-text-fill:#ebdae3");
    		m1.setEffect(shadow);
    	});
       	m1.setOnMouseExited((MouseEvent event)->{
    		DropShadow shadow= new DropShadow(20, Color.valueOf("#f8dae9"));
    		shadow.setRadius(30);
    		m1.setStyle("-fx-text-fill:#000");
    		m1.setEffect(shadow);
    	});

    }
    public void changeForm(ActionEvent event) {
    	if(event.getSource()== create_acc) {
    		signup_form.setVisible(true);
    		login_form.setVisible(false);

    		su_username.setText("");
    		su_password.setText("");
    		su_email.setText("");

    	}else if(event.getSource()== login_acc) {
    		login_form.setVisible(true);
    		signup_form.setVisible(false);

    		su_username.setText("");
    		su_password.setText("");
    		su_email.setText("");

    	}

    }
    public boolean validationEmail() {
    	Pattern pattern=Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
    	Matcher match=pattern.matcher(su_email.getText());
    	if(match.find()&& match.group().equals(su_email.getText())) {
    		return true;
    	}else {
    		Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("invalid Email");
			alert.showAndWait();
    		return false;
    	}

    }
    
    public void login() {
    	Connection connect = Database.connectDb();
    	String sql="SELECT * FROM account WHERE username=? and password=? ";
    	try {
    		
    		PreparedStatement prepare = connect.prepareStatement(sql);
    		prepare.setString(1, username.getText());
    		prepare.setString(2, password.getText());
    		ResultSet result = prepare.executeQuery();
    		if(result.next()) {
    			
    			
    			
    			Alert alert=new Alert(AlertType.INFORMATION);
    			alert.setTitle(" success Message");
    			alert.setHeaderText(null);
    			alert.setContentText("Successfully Login !");
    			alert.showAndWait();

    			login_btn.getScene().getWindow().hide();

    			Parent root=FXMLLoader.load(getClass().getResource("dashboard.fxml"));

    			Scene scene=new Scene(root);

    			Stage stage=new Stage();
    			stage.initStyle(StageStyle.TRANSPARENT);

    			stage.setScene(scene);

    			stage.show();


    		}else {
    			//username ou password faut
    			Alert alert=new Alert(AlertType.ERROR);
    			alert.setTitle("Error Message");
    			alert.setHeaderText(null);
    			alert.setContentText("Wrong Username/Password !");
    			alert.showAndWait();

    		}
    	}catch(Exception e) {

    	}
    }
    
    public void parent() {
    	parent_button.getScene().getWindow().hide();

		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("chat.fxml"));
			Scene scene=new Scene(root,500,500);

			Stage stage=new Stage();
			//stage.initStyle(StageStyle.TRANSPARENT);
			stage.setTitle("school");
			stage.setScene(scene);

			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    public void countRow(){

        connect = Database.connectDb();

//        TO COUNT THE DATA EXCLUDING NULL ON THE DATABASE
        String sql = "SELECT count(image) FROM account WHERE image = ''";



        try{

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){

                String count = result.getString("count(image)");

                System.out.println("COUNT: "+count);

            }

        }catch(Exception e){}

    }

    
    
    
    
    //insert dans database 
    public void signup() {
    	Connection connect = Database.connectDb();
    	String sql="INSERT INTO account (username,password,email) VALUES(?,?,?)";

    	try {
    		if(su_username.getText().isEmpty()| su_password.getText().isEmpty()
    				|su_email.getText().isEmpty()) {
    		Alert alert=new Alert(AlertType.ERROR);
    		alert.setTitle("Error Message");
    		alert.setHeaderText(null);
    		alert.setContentText("Enter all field blanks");
    		alert.showAndWait();    		}
    		else if(su_password.getText().length()<8) {
    			Alert alert=new Alert(AlertType.ERROR);
    			alert.setTitle("Error Message");
    			alert.setHeaderText(null);
    			alert.setContentText("invalid password");
    			alert.showAndWait();
    		}
    		else {
    			if(validationEmail()){

    				  String uri = "C:\\Users\\arijk\\Desktop\\projet javafx\\src\\application\\logo.jpg";

                      User.path = uri;
    				
    				
    				
    		PreparedStatement prepare = connect.prepareStatement(sql);
    		prepare.setString(1, su_username.getText());
    		prepare.setString(2, su_password.getText());
    		prepare.setString(3, su_email.getText());

    		prepare.execute();

    		su_email.setText("");
    		su_username.setText("");
    		su_password.setText("");


    		Alert alert=new Alert(AlertType.INFORMATION);
    		alert.setTitle("Message");
    		alert.setHeaderText(null);
    		alert.setContentText("Successfully create new account !");
    		alert.showAndWait();
    			}
    		}

    	}catch(Exception e) {e.printStackTrace();

    	}
    }
    
    @Override
public void initialize(URL url,ResourceBundle rb) {
	dropShadowEffect();
	 countRow();
}
}