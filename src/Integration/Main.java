package Integration;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main extends Application {

    @FXML
    Button login;

    @FXML
    TextField emailText;

    @FXML
    TextField passwordText;

    public static int count = 0;
    public static int StatusPersonID= 0;

    @Override
    public void start(Stage primaryStage){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/Status.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Football Team Management System");
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.show();
            primaryStage.centerOnScreen();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void Login (ActionEvent event) {
        try{
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/Login.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
            window.centerOnScreen();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loginBtn (ActionEvent event) {
        try{
            String emailStr = emailText.getText();
            String passwordStr = passwordText.getText();
            if(!emailStr.isEmpty() && !passwordStr.isEmpty())
            {
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");

                PreparedStatement pStat2 = conn.prepareStatement("select email,password,personid from person where email=?");
                pStat2.setString(1,emailStr);
                ResultSet resultSet2 = pStat2.executeQuery();

                if (!resultSet2.isBeforeFirst()) {
                    AlertBox("Login Error","Account Not Found");
                }
                else {
                    while (resultSet2.next()) {
                        if (!resultSet2.getString(2).equals(passwordStr))
                        {
                            AlertBox("Login Error", "Inaccurate Password");
                            break;
                        }
                        else if (resultSet2.getString(2).equals(passwordStr)) {
                            StatusPersonID = Integer.parseInt(resultSet2.getString(3));
                            String url2 = "";
                            if(count==1)
                                url2="../GUI/dashboardAdmin.fxml";
                            if(count==2)
                                url2="../GUI/dashboardManager.fxml";
                            if(count==3)
                                url2="../GUI/MedicalStaffDashboard.fxml";
                            if(count==4)
                                url2="../GUI/TrainerDashboard.fxml";
                            if(count==5)
                                url2="../GUI/dashboardPresident.fxml";
                            if(count==9)
                                url2="../GUI/AmbassadorDashboard.fxml";
                            if(count==10)
                                url2="../GUI/SpectatorDashboard.fxml";
                            Parent tableViewParent = FXMLLoader.load(getClass().getResource(url2));
                            Scene tableViewScene = new Scene(tableViewParent);
                            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            window.setScene(tableViewScene);
                            window.show();
                            window.centerOnScreen();
                        }
                    }
                }
            }
            else{
                AlertBox("Login Error","Email or Password is Empty");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void backLogin (ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/Status.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        window.centerOnScreen();
    }

    public void openRegister (ActionEvent event){
        Parent tableViewParent = null;
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/Register.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
            window.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void AlertBox (String title,String message){
        Stage window= new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(300);
        Label label= new Label();
        label.setText(message);
        Button closeButton = new Button("Close window");
        closeButton.setOnAction(e-> window.close());
        VBox layout = new VBox (10);
        layout.getChildren().addAll(label,closeButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene=new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public void admin (ActionEvent event) {
        count = 1;
        Login(event);
    }

    public void manager (ActionEvent event) {
        count = 2;
        Login(event);
    }

    public void medicalStaff (ActionEvent event) {
        count = 3;
        Login(event);
    }

    public void trainer (ActionEvent event) {
        count = 4;
        Login(event);
    }

    public void president (ActionEvent event) {
        count = 5;
        Login(event);
    }

    public void referee (ActionEvent event) {
        count = 6;
        openRegister(event);
    }

    public void player (ActionEvent event) {
        count = 7;
        openRegister(event);
    }

    public void scout (ActionEvent event) {
        count = 8;
        openRegister(event);
    }

    public void ambassador (ActionEvent event) {
        count = 9;
        Login(event);
    }

    public void spectator (ActionEvent event) {
        count = 10;
        Login(event);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
