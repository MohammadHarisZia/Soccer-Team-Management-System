package Integration;

import BackEndOOP.ScoutManagement;
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
import java.util.regex.Pattern;

import static Integration.Main.AlertBox;
import static Integration.Main.count;

public class ScoutManagementController {

    final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=*_])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 6 characters
                    "$");


    @FXML
    ComboBox Gender;
    @FXML
    TextField PersonIDTxt,nameTxt,AddressTxt,SkillsText,ContactNoTxt,emailTxt,passwordTxt;
    @FXML
    TableView<ScoutManagement> iScoutTable;
    @FXML
    TableColumn<ScoutManagement, Integer> iPersonID;
    @FXML
    TableColumn<ScoutManagement, String> iContactNo;
    @FXML
    TableColumn<ScoutManagement, String> iEmail;
    @FXML
    TableColumn<ScoutManagement, String> iName;
    @FXML
    TableColumn<ScoutManagement, String> iAddress;
    @FXML
    TableColumn<ScoutManagement, String> iGender;
    @FXML
    TableColumn<ScoutManagement, String> iPassword;
    @FXML
    TableColumn<ScoutManagement, String> iSkills;


    private ObservableList<ScoutManagement> ScoutData;

    public static int newPerson = 0;

    public void PersonCount(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            PreparedStatement P2=conn.prepareStatement("select max(personid) from Person" );
            ResultSet resultSet2=P2.executeQuery();
            int a=0;
            while (resultSet2.next()) {
                a=resultSet2.getInt(1);
            }
            newPerson=a+1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void clear(){
        PersonIDTxt.clear();
        ContactNoTxt.clear();
        AddressTxt.clear();
        SkillsText.clear();
        ContactNoTxt.clear();
        emailTxt.clear();
        passwordTxt.clear();
        Gender.getSelectionModel().clearSelection();
    }

    public void initialize(){
        PersonCount();
        Gender.setItems(FXCollections.observableArrayList("Male","Female"));
        PersonIDTxt.setText(String.valueOf(newPerson));
    }

    public void backAdmin (ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/dashboardAdmin.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        window.centerOnScreen();
    }

    public void loadData (ActionEvent event){
        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            this.ScoutData = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("select person.personid,person.personname,person.contactno,person.email,person.address,person.gender,person.password,scout.skills from Person,scout where person.personid = scout.personid");
            while (rs.next()) {
                this.ScoutData.add(new ScoutManagement(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),(rs.getString(8))));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.iPersonID.setCellValueFactory(new PropertyValueFactory<ScoutManagement, Integer>("PersonID"));
        this.iName.setCellValueFactory(new PropertyValueFactory<ScoutManagement, String>("Name"));
        this.iContactNo.setCellValueFactory(new PropertyValueFactory<ScoutManagement, String>("ContactNO"));
        this.iEmail.setCellValueFactory(new PropertyValueFactory<ScoutManagement, String>("Email"));
        this.iAddress.setCellValueFactory(new PropertyValueFactory<ScoutManagement, String>("Address"));
        this.iGender.setCellValueFactory(new PropertyValueFactory<ScoutManagement, String>("Gender"));
        this.iPassword.setCellValueFactory(new PropertyValueFactory<ScoutManagement, String>("Password"));
        this.iSkills.setCellValueFactory(new PropertyValueFactory<ScoutManagement, String>("Skills"));
        this.iScoutTable.setItems(this.ScoutData);
    }

    public boolean isValidEmailAddress(String email) {
        if(email.matches(emailPattern))
            return true;
        else
            return false;
    }

    public boolean isValidPassword(String password) {
        if(PASSWORD_PATTERN.matcher(password).matches())
            return true;
        else
            return false;
    }

    public void update (ActionEvent event) throws IOException {
        int updateID = 0;
        if(iScoutTable.getSelectionModel().getSelectedItem()!=null) {
            if(!nameTxt.getText().isEmpty() && !AddressTxt.getText().isEmpty() && !SkillsText.getText().isEmpty() && !ContactNoTxt.getText().isEmpty() && !emailTxt.getText().isEmpty() && !passwordTxt.getText().isEmpty() && !PersonIDTxt.getText().isEmpty() && Gender.getValue()!=null) {
                PersonIDTxt.setEditable(false);
                PersonIDTxt.setText(String.valueOf(iScoutTable.getSelectionModel().getSelectedItem().getPersonID()));
                updateID = iScoutTable.getSelectionModel().getSelectedItem().getPersonID();
                Connection conn = null;
                try {
                    conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                    PreparedStatement del = conn.prepareStatement("update Person set personname = ?, contactno = ? ,email = ?  , address = ? , gender = ? ,password = ?  where personid = ?");
                    del.setString(1, nameTxt.getText());
                    del.setString(2,ContactNoTxt.getText());
                    del.setString(3, emailTxt.getText());
                    del.setString(4,AddressTxt.getText());
                    del.setString(5, String.valueOf(Gender.getValue()));
                    del.setString(6, passwordTxt.getText());
                    del.setInt(7, updateID);
                    del.execute();
                    PreparedStatement del2 = conn.prepareStatement("update Scout set skills = ? where personid = ?");
                    del2.setString(1, SkillsText.getText());
                    del2.setString(2,PersonIDTxt.getText());
                    del2.execute();
                    AlertBox("Update Successful","Row Updated Successfully");
                    loadData(event);
                    clear();
                    PersonCount();
                }
                catch (SQLException throwables) {
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

    public void add (ActionEvent event) throws IOException {
        String email = emailTxt.getText();
        String password = passwordTxt.getText();
        if(!isValidEmailAddress(email)){
            AlertBox("Registeration Error","Invalid Email");
        }
        else if(!isValidPassword(password)){
            AlertBox("Registeration Error","Invalid Password");
        }
        else if(!nameTxt.getText().isEmpty() && !AddressTxt.getText().isEmpty() && !SkillsText.getText().isEmpty() && !ContactNoTxt.getText().isEmpty() && !emailTxt.getText().isEmpty() && !passwordTxt.getText().isEmpty() && !PersonIDTxt.getText().isEmpty() && Gender.getValue()!=null) {
            try {
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                PreparedStatement insertEmp = conn.prepareStatement("insert into person values (?,?,?,?,?,?,?)");
                insertEmp.setInt(1, Integer.parseInt(PersonIDTxt.getText()));
                insertEmp.setString(2,nameTxt.getText());
                insertEmp.setString(3,ContactNoTxt.getText());
                insertEmp.setString(4,emailTxt.getText());
                insertEmp.setString(5,AddressTxt.getText());
                insertEmp.setString(6, (String) Gender.getValue());
                insertEmp.setString(7, passwordTxt.getText());
                insertEmp.executeQuery();
                PreparedStatement p2 = conn.prepareStatement("insert into Scout values (?,?)");
                p2.setInt(1, Integer.parseInt(PersonIDTxt.getText()));
                p2.setString(2,SkillsText.getText());
                p2.execute();
                AlertBox("Added Successfully","Added Successfully");
                loadData(event);
                clear();
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
        if(iScoutTable.getSelectionModel().getSelectedItem()!=null) {
            deleteID = iScoutTable.getSelectionModel().getSelectedItem().getPersonID();
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                PreparedStatement del = conn.prepareStatement("delete from Person where personid = ?");
                del.setInt(1, deleteID);
                del.execute();
                PreparedStatement del1 = conn.prepareStatement("delete from Scout where personid = ?");
                del1.setInt(1, deleteID);
                del1.execute();
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

    public void back (ActionEvent event) {
        try{
            String url = "";
            if(count == 1)
                url = "../GUI/dashboardAdmin.fxml";
            else if(count==5)
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
