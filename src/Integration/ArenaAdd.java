package Integration;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import static Integration.Main.AlertBox;
import static Integration.TeamManagementController.teamCount;
import static Integration.TeamManagementController.adminCount;
import static Integration.TeamManagementController.tournamentCount;
import static Integration.TeamManagementController.teamName;
import static Integration.StatusController.ManagerCount;
import static Integration.StatusController.PresidentCount;


import static Integration.RegisterController.personId;

public class ArenaAdd {

    @FXML
    TextField ArenaID,ArenaName,MaintenanceTxt,HoursToTxt,HoursFromTxt;

    public int arenaID = 0;

    public void initialize() {
        arenaCount();
        ArenaID.setText(String.valueOf(arenaID));
        ArenaID.setEditable(false);
    }

    public void arenaCount(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            PreparedStatement P2=conn.prepareStatement("select max(arenaid) from arena" );
            ResultSet resultSet2=P2.executeQuery();
            int a=0;
            while (resultSet2.next()) {
                a=resultSet2.getInt(1);
            }
            arenaID=a+1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void AddArena (javafx.event.ActionEvent event) {
        if(!ArenaName.getText().isEmpty() && !MaintenanceTxt.getText().isEmpty() && !HoursFromTxt.getText().isEmpty() && !HoursToTxt.getText().isEmpty()){
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                PreparedStatement pStat2 = conn.prepareStatement("insert into Arena values (?,?,?,?,?)");
                pStat2.setInt(1,arenaID);
                pStat2.setString(2,ArenaName.getText());
                pStat2.setString (3,MaintenanceTxt.getText());
                pStat2.setString (4,HoursFromTxt.getText());
                pStat2.setString (5,HoursToTxt.getText());
                pStat2.execute();
                AlertBox("Arena Add","Arena Added Successfully");
                PreparedStatement pStat = conn.prepareStatement("insert into Team values (?,?,?,?,?,?,?)");
                pStat.setInt(1,teamCount);
                pStat.setString(2,teamName);
                pStat.setInt (3,arenaID);
                pStat.setInt (4,adminCount);
                pStat.setInt (5,PresidentCount);
                pStat.setInt (6,ManagerCount);
                pStat.setInt (7,tournamentCount);
                pStat.execute();
                AlertBox("Team Add","Team Added Successfully");
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/TeamManagement.fxml"));
                Scene tableViewScene = new Scene(tableViewParent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.centerOnScreen();
                window.setScene(tableViewScene);
                window.show();
                window.centerOnScreen();

            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }

        }

    }





}
