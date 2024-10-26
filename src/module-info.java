module projet_javafx {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	requires mysql.connector.java;
	requires java.desktop;
	requires fontawesomefx;
	
	opens application to javafx.graphics, javafx.fxml;
}
