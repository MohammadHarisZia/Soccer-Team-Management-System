package Integration;

import BackEndOOP.League;
import BackEndOOP.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import static Integration.Main.AlertBox;
import static Integration.Main.count;

public class TeamManagementController {

    @FXML
    TableView<Team> iTeamTable;
    @FXML
    TableColumn<Team, Integer> iID;
    @FXML
    TableColumn<Team, String> iName;
    @FXML
    TableColumn<Team, Integer> iArenaID;
    @FXML
    TableColumn<Team, Integer> iAdminID;
    @FXML
    TableColumn<Team, Integer> iPresidentID;
    @FXML
    TableColumn<Team, Integer> iManagerID;
    @FXML
    TableColumn<Team, Integer> iTournamentID;

    private ObservableList<Team> TeamData;

    @FXML
    TextField TeamID,TeamNameText;

    @FXML
    ComboBox AdminID,TournamentID;

    public static int teamCount;
    public static String teamName;
    public static int tournamentCount;
    public static int adminCount;
    public static String curr = "";


    ObservableList<Integer> adminList = FXCollections.observableArrayList();
    ObservableList<Integer> tournamentList= FXCollections.observableArrayList();


    public void initialize() {
        teamCount();tournamentCount();adminCount();
        TournamentID.setItems(tournamentList);
        AdminID.setItems(adminList);
        TeamID.setText(String.valueOf(teamCount));
        TeamID.setEditable(false);
    }

    public void adminCount(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            PreparedStatement P2=conn.prepareStatement("select personid from admin" );
            ResultSet resultSet2=P2.executeQuery();
            int a=0;
            while (resultSet2.next()) {
                a=resultSet2.getInt(1);
                adminList.add(a);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void tournamentCount(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            PreparedStatement P2=conn.prepareStatement("select tournamentid from tournament" );
            ResultSet resultSet2=P2.executeQuery();
            int a=0;
            while (resultSet2.next()) {
                a=resultSet2.getInt(1);
                tournamentList.add(a);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void teamCount(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            PreparedStatement P2=conn.prepareStatement("select max(teamid) from team" );
            ResultSet resultSet2=P2.executeQuery();
            int a=0;
            while (resultSet2.next()) {
                a=resultSet2.getInt(1);
            }
            teamCount=a+1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void loadData (ActionEvent event){
        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            this.TeamData = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("select* from team ");

            while (rs.next()) {
                this.TeamData.add(new Team(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.iID.setCellValueFactory(new PropertyValueFactory<Team, Integer>("TeamID"));
        this.iName.setCellValueFactory(new PropertyValueFactory<Team, String>("TeamName"));
        this.iArenaID.setCellValueFactory(new PropertyValueFactory<Team, Integer>("ArenaID"));
        this.iAdminID.setCellValueFactory(new PropertyValueFactory<Team, Integer>("Admin_PersonID"));
        this.iPresidentID.setCellValueFactory(new PropertyValueFactory<Team, Integer>("President_PersonID"));
        this.iManagerID.setCellValueFactory(new PropertyValueFactory<Team, Integer>("Manager_PersonID"));
        this.iTournamentID.setCellValueFactory(new PropertyValueFactory<Team, Integer>("TournamentID"));
        this.iTeamTable.setItems(this.TeamData);
    }


    public void AddTeam (javafx.event.ActionEvent event) {
        if(count==1)
            curr="../GUI/dashboardAdmin.fxml";
        if(count==2)
            curr="../GUI/dashboardManager.fxml";
        if(count==5)
            curr="../GUI/dashboardPresident.fxml";
        count=13;

        if(!TeamID.getText().isEmpty() && TournamentID.getValue()!=null && AdminID.getValue()!=null && !TeamNameText.getText().isEmpty()) {
            try {
                teamCount = Integer.parseInt(TeamID.getText());
                adminCount = (int) AdminID.getValue();
                tournamentCount = (int) TournamentID.getValue();
                teamName = TeamNameText.getText();
                AlertBox("Please Add Manager","Please Add Manager");
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/Register.fxml"));
                Scene tableViewScene = new Scene(tableViewParent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(tableViewScene);
                window.show();
                window.centerOnScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            {
                AlertBox("Error","Fields Are Empty");

            }
    }

    public void delete (ActionEvent event) throws IOException {
        int deleteID = 0;
        if(iTeamTable.getSelectionModel().getSelectedItem()!=null) {
            deleteID = iTeamTable.getSelectionModel().getSelectedItem().getTeamID();
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                PreparedStatement del = conn.prepareStatement("delete from team where teamid = ?");
                del.setInt(1, deleteID);
                del.execute();
                AlertBox("Delete Successful","Row Deleted Successfully");
                loadData(event);
                teamCount();
                TeamID.setText(String.valueOf(teamCount));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else{
            AlertBox("Delete Error","PLease select a row to delete");

        }
    }

    public void addTournament(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/Tournament.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        window.centerOnScreen();
    }



    public void back (ActionEvent event) {
        try{
            String url = "";
            if(count == 1)
                url = "../GUI/dashboardAdmin.fxml";
            else if(count==2 )
                url = "../GUI/dashboardManager.fxml";
            else if(count==5)
                url = "../GUI/dashboardPresident.fxml";
            else
                url = curr;

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
