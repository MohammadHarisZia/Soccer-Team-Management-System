package Integration;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static Integration.Main.AlertBox;
import static Integration.RegisterController.personId;
import static Integration.tableController.countryID;

public class AmbassadorRegisterController {

    @FXML
    TextField contractText,countryIDAmbassadorText;

    public void initialize(){

        countryIDAmbassadorText.setText(String.valueOf(countryID));
    }

    public void countryView(ActionEvent event){
        Parent tableViewParent = null;
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/countryView.fxml"));
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



    public void AmbassadorRegister(ActionEvent event){
        if(!contractText.getText().isEmpty()){
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                PreparedStatement pStat2 = conn.prepareStatement("insert into Ambassador values (?,?,?)");
                pStat2.setInt(1,personId);
                pStat2.setInt(2, Integer.parseInt(contractText.getText()));
                if(contractText.getText().equals("0")){
                    AlertBox("Country ID is Null","Country ID is Null,Select a Country ID if it will be null");
                    pStat2.setNull(3,0);
                }
                else
                    countryIDAmbassadorText.setText(String.valueOf(countryID));
                pStat2.setInt(3,countryID);

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
    }

}
