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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static Integration.Main.AlertBox;
import static Integration.Main.count;
import static Integration.tableController.scoutPersonID;
import static Integration.tableController.teamViewTeamID;
import static Integration.RegisterController.personId;

public class PlayerRegisterController {

    @FXML
    TextField positionsText,injuryDetailsText,suspensionsText,scoutIDText,teamIDText;

    @FXML
    RadioButton radioBtn1,radioBtn2;

    public void initialize(){

        teamIDText.setText(String.valueOf(teamViewTeamID));
        scoutIDText.setText(String.valueOf(scoutPersonID));
    }

    public void PlayerTeamView(ActionEvent event){
        Parent tableViewParent = null;
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/teamView.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
            window.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void PlayerScoutView(ActionEvent event){
        Parent tableViewParent = null;
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/scoutView.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
            window.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void PlayerRegister(ActionEvent event){
        if(!radioBtn1.isSelected() && !radioBtn2.isSelected())
        {
            AlertBox("Registration Error","PLease Select Availability");
        }
        else if(teamIDText.getText().equals("0"))
        {
            AlertBox("Registration Error","Team ID cant be null");
        }
        else if(positionsText.getText().isEmpty()){
            AlertBox("Registration Error","Fields Cant be Empty");
        }
        else if(!positionsText.getText().isEmpty() && !injuryDetailsText.getText().isEmpty() && !suspensionsText.getText().isEmpty()){
            Connection conn = null;
            String url = "";
            try {
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                PreparedStatement pStat2 = conn.prepareStatement("insert into player values (?,?,?,?,?,?,?)");
                pStat2.setInt(1,personId);
                pStat2.setString(2,positionsText.getText());
                pStat2.setString(4,injuryDetailsText.getText());
                pStat2.setString(5,suspensionsText.getText());
                pStat2.setInt(3, teamViewTeamID);
                pStat2.setInt(6,scoutPersonID);

                if(radioBtn1.isSelected()){
                    pStat2.setString(7,"Y");
                }
                else if (radioBtn2.isSelected()){
                    pStat2.setString(7,"N");
                }
                pStat2.execute();
                AlertBox("Registration Successful","Registration Done Successfully");

                if(count==7)
                    url = "../GUI/Status.fxml";
                else if(count==11 || count==15 || count==16)
                    url = "../GUI/PlayerManagement.fxml";

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
        else
            AlertBox("Registration Error","Fields cant be null");
    }

    public void backRegister (ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/Register.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        window.centerOnScreen();
    }



}
