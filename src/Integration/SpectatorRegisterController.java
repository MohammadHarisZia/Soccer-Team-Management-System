package Integration;

import BackEndOOP.Match;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import static Integration.Main.AlertBox;
import static Integration.Main.count;
import static Integration.RegisterController.personId;
import static Integration.tableController.countryID;
import static Integration.tableController.matchID;


public class SpectatorRegisterController {
    @FXML
    TextField yearsText,countryIDSpectatorText,MatchIDTxt;

    int reservation;

    @FXML
    RadioButton radioBtn1,radioBtn2;
    @FXML
    TableView<Match> matchTableID;
    @FXML
    TableColumn<Match, Integer> iMatchID;
    @FXML
    TableColumn<Match, String> iFixtureName;
    @FXML
    TableColumn<Match, String> iToBePlayed;
    @FXML
    TableColumn<Match, String> iNoOfSeats;
    @FXML
    TableColumn<Match, String> iDate;

    public void initialize(){
        ReservationCount();
        countryIDSpectatorText.setText(String.valueOf(countryID));
        MatchIDTxt.setText(String.valueOf(matchID));
    }

    public void countryView(ActionEvent event){
        Parent tableViewParent = null;
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/countryView.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
            window.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                System.out.println(a);
            }
            reservation= a+1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void backRegister (ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/Register.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        window.centerOnScreen();
    }

    public void getMatchView(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/MatchView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        window.centerOnScreen();
    }

    public void SpectatorRegister(ActionEvent event){
        if(countryIDSpectatorText.getText().equals("0")){
            AlertBox("Registration Error","Country ID cant be NULL");
        }
        else if(MatchIDTxt.getText().equals("0")){
            AlertBox("Registration Error","Match ID cant be NULL");
        }
        else if(yearsText.getText().isEmpty())
        {
            AlertBox("Registration Error","Fields are Null");
        }
        else if(!yearsText.getText().matches(".*\\d.*"))
            AlertBox("Registration Error","Years Since Fan Must be integer");
        else if(!yearsText.getText().isEmpty()){
            Connection conn = null;
            try {
                String url = "";
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                PreparedStatement pStat1 = conn.prepareStatement("insert into Reservation values (?,?,?)");
                pStat1.setInt(1,reservation);
                if(radioBtn1.isSelected())
                pStat1.setString(2,"Y" );
                if(radioBtn2.isSelected())
                    pStat1.setString(2,"N" );
                pStat1.setInt(3,matchID);
                pStat1.execute();

                PreparedStatement pStat2 = conn.prepareStatement("insert into Spectator values (?,?,?,?)");
                pStat2.setInt(1,personId);
                pStat2.setInt(2, Integer.parseInt(yearsText.getText()));
                pStat2.setInt(3,countryID);
                pStat2.setInt(4,reservation);
                pStat2.execute();
                AlertBox("Registration Successful","Registration Done Successfully");

                if(count==10)
                    url = "../GUI/Login.fxml";
                else if(count==12)
                    url = "../GUI/SpectatorManagement.fxml";

                Parent tableViewParent = FXMLLoader.load(getClass().getResource(url));
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
}
