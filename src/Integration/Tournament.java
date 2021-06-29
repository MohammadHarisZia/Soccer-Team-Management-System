package Integration;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import static Integration.Main.AlertBox;
import static Integration.LeagueManagementController.tournamentID;

public class Tournament {

    @FXML
    TextField TournamentID,TournamentName;

    public int tID = 0;

    public void initialize(){
        TournamentIDCount();
        TournamentID.setText(String.valueOf(tID));
        TournamentID.setEditable(false);
    }
    public void TournamentIDCount(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            PreparedStatement P2=conn.prepareStatement("select max(tournamentid) from tournament" );
            ResultSet resultSet2=P2.executeQuery();
            while (resultSet2.next()) {
                tID=resultSet2.getInt(1)+1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void add (ActionEvent event) throws IOException {
        if(!TournamentName.getText().isEmpty()){
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                PreparedStatement insertEmp = conn.prepareStatement("insert into Tournament values (?,?)");
                insertEmp.setInt(1, tID);
                insertEmp.setString(2, TournamentName.getText());
                insertEmp.execute();
                AlertBox("Added Successfully","Added Successfully");
                tournamentID = tID;
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/LeagueManagement.fxml"));
                Scene tableViewScene = new Scene(tableViewParent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(tableViewScene);
                window.show();
                window.centerOnScreen();

            } catch (SQLException throwables) {
                throwables.printStackTrace();

            }
        }
        else
            AlertBox("Added Failed","Fields Are Empty");

    }
    }
