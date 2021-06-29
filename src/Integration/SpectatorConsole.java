package Integration;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import static Integration.Main.AlertBox;
import static Integration.Main.count;
import static Integration.Main.StatusPersonID;
import static Integration.tableController.matchID;

public class SpectatorConsole {
    @FXML
    RadioButton radioBtn1,radioBtn2;

    @FXML
    TextField ReservationIDText, MatchIDText;

    public static int reservationCount = 0;
    public static int maxCount = 0;


    public int maxReservation(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            PreparedStatement P2=conn.prepareStatement("select noofseats from match where matchid = ?" );
            P2.setString(1,MatchIDText.getText());
            ResultSet resultSet2=P2.executeQuery();
            int a=0;
            while (resultSet2.next()) {
                a=resultSet2.getInt(1);
            }
            maxCount=a;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return maxCount;
    }

    public void ReservationCount(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            PreparedStatement P2=conn.prepareStatement("select max(reservationid) from reservation" );
            ResultSet resultSet2=P2.executeQuery();
            int a=0;
            while (resultSet2.next()) {
                a=resultSet2.getInt(1);
            }
            reservationCount=a;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void initialize(){
        MatchIDText.setText(String.valueOf(matchID));
        ReservationCount();
        ReservationIDText.setText(String.valueOf(reservationCount+1));
        ReservationIDText.setEditable(false);
    }

    public void addSpectator(ActionEvent event){
        if(!radioBtn1.isSelected() && !radioBtn2.isSelected())
        {
            AlertBox("Reservation Error","PLease Select Availability");
        }
        else if(MatchIDText.getText().equals("0"))
        {
            AlertBox("Reservation Error","Match ID cant be null");
        }
        else if(Integer.parseInt(ReservationIDText.getText()) > maxReservation()){
            AlertBox("Reservation Error","No Seats Available");
        }
        else{
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                PreparedStatement add = conn.prepareStatement("insert into Reservation values (?,?,?)");
                add.setInt(1,reservationCount+1);
                if(radioBtn1.isSelected()){
                    add.setString(2,"Y");
                }
                else if (radioBtn2.isSelected()){
                    add.setString(2,"N");
                }
                add.setString(3,MatchIDText.getText());
                add.execute();
                AlertBox("Added Successfully","Added Successfully");
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/Status.fxml"));
                Scene tableViewScene = new Scene(tableViewParent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(tableViewScene);
                window.show();
                window.centerOnScreen();
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void SpectatorMatchView(ActionEvent event){
        count=12;
        Parent tableViewParent = null;
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/matchView.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
            window.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backStatus (ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/Status.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        window.centerOnScreen();
    }

}
