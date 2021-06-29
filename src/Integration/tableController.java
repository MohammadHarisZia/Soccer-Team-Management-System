package Integration;


import BackEndOOP.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.sql.*;

import static Integration.Main.AlertBox;
import static Integration.Main.count;


public class tableController {

    @FXML
    TableView<Person> personTableID;
    @FXML
    TableColumn<Person, Integer> iID;
    @FXML
    TableColumn<Person, String> iName;
    @FXML
    TableColumn<Person, String> iContactNo;
    @FXML
    TableColumn<Person,String> iEmail;
    @FXML
    TableColumn<Person, String> iAddress;
    @FXML
    TableColumn<Person, String> iGender;
    @FXML
    TableColumn<Person, String> iPassword;

    @FXML
    TableView<teamView> teamTableID;
    @FXML
    TableColumn<teamView, Integer> iteamID;
    @FXML
    TableColumn<teamView, String> iTeamName;
    @FXML
    TableColumn<teamView, String> iArenaName;

    @FXML
    TableView<Country> countryTableID;
    @FXML
    TableColumn<Country, Integer> iCountryID;
    @FXML
    TableColumn<Country, String> iCountryName;
    @FXML
    TableColumn<Country, String> iRegionName;


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

    @FXML
    TableView<scoutView> scoutTableID;
    @FXML
    TableColumn<scoutView, Integer> iPersonID;
    @FXML
    TableColumn<scoutView, String> iScoutName;
    @FXML
    TableColumn<scoutView, String> iSkillsName;

    @FXML
    TableView<Sponsor> SponsorTableID;
    @FXML
    TableColumn<Sponsor, Integer> iSponsorID;
    @FXML
    TableColumn<Sponsor, String> iSponsorName;


    private ObservableList<Person> data;
    private ObservableList<teamView> teamData;
    private ObservableList<Country> countryData;
    private ObservableList<Match> matchData;
    private ObservableList<scoutView> scoutData;
    private ObservableList<Sponsor> SponsorData;


    public static int teamViewTeamID = 0;
    public static int countryID = 0;
    public static int matchID = 0;
    public static int scoutPersonID = 0;
    public static int sponsorID = 0;



    public void loadPerson(ActionEvent event){
        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            this.data = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("select * from person");
            while (rs.next()) {
                    this.data.add(new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.iID.setCellValueFactory(new PropertyValueFactory<Person, Integer>("PersonID"));
        this.iName.setCellValueFactory(new PropertyValueFactory<Person, String>("Name"));
        this.iContactNo.setCellValueFactory(new PropertyValueFactory<Person, String>("ContactNO"));
        this.iEmail.setCellValueFactory(new PropertyValueFactory<Person, String>("Email"));
        this.iAddress.setCellValueFactory(new PropertyValueFactory<Person, String>("Address"));
        this.iGender.setCellValueFactory(new PropertyValueFactory<Person, String>("Gender"));
        this.iPassword.setCellValueFactory(new PropertyValueFactory<Person, String>("Password"));
        this.personTableID.setItems(this.data);
    }

    public void getPerson(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("personTable.fxml"));
        tableController controller = loader.getController();
        if(personTableID.getSelectionModel().getSelectedItem()==null)
            AlertBox("Error","Kindly Select a row");

        else
            AlertBox("Alert", personTableID.getSelectionModel().getSelectedItem().getName());
    }

    public void loadTeam(ActionEvent event){
        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            this.teamData = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("select * from teamView");
            while (rs.next()) {
                this.teamData.add(new teamView(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.iteamID.setCellValueFactory(new PropertyValueFactory<teamView, Integer>("teamID"));
        this.iTeamName.setCellValueFactory(new PropertyValueFactory<teamView, String>("teamName"));
        this.iArenaName.setCellValueFactory(new PropertyValueFactory<teamView, String>("arenaName"));
        this.teamTableID.setItems(this.teamData);
    }
    public void loadSponsor(ActionEvent event){
        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            this.SponsorData = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("select * from SponsorView");
            while (rs.next()) {
                this.SponsorData.add(new Sponsor(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.iSponsorID.setCellValueFactory(new PropertyValueFactory<Sponsor, Integer>("SponsorID"));
        this.iSponsorName.setCellValueFactory(new PropertyValueFactory<Sponsor, String>("SponsorName"));
        this.SponsorTableID.setItems(this.SponsorData);
    }

    public void loadCountry(ActionEvent event){
        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            this.countryData = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("select countryID,country_Name,Region from Country");
            while (rs.next()) {
                this.countryData.add(new Country(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.iCountryID.setCellValueFactory(new PropertyValueFactory<Country, Integer>("CountryID"));
        this.iCountryName.setCellValueFactory(new PropertyValueFactory<Country, String>("Country_Name"));
        this.iRegionName.setCellValueFactory(new PropertyValueFactory<Country, String>("Region"));
        this.countryTableID.setItems(this.countryData);
    }

    public void loadMatch(ActionEvent event){
        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            this.matchData = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("select matchid,fixture,tobeplayed,noofseats,playdate from match");
            while (rs.next()) {
                this.matchData.add(new Match(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getInt(4),rs.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.iMatchID.setCellValueFactory(new PropertyValueFactory<Match, Integer>("MatchID"));
        this.iFixtureName.setCellValueFactory(new PropertyValueFactory<Match, String>("Fixture"));
        this.iToBePlayed.setCellValueFactory(new PropertyValueFactory<Match, String>("ToBePlayed"));
        this.iNoOfSeats.setCellValueFactory(new PropertyValueFactory<Match, String>("NoOfSeats"));
        this.iDate.setCellValueFactory(new PropertyValueFactory<Match, String>("date"));
        this.matchTableID.setItems(this.matchData);
    }

    public void loadScout(ActionEvent event){
        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            this.scoutData = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("select * from scoutView");
            while (rs.next()) {
                this.scoutData.add(new scoutView(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.iPersonID.setCellValueFactory(new PropertyValueFactory<scoutView, Integer>("personID"));
        this.iScoutName.setCellValueFactory(new PropertyValueFactory<scoutView, String>("ScoutName"));
        this.iSkillsName.setCellValueFactory(new PropertyValueFactory<scoutView, String>("SkillsName"));
        this.scoutTableID.setItems(this.scoutData);
    }

    public void getScoutView(ActionEvent event){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("scoutView.fxml"));
        tableController controller = loader.getController();
        if(scoutTableID.getSelectionModel().getSelectedItem()!=null)
            scoutPersonID = scoutTableID.getSelectionModel().getSelectedItem().getPersonID();
        else
            scoutPersonID = 0;

        Parent tableViewParent = null;
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/PlayerRegister.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
            window.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getSponsorView(ActionEvent event){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SponsorView.fxml"));
        tableController controller = loader.getController();
        if(SponsorTableID.getSelectionModel().getSelectedItem()!=null)
            sponsorID = SponsorTableID.getSelectionModel().getSelectedItem().getSponsorID();
        else
            sponsorID = 0;

        Parent tableViewParent = null;
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/AmbassadorDashboard.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
            window.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void getTeamView(ActionEvent event){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("teamView.fxml"));
        tableController controller = loader.getController();
        if(teamTableID.getSelectionModel().getSelectedItem()!=null)
            teamViewTeamID = teamTableID.getSelectionModel().getSelectedItem().getTeamID();
        else
            teamViewTeamID = 0;

        Parent tableViewParent = null;
        try {
            String url1= "";
            if (count==3){
                url1 = "../GUI/MedicalStaffRegister.fxml";
            }
            else if (count==4){
                url1 = "../GUI/TrainerRegister.fxml";
            }
            else if(count==7)
                url1 = "../GUI/PlayerRegister.fxml";
            else if(count==11)
                url1 = "../GUI/PlayerRegister.fxml";
            else if(count==9)
                url1 = "../GUI/AmbassadorDashboard.fxml";
            else if(count==15 || count ==16)
                url1 = "../GUI/PlayerRegister.fxml";

            tableViewParent = FXMLLoader.load(getClass().getResource(url1));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
            window.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getCountryView(ActionEvent event){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("teamView.fxml"));
        tableController controller = loader.getController();
        if(countryTableID.getSelectionModel().getSelectedItem()!=null)
            countryID = countryTableID.getSelectionModel().getSelectedItem().getCountryID();
        else
            countryID = 0;

        Parent tableViewParent = null;
        String url = "";
        if (count==9)
            url = "../GUI/AmbassadorRegister.fxml";
        else if(count == 10 || count ==12)
            url = "../GUI/SpectatorRegister.fxml";

        try {
            tableViewParent = FXMLLoader.load(getClass().getResource(url));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
            window.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void getMatchView(ActionEvent event){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("matchView.fxml"));
        tableController controller = loader.getController();
        if(matchTableID.getSelectionModel().getSelectedItem()!=null)
            matchID = matchTableID.getSelectionModel().getSelectedItem().getMatchID();
        else
            matchID = 0;

        Parent tableViewParent = null;
        try {
            String URL = "";
            if(count==6)
                URL = "../GUI/RefereeRegister.fxml";
            else if(count==12)
                URL = "../GUI/SpectatorDashboard.fxml";
            else if(count == 10)
                URL = "../GUI/SpectatorRegister.fxml";

            tableViewParent = FXMLLoader.load(getClass().getResource(URL));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
            window.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
