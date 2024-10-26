package application;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class ServerController implements Initializable{

		@FXML
		private Button button_send;
		@FXML
		private TextField tf_message;
		@FXML
		private VBox vbox_messages;
		@FXML
		private ScrollPane sp_main;
		
		private Server server;
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			server = new Server(new ServerSocket(1234));
			
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("error creating server");
		}
		vbox_messages.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,Number oldValue, Number newValue) {
				sp_main.setVvalue((double) newValue);
			}
		});
		
		String msg = "Bonjour , veuiller entrez le id de l'étudiant et ecrivez OK pour savoir son état  ";
		Text text1 = new Text(msg);
		TextFlow textFlow=new TextFlow(text1);
		server.recieveMessageFromClient(vbox_messages);
		textFlow.setStyle	("-fx-color: rgb(255,153,204);"+
				"-fg-background-color:rgb(15,125,242);"+
				"-fx-background-darius:20px;");
		textFlow.setPadding(new Insets(5,5,5,10));
		HBox hBox= new HBox();
		hBox.setAlignment(Pos.CENTER_RIGHT);
		hBox.setPadding( new Insets(5,5,5,10));
		hBox.getChildren().add(textFlow);
		vbox_messages.getChildren().add(hBox);
		server.sendMessageToParent(msg);
		System.out.print("got the id\n");
		
		int id = server.recieveId(vbox_messages);
		System.out.print("got the id\n");
		ProjectDAO dao = new ProjectDAO();
		ResultSet rs = dao.selectID(id);
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			 while(rs.next()) {
				  String surname = rs.getString(rsmd.getColumnName(2));
				  String given = rs.getString(rsmd.getColumnName(3));
				  String gender = rs.getString(rsmd.getColumnName(4));
				  String state = rs.getString(rsmd.getColumnName(6));
				  String msg2 = "nom : "+surname+" | prenom : "+given+" | sexe : "+gender+" | état : "+state;
				  Text text2 = new Text(msg2);
					TextFlow textFlow2=new TextFlow(text2);
					server.recieveMessageFromClient(vbox_messages);
					textFlow2.setStyle	("-fx-color: rgb(255,153,204);"+
							"-fg-background-color:rgb(15,125,242);"+
							"-fx-background-darius:20px;");
					textFlow2.setPadding(new Insets(5,5,5,10));
					HBox hBox2= new HBox();
					hBox2.setAlignment(Pos.CENTER_RIGHT);
					hBox2.setPadding( new Insets(5,5,5,10));
					hBox2.getChildren().add(textFlow2);
					vbox_messages.getChildren().add(hBox2);
					server.sendMessageToParent(msg2);
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("id non trouvé\n");
		}
		server.recieveMessageFromClient(vbox_messages);
		button_send.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String messageToSend= tf_message.getText();
				if(!messageToSend.isEmpty()) {
					HBox hBox= new HBox();
					hBox.setAlignment(Pos.CENTER_RIGHT);
					hBox.setPadding( new Insets(5,5,5,10));
					Text text = new Text(messageToSend);
					TextFlow textFlow=new TextFlow(text);
					textFlow.setStyle	("-fx-color: rgb(239,242,255);"+
										"-fg-background-color:rgb(15,125,242);"+
										"-fx-background-darius:20px;");
					textFlow.setPadding(new Insets(5,5,5,10));
					hBox.getChildren().add(textFlow);
					vbox_messages.getChildren().add(hBox);
					server.sendMessageToParent(messageToSend);
					System.out.println(tf_message.getText());
					tf_message.clear();
					
				}
			}
		});
	}
	public static void addLabel(String messageFromClient,VBox vbox) {
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER_LEFT);
		hBox.setPadding(new Insets(5,5,5,10));
		Text text = new Text(messageFromClient);
		TextFlow textFlow=new TextFlow(text);
		textFlow.setStyle	(
						"-fg-background-color:rgb(233,233,235);"+
						"-fx-background-darius:20px;");
		textFlow.setPadding(new Insets(5,5,5,10));
		hBox.getChildren().add(textFlow);
		
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				vbox.getChildren().add(hBox);
				
			}
		});
		
		
		
	}
}