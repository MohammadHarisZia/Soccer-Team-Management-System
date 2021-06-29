package Integration;

import BackEndOOP.AmbassadorDashboard;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import static Integration.Main.AlertBox;
import static Integration.tableController.sponsorID;
import static Integration.tableController.teamViewTeamID;

public class AmbassadorConsole {
    @FXML
    TableView<AmbassadorDashboard> AmbassadorTableID;
    @FXML
    TableColumn<AmbassadorDashboard, Integer> iTeamID;
    @FXML
    TableColumn<AmbassadorDashboard, String> iTeamName;
    @FXML
    TableColumn<AmbassadorDashboard, String> iSponsorName;
    @FXML
    TableColumn<AmbassadorDashboard, Integer> iSponsorID;

    @FXML
    TextField TeamIDText,SponsorIDText,SponsorNameTxt;

    private ObservableList<AmbassadorDashboard> AmbassadorData;

    public static int sponsorCount = 0;

    public void initialize(){
        TeamIDText.setText(String.valueOf(teamViewTeamID));
        SponsorIDText.setText(String.valueOf(sponsorID));
        SponsorCount();
    }

    public void SponsorCount(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            PreparedStatement P2=conn.prepareStatement("select max(sponserid) from sponsor" );
            ResultSet resultSet2=P2.executeQuery();
            int a=0;
            while (resultSet2.next()) {
                a=resultSet2.getInt(1);
            }
            sponsorCount=a+1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addSponsor (ActionEvent event) throws IOException {
        if(!SponsorNameTxt.getText().isEmpty()) {
            try {
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                PreparedStatement insertEmp = conn.prepareStatement("insert into Sponsor values (?,?)");
                insertEmp.setInt(1,sponsorCount);
                insertEmp.setString(2, SponsorNameTxt.getText());
                insertEmp.execute();
                AlertBox("Added Successfully","Added Successfully");
                SponsorCount();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else{
            AlertBox("Add Error","Fields are empty");
        }
    }

    public void loadSponsor(ActionEvent event){
        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            this.AmbassadorData = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("select team.teamid,team.teamname,sponsors.sponsor_sponserid,sponsor.sponsername from sponsor,sponsors,team where sponsors.team_teamid = team.teamid and sponsors.sponsor_sponserid = sponsor.sponserid");
            while (rs.next()) {
                this.AmbassadorData.add(new AmbassadorDashboard(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.iTeamID.setCellValueFactory(new PropertyValueFactory<AmbassadorDashboard, Integer>("teamID"));
        this.iTeamName.setCellValueFactory(new PropertyValueFactory<AmbassadorDashboard, String>("teamName"));
        this.iSponsorID.setCellValueFactory(new PropertyValueFactory<AmbassadorDashboard, Integer>("sponsorID"));
        this.iSponsorName.setCellValueFactory(new PropertyValueFactory<AmbassadorDashboard,String>("sponsorName"));
        this.AmbassadorTableID.setItems(this.AmbassadorData);
    }

    public Boolean validAddition(int num1, int num2){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            PreparedStatement P2=conn.prepareStatement("select * from sponsors" );
            ResultSet resultSet2=P2.executeQuery();
            while (resultSet2.next()) {
                if(num1 == resultSet2.getInt(1) && num2 ==  resultSet2.getInt(2))
                    return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public void add (ActionEvent event) throws IOException {
        int num1 = Integer.parseInt(TeamIDText.getText());
        int num2 = Integer.parseInt(SponsorIDText.getText());
        if(!validAddition(num1,num2))
            AlertBox("Addition Unsuccessful","Key Violation");
        else if(!TeamIDText.getText().equals("0") && !SponsorIDText.getText().equals("0")) {
            try {
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                PreparedStatement insertEmp = conn.prepareStatement("insert into Sponsors values (?,?)");
                insertEmp.setInt(1, Integer.parseInt(TeamIDText.getText()));
                insertEmp.setString(2, SponsorIDText.getText());
                insertEmp.execute();
                AlertBox("Added Successfully","Added Successfully");
                loadSponsor(event);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else{
            AlertBox("Add Error","Fields are empty");
        }
    }

    public void delete (ActionEvent event) throws IOException {
        int deleteID = 0;
        int deleteID2 = 0;
        if(AmbassadorTableID.getSelectionModel().getSelectedItem()!=null) {
            deleteID = AmbassadorTableID.getSelectionModel().getSelectedItem().getTeamID();
            deleteID2 = AmbassadorTableID.getSelectionModel().getSelectedItem().getSponsorID();
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                PreparedStatement del = conn.prepareStatement("delete from sponsors where sponsors.team_teamid = ? and sponsors.sponsor_sponserid = ?");
                del.setInt(1, deleteID);
                del.setInt(2, deleteID2);
                del.execute();
                AlertBox("Delete Successful","Row Deleted Successfully");
                loadSponsor(event);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else{
            AlertBox("Delete Error","PLease select a row to delete");

        }
    }

    public void OpenTeam(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/TeamView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        window.centerOnScreen();
    }

    public void OpenSponsor(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/SponsorView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        window.centerOnScreen();
    }

    public void backStatus(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/Status.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        window.centerOnScreen();
    }

}
