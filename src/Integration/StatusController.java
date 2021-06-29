package Integration;

import javafx.event.ActionEvent;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static Integration.Main.AlertBox;
import static Integration.RegisterController.personId;

import static Integration.Main.count;

public class StatusController {

    @FXML
    TextField adminSkillsText,strengthText,netWorthText,scoutSkillsText;

    @FXML
    DatePicker adminDate,electedTillDate;

    public static int PresidentCount;
    public static int ManagerCount;

    public void adminRegister(ActionEvent event)throws IOException {
        if(!adminSkillsText.getText().isEmpty() && adminDate.getValue()!=null){

            LocalDate aDate = adminDate.getValue();
            String formattedDate = aDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

            try {
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                PreparedStatement pStat2 = conn.prepareStatement("insert into admin values (?,?,?)");
                pStat2.setInt(1,personId);
                pStat2.setString(2,formattedDate);
                pStat2.setString (3, adminSkillsText.getText());
                pStat2.execute();
                AlertBox("Registration Successful","Registration Done Successfully");
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/Login.fxml"));
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
            AlertBox("Registration Error","Fields are Empty");
    }

    public void managerRegister(ActionEvent event){
        if(!strengthText.getText().isEmpty()){
            Connection conn = null;
            try {
                String url = "";
                ManagerCount = personId;
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                PreparedStatement pStat2 = conn.prepareStatement("insert into manager values (?,?)");
                pStat2.setInt(1,personId);
                pStat2.setString(2,strengthText.getText());
                pStat2.execute();
                if(count==2){
                    url = "../GUI/Login.fxml";
                    AlertBox("Registration Successful","Registration Done Successfully");
                }
                else if(count ==13) {
                    url = "../GUI/Register.fxml";
                    AlertBox("Manager Added","Manager Added Successfully");
                    count=14;
                    AlertBox("Please Add President","Please Add President");
                }
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
            AlertBox("Registration Error","Fields are Empty");

    }

    public void PresidentRegister(ActionEvent event)throws IOException {
        if(!netWorthText.getText().isEmpty() && electedTillDate.getValue()!=null){

            LocalDate aDate = electedTillDate.getValue();
            String formattedDate = aDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

            try {
                if(!netWorthText.getText().matches(".*\\d.*"))
                    AlertBox("Error","Net Worth Must be in integer per billion");
                else {
                    PresidentCount = personId;
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                    PreparedStatement pStat2 = conn.prepareStatement("insert into president values (?,?,?)");
                    pStat2.setInt(1, personId);
                    pStat2.setString(2, formattedDate);
                    pStat2.setString(3, netWorthText.getText());
                    pStat2.execute();
                    String url = "";
                    if (count == 5) {
                        url = "../GUI/Login.fxml";
                        AlertBox("Registration Successful", "Registration Done Successfully");
                    } else if (count == 14) {
                        url = "../GUI/ArenaAddition.fxml";
                        AlertBox("President Added", "President Added Successfully");
                        AlertBox("Please Add Arena", "Please Add Arena");
                    }
                    Parent tableViewParent = FXMLLoader.load(getClass().getResource(url));
                    Scene tableViewScene = new Scene(tableViewParent);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(tableViewScene);
                    window.show();
                    window.centerOnScreen();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else
            AlertBox("Registration Error","Fields are Empty");
    }

    public void ScoutRegister(ActionEvent event){
        if(!scoutSkillsText.getText().isEmpty()){
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                PreparedStatement pStat2 = conn.prepareStatement("insert into scout values (?,?)");
                pStat2.setInt(1,personId);
                pStat2.setString(2,scoutSkillsText.getText());
                pStat2.execute();
                AlertBox("Registration Successful","Registration Done Successfully");
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/Status.fxml"));
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
            AlertBox("Registration Error","Fields are Empty");
    }


}
