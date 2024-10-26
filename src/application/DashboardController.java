package application;






import java.io.File;
import java.net.URL;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class DashboardController implements Initializable {
	
	
	
	
	 @FXML
   private Button line_chart_button;

   @FXML
   private Button bar_chart_button;

   @FXML
   private Button area_chart_button;

   @FXML
   private AnchorPane line_chart_page;

   @FXML //THEY ARE SAME LIKE XYCHART "<String, Integer>"
   private LineChart<String, Integer> lineChart;

   @FXML
   private Button show_Chart_Button;

   @FXML
   private AnchorPane bar_chart_page;

   @FXML
   private BarChart<String, Integer> barChart;

   @FXML
   private AnchorPane area_chart_page;

   @FXML
   private AreaChart<String, Integer> areaChart;
	
	
    @FXML
    private AnchorPane navbar;

    @FXML
    private Label user;

    @FXML
    private Button home;

    @FXML
    private Button student;
    
    @FXML
    private AnchorPane logout_card;

    @FXML
    private Button record;

    @FXML
    private Button data;

    @FXML
    private Button edit;

    @FXML
    private AnchorPane home_page;

    @FXML
    private AnchorPane student_page;

    @FXML
    private AnchorPane record_page;

    @FXML
    private AnchorPane data_page;

    @FXML
    private Circle circle;
    
    @FXML
    private Button cog_btn;

    @FXML
    private FontAwesomeIcon edit_icon;

    @FXML
    private AnchorPane total_student_card;

    @FXML
    private Label total_student;

    @FXML
    private AnchorPane total_enrolled_card;

    @FXML
    private Label total_enrolled;

    @FXML
    private AnchorPane total_graduated_card;

    @FXML
    private Label total_graduated;

    @FXML
    private AnchorPane total_inactive_card;

    @FXML
    private Label total_inactive;

    @FXML
    private TextField id;

    @FXML
    private TextField surname;

    @FXML
    private TextField given;

    @FXML
    private ComboBox<?> gender;

    @FXML
    private ImageView image_view;

    @FXML
    private Label file_path;

    @FXML
    private Button insert_image;
    
    @FXML
    private Button logout_button;

    @FXML
    private Button insert;

    @FXML
    private Button update;

    @FXML
    private Button clear;

    @FXML
    private Button delete;

    @FXML
    private TableView<Data> table_view;

    @FXML
    private TableColumn<Data, Integer> col_id;

    @FXML
    private TableColumn<Data, String> col_surname;

    @FXML
    private TableColumn<Data, String> col_given;

    @FXML
    private TableColumn<Data, String> col_gender;

    @FXML
    private TableColumn<Data, String> col_image;

    @FXML
    private Button print;

    @FXML
    private Label sr_id;

    @FXML
    private ImageView sr_view_image;

    @FXML
    private ComboBox<?> sr_current;

    @FXML
    private Button sr_update;

    @FXML
    private TableView<Data> sr_table_view;

    @FXML
    private TableColumn<Data, Integer> col_sr_id;

    @FXML
    private TableColumn<Data, String> col_sr_surname;

    @FXML
    private TableColumn<Data, String> col_sr_given;

    @FXML
    private TableColumn<Data, String> col_sr_gender;

    @FXML
    private TableColumn<Data, String> col_sr_current;

    @FXML
    private TableColumn<Data, String> col_sr_image;

    @FXML
    private Button sr_clear;

    @FXML
    private AnchorPane nav_chart;


    private Image image;


    //	    DATABASE TOOL
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    public void exit() {
        System.exit(0);
    }


    public void navButton() {

        home.setOnMouseClicked((MouseEvent event) -> {

            home.setStyle("-fx-background-color:linear-gradient(to bottom right,"
                    + " rgba(121, 172, 255, 0.6), rgba(255, 106, 239, 0.6));"
                    + "-fx-border-color:linear-gradient(to bottom, #517ab5, #ae44a5);"
                    + "-fx-border-width:0px 0px 0px 5px");

            student.setStyle("-fx-background-color:linear-gradient(to bottom right,"
                    + " rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2))");

            record.setStyle("-fx-background-color:linear-gradient(to bottom right,"
                    + " rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2))");

            data.setStyle("-fx-background-color:linear-gradient(to bottom right,"
                    + " rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2))");
        });

        student.setOnMouseClicked((MouseEvent event) -> {

            home.setStyle("-fx-background-color:linear-gradient(to bottom right,"
                    + " rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2))");

            student.setStyle("-fx-background-color:linear-gradient(to bottom right,"
                    + " rgba(121, 172, 255, 0.6), rgba(255, 106, 239, 0.6));"
                    + "-fx-border-color:linear-gradient(to bottom, #517ab5, #ae44a5);"
                    + "-fx-border-width:0px 0px 0px 5px");

            record.setStyle("-fx-background-color:linear-gradient(to bottom right,"
                    + " rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2))");

            data.setStyle("-fx-background-color:linear-gradient(to bottom right,"
                    + " rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2))");
        });

        record.setOnMouseClicked((MouseEvent event) -> {

            home.setStyle("-fx-background-color:linear-gradient(to bottom right,"
                    + " rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2))");

            student.setStyle("-fx-background-color:linear-gradient(to bottom right,"
                    + " rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2))");

            record.setStyle("-fx-background-color:linear-gradient(to bottom right,"
                    + " rgba(121, 172, 255, 0.6), rgba(255, 106, 239, 0.6));"
                    + "-fx-border-color:linear-gradient(to bottom, #517ab5, #ae44a5);"
                    + "-fx-border-width:0px 0px 0px 5px");

            data.setStyle("-fx-background-color:linear-gradient(to bottom right,"
                    + " rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2))");
        });

        data.setOnMouseClicked((MouseEvent event) -> {

            home.setStyle("-fx-background-color:linear-gradient(to bottom right,"
                    + " rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2))");

            student.setStyle("-fx-background-color:linear-gradient(to bottom right,"
                    + " rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2))");

            record.setStyle("-fx-background-color:linear-gradient(to bottom right,"
                    + " rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2))");

            data.setStyle("-fx-background-color:linear-gradient(to bottom right,"
                    + " rgba(121, 172, 255, 0.6), rgba(255, 106, 239, 0.6));"
                    + "-fx-border-color:linear-gradient(to bottom, #517ab5, #ae44a5);"
                    + "-fx-border-width:0px 0px 0px 5px");
        });

    }

    public void defaultHomeDesign() {

        home.setStyle("-fx-background-color:linear-gradient(to bottom right,"
                + " rgba(121, 172, 255, 0.6), rgba(255, 106, 239, 0.6));"
                + "-fx-border-color:linear-gradient(to bottom, #517ab5, #ae44a5);"
                + "-fx-border-width:0px 0px 0px 5px");

    }

    public void changePage() {

        if (home.isFocused()) {

            home_page.setVisible(true);
            student_page.setVisible(false);
            record_page.setVisible(false);
            data_page.setVisible(false);

//	    	            TO SEE IMMIDIATELY THE RECORD!
            totalStudent();
            totalGraduated();
            totalEnrolled();
            totalInactive();

            nav_chart.setVisible(false);

        } else if (student.isFocused()) {

            home_page.setVisible(false);
            student_page.setVisible(true);
            record_page.setVisible(false);
            data_page.setVisible(false);

            showData();

            nav_chart.setVisible(false);

        } else if (record.isFocused()) {

            home_page.setVisible(false);
            student_page.setVisible(false);
            record_page.setVisible(true);
            data_page.setVisible(false);

              showStudentRecord();

            nav_chart.setVisible(false);

        } else if (data.isFocused()) {

            home_page.setVisible(false);
            student_page.setVisible(false);
            record_page.setVisible(false);
            data_page.setVisible(true);

            nav_chart.setVisible(true);

        }

    }

    public void navigationChartButton() {

        if (line_chart_button.isFocused()) {

            line_chart_page.setVisible(true);
            bar_chart_page.setVisible(false);
            area_chart_page.setVisible(false);

        } else if (bar_chart_button.isFocused()) {

            line_chart_page.setVisible(false);
            bar_chart_page.setVisible(true);
            area_chart_page.setVisible(false);

        } else if (area_chart_button.isFocused()) {

            line_chart_page.setVisible(false);
            bar_chart_page.setVisible(false);
            area_chart_page.setVisible(true);

        }

    }

    public void editImageDesign() {

        edit.setVisible(false);

        circle.setOnMouseEntered((MouseEvent event) -> {

            edit.setVisible(true);

        });

        circle.setOnMouseExited((MouseEvent event) -> {

            edit.setVisible(false);

        });

        edit.setOnMouseEntered((MouseEvent event) -> {

            edit.setVisible(true);

        });

        edit.setOnMouseExited((MouseEvent event) -> {

            edit.setVisible(false);
            edit_icon.setFill(Color.valueOf("#fff"));

        });

        edit.setOnMouseClicked((MouseEvent event) -> {

            edit_icon.setFill(Color.rgb(255, 106, 239));

        });

        edit.setOnMousePressed((MouseEvent event) -> {

            edit_icon.setFill(Color.rgb(255, 106, 239));

        });

        edit.setOnMouseReleased((MouseEvent event) -> {

            edit_icon.setFill(Color.valueOf("#fff"));

        });

    }

    public void account() {
        user.setText(User.username);
    }

    public void insertImage() {

        FileChooser open = new FileChooser();

        open.setTitle("Open image file");

        Stage stage = (Stage) navbar.getScene().getWindow();

        File file = open.showOpenDialog(stage);


        if (file != null) {

            User.path = file.getAbsolutePath();

            System.out.println(file.getAbsolutePath());

            image = new Image(file.toURI().toString(), 85, 85, false, true);

            circle.setFill(new ImagePattern(image));

            changeProfile();

        }

    }

    public void changeProfile() {

        connect = Database.connectDb();

        String uri = User.path;

        uri = uri.replace("\\", "\\\\");

        String sql = "UPDATE `account` SET `image` = '"
                + uri + "' WHERE username = '" + User.username + "'";

        try {

            statement = connect.createStatement();
            statement.executeUpdate(sql);

        } catch (Exception e) {
        }

    }

    public void displayImage() {

        String uri = "file:" + User.path;

        image = new Image(uri, 85, 85, false, true);

        circle.setFill(new ImagePattern(image));

    }

    public void hoverTotalCard() {

        total_student_card.setOnMouseEntered((MouseEvent event) -> {

            DropShadow shadow = new DropShadow(20, Color.valueOf("#aaa"));

            shadow.setOffsetX(5);
            shadow.setOffsetY(3);

            total_student_card.setEffect(shadow);
            total_enrolled_card.setEffect(null);
            total_graduated_card.setEffect(null);
            total_inactive_card.setEffect(null);

        });

        total_student_card.setOnMouseExited((MouseEvent event) -> {

            total_student_card.setEffect(null);
            total_enrolled_card.setEffect(null);
            total_graduated_card.setEffect(null);
            total_inactive_card.setEffect(null);

        });

        total_enrolled_card.setOnMouseEntered((MouseEvent event) -> {

            DropShadow shadow = new DropShadow(20, Color.valueOf("#aaa"));

            shadow.setOffsetX(5);
            shadow.setOffsetY(3);

            total_student_card.setEffect(null);
            total_enrolled_card.setEffect(shadow);
            total_graduated_card.setEffect(null);
            total_inactive_card.setEffect(null);

        });

        total_enrolled_card.setOnMouseExited((MouseEvent event) -> {

            total_student_card.setEffect(null);
            total_enrolled_card.setEffect(null);
            total_graduated_card.setEffect(null);
            total_inactive_card.setEffect(null);

        });

        total_graduated_card.setOnMouseEntered((MouseEvent event) -> {

            DropShadow shadow = new DropShadow(20, Color.valueOf("#aaa"));

            shadow.setOffsetX(5);
            shadow.setOffsetY(3);

            total_student_card.setEffect(null);
            total_enrolled_card.setEffect(null);
            total_graduated_card.setEffect(shadow);
            total_inactive_card.setEffect(null);

        });

        total_graduated_card.setOnMouseExited((MouseEvent event) -> {

            total_student_card.setEffect(null);
            total_enrolled_card.setEffect(null);
            total_graduated_card.setEffect(null);
            total_inactive_card.setEffect(null);

        });

        total_inactive_card.setOnMouseEntered((MouseEvent event) -> {

            DropShadow shadow = new DropShadow(20, Color.valueOf("#aaa"));

            shadow.setOffsetX(5);
            shadow.setOffsetY(3);

            total_student_card.setEffect(null);
            total_enrolled_card.setEffect(null);
            total_graduated_card.setEffect(null);
            total_inactive_card.setEffect(shadow);

        });

        total_inactive_card.setOnMouseExited((MouseEvent event) -> {

            total_student_card.setEffect(null);
            total_enrolled_card.setEffect(null);
            total_graduated_card.setEffect(null);
            total_inactive_card.setEffect(null);

        });

    }

    public void totalStudent() {
        Connection connect = Database.connectDb();
        String sql = "SELECT count (surname) FROM student WHERE current != ''";
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {

                String totalStudent = result.getString("count(surname)");

               total_student.setText(totalStudent);

            }

        } catch (Exception e) {
        }

    }

    public void totalGraduated() {

        connect = Database.connectDb();

        String sql = "SELECT count(surname) FROM student WHERE current = 'Graduated'";

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {

                String totalGraduated = result.getString("count(surname)");

                total_graduated.setText(totalGraduated);

            }

        } catch (Exception e) {
        }

    }

    public void totalEnrolled() {

        connect = Database.connectDb();

        String sql = "SELECT count(surname) FROM student WHERE current = 'Enrolled'";

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {

                String totalEnrolled = result.getString("count(surname)");

                total_enrolled.setText(totalEnrolled);

            }

        } catch (Exception e) {
        }

    }

    public void totalInactive() {

        connect = Database.connectDb();

        String sql = "SELECT count(surname) FROM student WHERE current = 'Inactive'";

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {

                String totalInactive = result.getString("count(surname)");

                total_inactive.setText(totalInactive);

            }

        } catch (Exception e) {
        }

    }

    // -----------------------------------------
    //student
    public void textfieldRecord() {
        if (id.isFocused()) {
            id.setStyle("fx-background-color:#fff; -fx-border-width:2px");
            surname.setStyle("fx-background-color:transparent; -fx-border-width:1px");
            given.setStyle("fx-background-color:transparent; -fx-border-width:1px");
            gender.setStyle("fx-background-color:transparent; -fx-border-width:1px");
        } else if (surname.isFocused()) {
            id.setStyle("fx-background-color:transparent; -fx-border-width:1px");
            surname.setStyle("fx-background-color:#fff; -fx-border-width:2px");
            given.setStyle("fx-background-color:transparent; -fx-border-width:1px");
            gender.setStyle("fx-background-color:transparent; -fx-border-width:1px");

        } else if (given.isFocused()) {
            id.setStyle("fx-background-color:transparent; -fx-border-width:1px");
            surname.setStyle("fx-background-color:transparent; -fx-border-width:1px");
            given.setStyle("fx-background-color:#fff; -fx-border-width:2px");
            gender.setStyle("fx-background-color:transparent; -fx-border-width:1px");

        } else if (gender.isFocused()) {
            id.setStyle("fx-background-color:transparent; -fx-border-width:1px");
            surname.setStyle("fx-background-color:transparent; -fx-border-width:1px");
            given.setStyle("fx-background-color:transparent; -fx-border-width:1px");
            gender.setStyle("fx-background-color:#fff; -fx-border-width:2px");

        }

    }



    public void insertImageData() {

        FileChooser open = new FileChooser();

        Stage stage = (Stage) navbar.getScene().getWindow();

        File file = open.showOpenDialog(stage);

        if (file != null) {

            System.out.println("IMAGE PATH: " + file.getAbsolutePath());

            file_path.setText(file.getAbsolutePath());

            image = new Image(file.toURI().toString(), 110, 110, false, true);

            image_view.setImage(image);

        } else {

            System.out.println("NO DATA EXIST");

        }

    }
    public ObservableList<Data> listData() {
        //Statement statement;
        ObservableList<Data> dataList = FXCollections.observableArrayList();
        Connection connect = Database.connectDb();
        String sql = "SELECT * FROM student";
        try {
            statement = connect.createStatement();
            ResultSet result = statement.executeQuery(sql);
            Data data;
            while (result.next()) {
                data = new Data(result.getInt("student id"),
                        result.getString("surname"),
                        result.getString("given"),
                        result.getString("gender"),
                        result.getString("image"));
                dataList.addAll(data);
            }
        } catch (Exception e) {
           return dataList;
        }
        return dataList;
    }

    public void showData() {
        ObservableList<Data> show = listData();
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        col_given.setCellValueFactory(new PropertyValueFactory<>("given"));
        col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        col_image.setCellValueFactory(new PropertyValueFactory<>("image"));

        table_view.setItems(show);

    }

    private String genderData[] = {"Male", "Female", "Others"};

    public void comboBox() {

        List<String> list = new ArrayList<>();

        for (String data1 : genderData) {

            list.add(data1);

        }

        ObservableList dataList = FXCollections.observableArrayList(list);

        gender.setItems(dataList);

    }

    public void insert() {

        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String dateFormat = format.format(date);

        connect = Database.connectDb();
        String sql = "INSERT INTO student VALUES(?,?,?,?,?,?,?)";

        try {

            if (id.getText().isEmpty() | surname.getText().isEmpty()
                    | given.getText().isEmpty()
                    | gender.getSelectionModel().isEmpty()
                    | image_view.getImage() == null) {

                Alert alert = new Alert(AlertType.ERROR);

                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Enter all blank fields!");
                alert.showAndWait();

            } else {

                String file = file_path.getText().replace("\\", "\\\\");

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, id.getText());
                prepare.setString(2, surname.getText());
                prepare.setString(3, given.getText());
                prepare.setString(4, (String) gender.getSelectionModel().getSelectedItem());
                prepare.setString(5, file);
                prepare.setString(6, "");
                prepare.setString(7, dateFormat);

                prepare.executeUpdate();

                showData();
               clear();
            }
        } catch (Exception e) {
        }

    }

    public void update() {

        connect = Database.connectDb();

        String replace = file_path.getText().replace("\\", "\\\\");

        String sql = "UPDATE student SET `surname` = '" + surname.getText()
                + "', `given` = '" + given.getText()
                + "', `gender` = '"
                + gender.getSelectionModel().getSelectedItem()
                + "', `image` = '" + replace
                + "' WHERE `student id` ='" + id.getText() + "'";

        try {
            if (id.getText().isEmpty() | surname.getText().isEmpty()
                    | given.getText().isEmpty()
                    | gender.getSelectionModel().isEmpty()
                    | image_view.getImage() == null) {

                Alert alert = new Alert(AlertType.ERROR);

                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Something wrong :3");
                alert.showAndWait();

            } else {

                statement = connect.createStatement();
                statement.executeUpdate(sql);

                Alert alert = new Alert(AlertType.INFORMATION);

                alert.setTitle(" Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");
                alert.showAndWait();

                showData();
                clear();

            }
        } catch (Exception e) {
        }

    }

    public void delete() {

        connect = Database.connectDb();

        String sql = "DELETE FROM student WHERE `student id` ='" + id.getText() + "'";

        try {

            if (id.getText().isEmpty()) {

                Alert alert = new Alert(AlertType.ERROR);

                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Enter your Student ID");
                alert.showAndWait();

            } else {

                Alert alert = new Alert(AlertType.CONFIRMATION);

                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get() != ButtonType.OK)
                    return;
                else {

                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                }

                showData();
                clear();

            }

        } catch (Exception e) {
        }

    }


    public void clear() {

        id.setText("");
        surname.setText("");
        given.setText("");
        gender.getSelectionModel().clearSelection();
        file_path.setText("");
        image_view.setImage(null);

    }

    public void selectData() {

        int num = table_view.getSelectionModel().getSelectedIndex();

        Data data1 = table_view.getSelectionModel().getSelectedItem();

        if (num - 1 < -1)
            return;

        id.setText(String.valueOf(data1.getId()));
        surname.setText(data1.getSurname());
        given.setText(data1.getGiven());
        gender.getSelectionModel().clearSelection();
        file_path.setText(data1.getImage());
        image = new Image("file:" + data1.getImage(), 110, 110, false, true);

        image_view.setImage(image);

    }


    //----------------------------------------------
    //student record
    public void SRFieldDesign() {

        if (sr_current.isFocused()) {

            sr_current.setStyle("-fx-background-color:#fff;"
                    + " -fx-border-width:2px");
            sr_id.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");

        } else if (sr_id.isFocused()) {

            sr_current.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            sr_id.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:2px");

        } else {

            sr_current.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            sr_id.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");

        }

    }

    private String current[] = {"Enrolled", "Graduated", "Inactive"};

    public void SRComboBox() {

        List<String> list = new ArrayList<>();

        for (String contain : current) {

            list.add(contain);

        }

        ObservableList addData = FXCollections.observableArrayList(list);

        sr_current.setItems(addData);

    }

    public ObservableList<Data> StudentRecordList() {

        connect = Database.connectDb();

        ObservableList<Data> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM student";

        try {

            statement = connect.createStatement();
            result = statement.executeQuery(sql);

            Data containData;

            while (result.next()) {

                containData = new Data(result.getInt("student id")
                        , result.getString("surname")
                        , result.getString("given")
                        , result.getString("gender")
                        , result.getString("current")
                        , result.getString("image"));

                listData.addAll(containData);

            }

        } catch (Exception e) {
        }

        return listData;

    }


    public void showStudentRecord() {

        ObservableList<Data> studentRecord = StudentRecordList();
        col_sr_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_sr_surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        col_sr_given.setCellValueFactory(new PropertyValueFactory<>("given"));
        col_sr_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        col_sr_current.setCellValueFactory(new PropertyValueFactory<>("current"));
        col_sr_image.setCellValueFactory(new PropertyValueFactory<>("image"));

        sr_table_view.setItems(studentRecord);

    }

    public void selectStudentRecord() {

        int index = sr_table_view.getSelectionModel().getSelectedIndex();

        Data containData = sr_table_view.getSelectionModel().getSelectedItem();

        if (index - 1 < -1)
            return;

        sr_id.setText(String.valueOf(containData.getId()));
        sr_current.getSelectionModel().clearSelection();

        String filePath = "file:" + containData.getImage();

        image = new Image(filePath, 110, 110, false, true);

        sr_view_image.setImage(image);

    }
    public void StudentRecordUpdate(){

        connect = Database.connectDb();

        String sql = "UPDATE student SET `current` = '" 
                + sr_current.getSelectionModel().getSelectedItem() 
                + "' WHERE `student id` = '" + sr_id.getText() + "'";

        try{

            if(sr_current.getSelectionModel().isEmpty()
                    | sr_id.getText().isEmpty()){

                Alert alert = new Alert(AlertType.ERROR);

                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Select the Student Data!");
                alert.showAndWait();

            }else{

                statement = connect.createStatement();
                statement.executeUpdate(sql);

                Alert alert = new Alert(AlertType.INFORMATION);

                alert.setTitle("MarcoMan Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");
                alert.showAndWait();

                showStudentRecord();
                clearStudentRecord();

            }

        }catch(Exception e){}

    }
    public void clearStudentRecord(){

        sr_id.setText("");
        sr_current.getSelectionModel().clearSelection();
        sr_view_image.setImage(null);

    }


    
    
    //logout
    public void openLogoutCard(){

        cog_btn.setOnMouseClicked((MouseEvent event) ->{

            if(!logout_card.isVisible()){

                logout_card.setVisible(true);

            }else{

                logout_card.setVisible(false);

            }

        });

    }

    
    private double x = 0;
    private double y = 0;

    public void logoutAccount(){

        logout_button.getScene().getWindow().hide();

        try{
    //                NAME OF LOGIN FORM
            Parent root = FXMLLoader.load(getClass().getResource("projet.fxml"));

            Scene scene = new Scene(root);

            Stage stage = new Stage();

            root.setOnMousePressed((MouseEvent event) ->{

                x = event.getSceneX();
                y = event.getSceneY();



            });

            root.setOnMouseDragged((MouseEvent event) ->{

                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);

                stage.setOpacity(0.8);

            });

            root.setOnMouseReleased((MouseEvent event) ->{

                stage.setOpacity(1);

            });

            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){}
    }
//data
    
    
    public void showChart(){
      connect = Database.connectDb();

      String sql = "SELECT count(surname),date FROM student WHERE current != '' ORDER BY date asc";

      XYChart.Series<String, Integer> chart = new XYChart.Series<>();

      try{

          prepare = connect.prepareStatement(sql);
          result = prepare.executeQuery();

          while(result.next()){

              Integer count = Integer.parseInt(result.getString("count(surname)"));

                  chart.getData().add(new XYChart.Data<>(result.getString("date"), count));
          }
          if(line_chart_page.isVisible()){

              lineChart.getData().add(chart);

          }else if(bar_chart_page.isVisible()){

              barChart.getData().add(chart);

          }else if(area_chart_page.isVisible()){

              areaChart.getData().add(chart);

          }
      }catch(Exception e){}

  }

    @Override
    public void initialize(URL url, ResourceBundle resource) {
    	account();


      navButton();

      editImageDesign(); 

      defaultHomeDesign();

      hoverTotalCard();

      showData();

      comboBox();
      
      SRComboBox();

      showStudentRecord();
      showChart();

    }
   
}
            


