package Integration;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static Integration.Main.AlertBox;
import static Integration.RegisterController.personId;
import static Integration.tableController.teamViewTeamID;

public class TrainerRegisterController {
    @FXML
    TextField gradeText,typeOfPlayerText,TrainerTeamID;

    @FXML
    Button registerTrainer,showTeamsBtn;

    public void initialize(){
        TrainerTeamID.setText(String.valueOf(teamViewTeamID));
        TrainerTeamID.setEditable(false);
    }

    public void TrainerTeamView(ActionEvent event){
        Parent tableViewParent = null;
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/teamView.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
            window.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
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

    public void TrainerRegister(ActionEvent event){
        if(typeOfPlayerText.getText().matches(".*\\d.*") || gradeText.getText().matches(".*\\d.*"))
            AlertBox("Registration Error","Grade or Type cant have numbers");
        else if( !typeOfPlayerText.getText().isEmpty() && !gradeText.getText().isEmpty()){
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                PreparedStatement pStat2 = conn.prepareStatement("insert into trainer values (?,?,?,?)");
                pStat2.setInt(1,personId);
                pStat2.setString(2,gradeText.getText());
                pStat2.setString(3,typeOfPlayerText.getText());

                if(TrainerTeamID.getText().equals("0")){
                    pStat2.setNull(4,0);
                }
                else {
                    TrainerTeamID.setText(String.valueOf(teamViewTeamID));
                    pStat2.setInt(4, teamViewTeamID);
                }
                pStat2.execute();
                AlertBox("Registration Successful","Registration Done Successfully");
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/Login.fxml"));
                Scene tableViewScene = new Scene(tableViewParent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(tableViewScene);
                window.show();
                window.centerOnScreen();

            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
