package Integration;

import BackEndOOP.League;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static Integration.Main.AlertBox;
import static Integration.Main.count;

public class LeagueManagementController {

    public static int tournamentID,matchID = 0;

    @FXML
    TextField FixtureTxt,NoOfSeats,MatchID;

    @FXML
    ComboBox TournamentID;

    @FXML
    RadioButton radioBtn1,radioBtn2;

    @FXML
    DatePicker PlayDate;

    @FXML
    TableView<League> iLeagueTable;
    @FXML
    TableColumn<League, Integer> iMatchID;
    @FXML
    TableColumn<League, String> iFixture;
    @FXML
    TableColumn<League, String> iPlayed;
    @FXML
    TableColumn<League, Integer> iSeats;
    @FXML
    TableColumn<League, String> iPlayDate;
    @FXML
    TableColumn<League, Integer> iTournamentID;
    @FXML
    TableColumn<League, String> iTournamentName;

    private ObservableList<League> LeagueData;

    ObservableList<Integer> TournamentList = FXCollections.observableArrayList();


    public void initialize(){
        TournamentCount();MatchIDCount();
        if(tournamentID == 0)
            TournamentID.setItems(TournamentList);
        else {
            TournamentID.setValue(tournamentID);
            TournamentID.setEditable(false);
        }
        MatchID.setText(String.valueOf(matchID));
        MatchID.setEditable(false);
    }

    public void TournamentCount(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            PreparedStatement P2=conn.prepareStatement("select tournamentid from tournament" );
            ResultSet resultSet2=P2.executeQuery();
            int a=0;
            while (resultSet2.next()) {
               TournamentList.add(resultSet2.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void MatchIDCount(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            PreparedStatement P2=conn.prepareStatement("select max(matchID) from Match");
            ResultSet resultSet2=P2.executeQuery();
            while (resultSet2.next()) {
                matchID=resultSet2.getInt(1)+1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int ValidTeam(String str){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            PreparedStatement P2=conn.prepareStatement("select teamid from team where teamname like ?");
            P2.setString(1,"%"+str+"%");
            ResultSet resultSet2=P2.executeQuery();
            if(!resultSet2.isBeforeFirst())
                return -1;
            while (resultSet2.next()) {
                return resultSet2.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 1;
    }

    public void addTournament(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/Tournament.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        window.centerOnScreen();
    }

    public void update (ActionEvent event) throws IOException {
        int updateID = 0;
        if(iLeagueTable.getSelectionModel().getSelectedItem()!=null) {
            if(!FixtureTxt.getText().isEmpty() && !NoOfSeats.getText().isEmpty() && TournamentID.getValue()!=null && (radioBtn2.isSelected() || radioBtn1.isSelected())) {
                MatchID.setEditable(false);
                TournamentID.setValue(iLeagueTable.getSelectionModel().getSelectedItem().getTournamentID());
                TournamentID.setEditable(false);
                MatchID.setText(String.valueOf(iLeagueTable.getSelectionModel().getSelectedItem().getMatchID()));
                updateID = iLeagueTable.getSelectionModel().getSelectedItem().getMatchID();
                Connection conn = null;
                try {
                    LocalDate aDate = PlayDate.getValue();
                    String formattedDate = aDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
                    conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                    PreparedStatement del = conn.prepareStatement("update match set fixture = ? ,tobeplayed = ? ,noofseats = ?  , playdate = ? ,tournament_tournamentid = ?  where matchid = ?");
                    del.setString(1, FixtureTxt.getText());
                    if(radioBtn1.isSelected())
                        del.setString(2,"Y");
                    else
                        del.setString(2,"N");
                    del.setString(3, NoOfSeats.getText());
                    del.setString(4, (formattedDate));
                    del.setInt(5, (Integer) TournamentID.getValue());
                    del.setInt(6, updateID);
                    del.execute();
                    AlertBox("Update Successful","Row Updated Successfully");
                    loadData(event);
                    TournamentCount();MatchIDCount();
                    MatchID.setText(String.valueOf(matchID));
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

    public void delete (ActionEvent event) throws IOException {
        int deleteID = 0;
        if(iLeagueTable.getSelectionModel().getSelectedItem()!=null) {
            deleteID = iLeagueTable.getSelectionModel().getSelectedItem().getMatchID();
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                PreparedStatement del = conn.prepareStatement("delete from match where matchid = ?");
                del.setInt(1, deleteID);
                del.execute();
                AlertBox("Delete Successful","Row Deleted Successfully");
                loadData(event);
                TournamentCount();MatchIDCount();
                MatchID.setText(String.valueOf(matchID));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else{
            AlertBox("Delete Error","PLease select a row to delete");

        }
    }

    public void loadData (ActionEvent event){
        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            this.LeagueData = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("select match.matchid,match.fixture,match.tobeplayed,match.noofseats,match.playdate,match.tournament_tournamentid,tournament.tournament_name from match,tournament where tournament.tournamentid = match.tournament_tournamentid");

            while (rs.next()) {
                this.LeagueData.add(new League(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getString(7)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.iMatchID.setCellValueFactory(new PropertyValueFactory<League, Integer>("MatchID"));
        this.iFixture.setCellValueFactory(new PropertyValueFactory<League, String>("Fixture"));
        this.iPlayed.setCellValueFactory(new PropertyValueFactory<League, String>("ToBePlayed"));
        this.iSeats.setCellValueFactory(new PropertyValueFactory<League, Integer>("NoOfSeats"));
        this.iPlayDate.setCellValueFactory(new PropertyValueFactory<League, String>("date"));
        this.iTournamentID.setCellValueFactory(new PropertyValueFactory<League, Integer>("TournamentID"));
        this.iTournamentName.setCellValueFactory(new PropertyValueFactory<League, String>("TournamentName"));
        this.iLeagueTable.setItems(this.LeagueData);
    }


    public void add (ActionEvent event) throws IOException {
        if(!FixtureTxt.getText().isEmpty() && !NoOfSeats.getText().isEmpty() && TournamentID.getValue()!=null && (radioBtn2.isSelected() || radioBtn1.isSelected())) {
            try {
                String fixture = FixtureTxt.getText();
                String team1= "";
                String team2 = "";
                if(!fixture.contains("vs"))
                    AlertBox("Enter 2 Teams","Please Enter 2 teams");

                else if(ValidTeam(team1)==-1 || ValidTeam(team2)==-1){
                    AlertBox("Invalid Team Names","Make sure to have one space between vs and enter Valid Team Names");
                }
                else
                {
                    team1= fixture.substring(0,fixture.indexOf("vs")-1);
                    team2 = fixture.substring(fixture.indexOf("vs")+3);
                    System.out.println(team2);
                    System.out.println(team1);
                    System.out.println(ValidTeam(team1) + " " +  ValidTeam(team2));
                    LocalDate aDate = PlayDate.getValue();
                    String formattedDate = aDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                    PreparedStatement insertEmp = conn.prepareStatement("insert into Match values (?,?,?,?,?,?)");
                    insertEmp.setInt(1, Integer.parseInt(MatchID.getText()));
                    insertEmp.setString(2, FixtureTxt.getText());
                    if(radioBtn1.isSelected())
                    insertEmp.setString(3,"Y");
                    else
                        insertEmp.setString(3,"N");
                    insertEmp.setInt(4, Integer.parseInt(NoOfSeats.getText()));
                    insertEmp.setString(5, formattedDate);
                    insertEmp.setInt(6, (Integer) TournamentID.getValue());
                    insertEmp.execute();
                    PreparedStatement insertEmp2 = conn.prepareStatement("insert into Plays values (?,?)");
                    insertEmp2.setInt(1, ValidTeam(team1));
                    insertEmp2.setInt(2, Integer.parseInt(MatchID.getText()));
                    insertEmp2.execute();
                    PreparedStatement insertEmp3 = conn.prepareStatement("insert into Plays values (?,?)");
                    insertEmp3.setInt(1, ValidTeam(team2));
                    insertEmp3.setInt(2, Integer.parseInt(MatchID.getText()));
                    insertEmp3.execute();
                    AlertBox("Added Successfully","Added Successfully");
                    loadData(event);
                    TournamentCount();MatchIDCount();
                    MatchID.setText(String.valueOf(matchID));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else{
            AlertBox("Add Error","Fields are empty");
        }
    }
    public void back (ActionEvent event) {
        try{
            String url = "";
            if(count == 1)
                url = "../GUI/dashboardAdmin.fxml";
            else if(count==5 || count==15)
                url = "../GUI/dashboardPresident.fxml";

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
