package Integration;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.util.regex.Pattern;
import static Integration.Main.AlertBox;
import static Integration.Main.count;


public class RegisterController{

    @FXML
    Button registerBtn;

    @FXML
    TextField nameTxt,emailTxt,passwordTxt,ContactNoTxt,AddressTxt;

    String name,email,password,contactNo,Address = "";

    @FXML
    public ComboBox <String> genderCombo;

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

    public static int personId = 0;

    public void initialize() {
        genderCombo.setItems(FXCollections.observableArrayList("Male","Female"));
    }

    public Integer countPerson(){
        int personCount = 0;
        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            PreparedStatement P2=conn.prepareStatement("SELECT COUNT(personid) FROM person" );
            ResultSet resultSet2=P2.executeQuery();
            int a=0;
            while (resultSet2.next()) {
                a=resultSet2.getInt(1)+1;
            }
            personCount=a;
        }catch (Exception e){
            e.printStackTrace();
        }
        return personCount;
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

    public void personRegister(ActionEvent event){
        name = nameTxt.getText();
        email = emailTxt.getText();
        password = passwordTxt.getText();
        contactNo = ContactNoTxt.getText();
        Address = AddressTxt.getText();

        String url1 = "";
        if (count==1){
            url1 = "../GUI/AdminRegister.fxml";
        }
        else if (count==2){
            url1 = "../GUI/ManagerRegister.fxml";
        }
        else if (count==3){
            url1 = "../GUI/MedicalStaffRegister.fxml";
        }
        else if (count==4){
            url1 = "../GUI/TrainerRegister.fxml";
        }
        else if (count==5){
            url1 = "../GUI/PresidentRegister.fxml";
        }
        else if (count==6){
            url1 = "../GUI/RefereeRegister.fxml";
        }
        else if (count==7){
            url1 = "../GUI/PlayerRegister.fxml";
        }
        else if (count==8){
            url1 = "../GUI/ScoutRegister.fxml";
        }
        else if (count==9){
            url1 = "../GUI/AmbassadorRegister.fxml";
        }
        else if (count==10){
            url1 = "../GUI/SpectatorRegister.fxml";
        }
        else if(count==11)
            url1 = "../GUI/PlayerRegister.fxml";

        else if (count==12){
            url1 = "../GUI/SpectatorRegister.fxml";
        }
        else if (count==13){
            url1 = "../GUI/ManagerRegister.fxml";
        }
        else if (count==14){
            url1 = "../GUI/PresidentRegister.fxml";
        }
        else if (count==15 || count==16){
            url1 = "../GUI/PlayerRegister.fxml";
        }
        Parent tableViewParent = null;
            if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !contactNo.isEmpty() && !Address.isEmpty() && genderCombo.getValue()!=null)
                {
                    boolean emailCheck=isValidEmailAddress(email);
                    if(!isValidEmailAddress(email)){
                        AlertBox("Registration Error","Invalid Email");
                    }
                    else if(!isValidPassword(password)){
                        AlertBox("Registration Error","Invalid Password");
                    }
                    else if(name.matches(".*\\d.*")){
                        AlertBox("Registration Error","Name Cant have numbers");
                    }
                    else if(contactNo.length()>=12 || !contactNo.matches("^[0-9]*$")){
                        AlertBox("Registration Error","Contact Number Must be less than 12 digits and no Characters Allowed");
                    }
                    else{
                        Connection conn = null;
                        try {
                            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                            PreparedStatement P1 = conn.prepareStatement("select personid from person WHERE email IN ?");
                            P1.setString(1, email);
                            ResultSet resultSet = P1.executeQuery();
                            if (resultSet.next() == false) {
                                personId = countPerson();
                                PreparedStatement insertEmp = conn.prepareStatement("insert into person values (?,?,?,?,?,?,?)");
                                insertEmp.setInt(1,personId);
                                insertEmp.setString(2,nameTxt.getText());
                                insertEmp.setString(3,ContactNoTxt.getText());
                                insertEmp.setString(4,emailTxt.getText());
                                insertEmp.setString(5,AddressTxt.getText());
                                insertEmp.setString(6,genderCombo.getValue());
                                insertEmp.setString(7, passwordTxt.getText());
                                insertEmp.executeQuery();
                                if(count==13 || count ==14)
                                    AlertBox("Added Successful","Added Successful");
                                else
                                    AlertBox("Registration Successful","Registration Successful");
                                tableViewParent = FXMLLoader.load(getClass().getResource(url1));
                                Scene tableViewScene = new Scene(tableViewParent);
                                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                window.centerOnScreen();
                                window.setScene(tableViewScene);
                                window.show();
                                window.centerOnScreen();
                            }
                            else
                                AlertBox("Registration Error","Email Must be Unique");

                        } catch (SQLException | IOException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                }
            else
            {
                AlertBox("Registration Error","Fields are Empty");
            }
        }

    public void backRegister (ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/Register.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        window.centerOnScreen();

    }

    public void backLogin (ActionEvent event) throws IOException {
        String url = "";
        if(count==6 || count==7 || count == 8 || count==9)
            url = "../GUI/Status.fxml";
        else if(count==11)
            url = "../GUI/dashboardAdmin.fxml";
        else if(count==13)
            url = "../GUI/TeamManagement.fxml";
        else if(count==15)
            url = "../GUI/dashboardPresident.fxml";
        else if(count==16)
            url = "../GUI/dashboardManager.fxml";
        else
            url = "../GUI/Login.fxml";

        Parent tableViewParent = FXMLLoader.load(getClass().getResource(url));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        window.centerOnScreen();
    }
}
