package Integration;

import BackEndOOP.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import static Integration.Main.AlertBox;
import static Integration.Main.count;
import static Integration.tableController.scoutPersonID;
import static Integration.tableController.teamViewTeamID;

public class PlayerManagement {
    @FXML
    TextField PositionTxt,InjuryTxt,SuspensionsTxt,PersonIDTxt;
    @FXML
    ComboBox ScoutID,TeamID;
    @FXML
    RadioButton radioBtn1,radioBtn2;
    @FXML
    TableView<Player> iPlayerTable;
    @FXML
    TableColumn<Player, Integer> iPersonID;
    @FXML
    TableColumn<Player, String> iPosition;
    @FXML
    TableColumn<Player,Integer> iTeamID;
    @FXML
    TableColumn<Player, String> iInjuryDetails;
    @FXML
    TableColumn<Player, String> iSuspensions;
    @FXML
    TableColumn<Player, Integer> iScoutID;
    @FXML
    TableColumn<Player, String> iAvailable;

    private ObservableList<Player> PlayerData;

    ObservableList<Integer> TeamList = FXCollections.observableArrayList();
    ObservableList<Integer> ScoutList = FXCollections.observableArrayList();

    public int PersonCount = 0;

    public void initialize(){
        TeamCount();ScoutCount();
        TeamID.setItems(TeamList);
        ScoutID.setItems(ScoutList);
    }

    public void add (ActionEvent event) throws IOException {
        if(count==1)
            count = 11;
        if(count==2)
            count = 16;
        else if(count==5)
            count = 15;
        Parent tableViewParent =FXMLLoader.load(getClass().getResource("../GUI/Register.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.centerOnScreen();
        window.setScene(tableViewScene);
        window.show();
        window.centerOnScreen();
    }


    public void TeamCount(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            PreparedStatement P2=conn.prepareStatement("select teamid from team" );
            ResultSet resultSet2=P2.executeQuery();
            int a=0;
            while (resultSet2.next()) {
                a=resultSet2.getInt(1);
                TeamList.add(a);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void ScoutCount(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            PreparedStatement P2=conn.prepareStatement("select personid from Scout" );
            ResultSet resultSet2=P2.executeQuery();
            int a=0;
            while (resultSet2.next()) {
                a=resultSet2.getInt(1);
                ScoutList.add(a);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete (ActionEvent event) throws IOException {
        int deleteID = 0;
        if(iPlayerTable.getSelectionModel().getSelectedItem()!=null) {
            deleteID = iPlayerTable.getSelectionModel().getSelectedItem().getPlayerID();
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                PreparedStatement del = conn.prepareStatement("delete from player where personid = ?");
                del.setInt(1, deleteID);
                del.execute();
                AlertBox("Delete Successful","Row Deleted Successfully");
                loadPlayer(event);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else{
            AlertBox("Delete Error","PLease select a row to delete");

        }
    }

    public void update (ActionEvent event) throws IOException {
        int updateID = 0;
        if(iPlayerTable.getSelectionModel().getSelectedItem()!=null) {
            if(!PositionTxt.getText().isEmpty() && TeamID.getValue()!=null && (radioBtn1.isSelected() || radioBtn2.isSelected())){
                updateID = iPlayerTable.getSelectionModel().getSelectedItem().getPlayerID();
                Connection conn = null;
                try {
                    conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                    PreparedStatement del = conn.prepareStatement("update player set personid = ? ,position = ? ,team_teamid = ?  , injurydetails= ? ,suspensions = ?,scout_personid = ?,available = ?  where personid = ?");
                    del.setInt(1, updateID);
                    del.setString(2, PositionTxt.getText());
                    del.setInt(3, (Integer) TeamID.getValue());
                    del.setString(4, InjuryTxt.getText());
                    del.setString(5, SuspensionsTxt.getText());
                    del.setInt(6, (Integer) ScoutID.getValue());
                    if(radioBtn1.isSelected())
                        del.setString(7,"Y");
                    else
                        del.setString(7,"N");
                    del.setInt(8, updateID);
                    del.execute();
                    AlertBox("Update Successful","Row Updated Successfully");
                    loadPlayer(event);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            else
                AlertBox("Update Error","PLease enter fields");
        }
        else{
            AlertBox("Update Error","PLease select a row to delete");
        }
    }

    public void loadPlayer (ActionEvent event){
        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            this.PlayerData = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("select * from player");
            while (rs.next()) {
                this.PlayerData.add(new Player(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.iPersonID.setCellValueFactory(new PropertyValueFactory<Player, Integer>("PlayerID"));
        this.iPosition.setCellValueFactory(new PropertyValueFactory<Player, String>("position"));
        this.iTeamID.setCellValueFactory(new PropertyValueFactory<Player,Integer>("TeamID"));
        this.iInjuryDetails.setCellValueFactory(new PropertyValueFactory<Player, String>("InjuryDetails"));
        this.iSuspensions.setCellValueFactory(new PropertyValueFactory<Player, String>("Suspensions"));
        this.iScoutID.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Scout_PersonID"));
        this.iAvailable.setCellValueFactory(new PropertyValueFactory<Player, String>("Available"));
        this.iPlayerTable.setItems(this.PlayerData);
    }

    public void back (ActionEvent event) {
        try{
            String url = "";
            if(count == 1)
                url = "../GUI/dashboardAdmin.fxml";
            else if(count==2)
                url = "../GUI/dashboardManager.fxml";
            else if(count==5)
                url = "../GUI/dashboardPresident.fxml";
            else if(count==11)
                url = "../GUI/dashboardAdmin.fxml";
            else if(count==15)
                url = "../GUI/dashboardPresident.fxml";
            else if(count==16)
                url = "../GUI/dashboardManager.fxml";

            Parent tableViewParent = FXMLLoader.load(getClass().getResource(url));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
            window.centerOnScreen();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

