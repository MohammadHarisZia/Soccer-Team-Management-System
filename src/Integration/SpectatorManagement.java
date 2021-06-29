package Integration;

import BackEndOOP.Player;
import BackEndOOP.Spectator;
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

public class SpectatorManagement {




    @FXML
    TextField YearsTxt;

    @FXML
    ComboBox CountryID;

    @FXML
    TableView<Spectator> iSpectatorTable;

    @FXML
    TableColumn<Spectator, Integer> iPersonID;
    @FXML
    TableColumn<Spectator, Integer> iYears;
    @FXML
    TableColumn<Spectator, Integer> iCountryID;
    @FXML
    TableColumn<Spectator, Integer> iReservationID;

    ObservableList<Integer> CountryList = FXCollections.observableArrayList();

    private ObservableList<Spectator> SpectatorData;

    public void initialize(){
        CountryCount();
        CountryID.setItems(CountryList);
    }

    public void CountryCount(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            PreparedStatement P2=conn.prepareStatement("select Countryid from country" );
            ResultSet resultSet2=P2.executeQuery();
            int a=0;
            while (resultSet2.next()) {
                a=resultSet2.getInt(1);
                System.out.println(a);
                CountryList.add(a);
            }
            System.out.println(CountryList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete (ActionEvent event) throws IOException {
        int deleteID = 0;
        if(iSpectatorTable.getSelectionModel().getSelectedItem()!=null) {
            deleteID = iSpectatorTable.getSelectionModel().getSelectedItem().getPersonID();
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                PreparedStatement del = conn.prepareStatement("delete from spectator where personid = ?");
                del.setInt(1, deleteID);
                del.execute();
                AlertBox("Delete Successful","Row Deleted Successfully");
                loadData(event);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else{
            AlertBox("Delete Error","PLease select a row to delete");

        }
    }
    public void add (ActionEvent event) throws IOException {
        count = 12;
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/Register.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.centerOnScreen();
        window.setScene(tableViewScene);
        window.show();
        window.centerOnScreen();
    }

    public void loadData (ActionEvent event){
        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            this.SpectatorData = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("select * from Spectator");
            while (rs.next()) {
                this.SpectatorData.add(new Spectator(rs.getInt(1), rs.getInt(2),rs.getInt(3),rs.getInt(4)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.iPersonID.setCellValueFactory(new PropertyValueFactory<Spectator, Integer>("PersonID"));
        this.iYears.setCellValueFactory(new PropertyValueFactory<Spectator, Integer>("Years"));
        this.iCountryID.setCellValueFactory(new PropertyValueFactory<Spectator, Integer>("CountryID"));
        this.iReservationID.setCellValueFactory(new PropertyValueFactory<Spectator, Integer>("ReservationID"));
        this.iSpectatorTable.setItems(this.SpectatorData);
    }

    public void update (ActionEvent event) throws IOException {
        int updateID = 0;
        if(iSpectatorTable.getSelectionModel().getSelectedItem()!=null) {
            if(!YearsTxt.getText().isEmpty() && CountryID.getValue()!=null){
                updateID = iSpectatorTable.getSelectionModel().getSelectedItem().getPersonID();
                Connection conn = null;
                try {
                    conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                    PreparedStatement del = conn.prepareStatement("update spectator set personid = ? ,years = ? ,country_countryid = ? where personid = ?");
                    del.setInt(1, updateID);
                    del.setString(2, YearsTxt.getText());
                    del.setInt(3, (Integer) CountryID.getValue());
                    del.setInt(4, updateID);
                    del.execute();
                    AlertBox("Update Successful","Row Updated Successfully");
                    loadData(event);
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

    public void back (ActionEvent event) {
        try{
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/dashboardAdmin.fxml"));
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
