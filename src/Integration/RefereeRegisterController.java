package Integration;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static Integration.Main.AlertBox;
import static Integration.RegisterController.personId;
import static Integration.tableController.matchID;

public class RefereeRegisterController {
    @FXML
    TextField matchesRefereeText,yellowCardsText,redCardsText,matchIDText;


    public void initialize(){
        matchIDText.setText(String.valueOf(matchID));
        matchIDText.setEditable(false);
    }

    public void refereeMatchView(ActionEvent event){
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

    public boolean isStringInt(String s)
    {
        try
        {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
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

    public void RefereeRegister(ActionEvent event){
        if(!isStringInt(matchesRefereeText.getText()) || !isStringInt(redCardsText.getText()) || !isStringInt(yellowCardsText.getText())){
            AlertBox("Registration Error","TextFields must be of Integer Type");
        }
        else if(!matchesRefereeText.getText().matches(".*\\d.*") || !yellowCardsText.getText().matches(".*\\d.*") || !redCardsText.getText().matches(".*\\d.*")){
            AlertBox("Registration Error","Fields must have integer values");
        }
        else if(!matchesRefereeText.getText().isEmpty() && !redCardsText .getText().isEmpty() && !yellowCardsText.getText().isEmpty()){
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                PreparedStatement pStat2 = conn.prepareStatement("insert into referee values (?,?,?,?,?)");
                pStat2.setInt(1,personId);
                pStat2.setString(2,matchesRefereeText.getText());
                pStat2.setString(3,redCardsText.getText());
                pStat2.setString(4,yellowCardsText.getText());

                if(matchIDText.getText().equals("0")){
                    pStat2.setNull(5,matchID);
                }
                else {
                    matchIDText.setText(String.valueOf(matchIDText));
                    pStat2.setInt(5, matchID);
                }
                pStat2.execute();
                AlertBox("Registration Successful","Registration Done Successfully");
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



}
