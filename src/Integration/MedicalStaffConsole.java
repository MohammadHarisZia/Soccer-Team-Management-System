package Integration;


import BackEndOOP.Recover;
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
import static Integration.Main.StatusPersonID;

public class MedicalStaffConsole {
    @FXML
    ComboBox PlayerID;
    @FXML
    TextField RecoverIDText,DietText,RemainingTimeText;
    @FXML
    TableView<Recover> RecoverTableID;
    @FXML
    TableColumn<Recover, Integer> iRecoverID;
    @FXML
    TableColumn<Recover, String> iDiet;
    @FXML
    TableColumn<Recover, String> iRemainingTime;
    @FXML
    TableColumn<Recover, String> iPlayerID;
    @FXML
    TableColumn<Recover, String> iMedicalStaffID;

    private ObservableList<Recover> RecoverData;

    public static int recoveryIDCount;

    ObservableList<Integer> playerList = FXCollections.observableArrayList();

    public void initialize(){
        PlayerCount();RecoveryCount();
        PlayerID.setItems(playerList);
        RecoverIDText.setEditable(false);
        RecoverIDText.setText(String.valueOf(recoveryIDCount+1));
    }

    public void PlayerCount(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            PreparedStatement P2=conn.prepareStatement("select personid from player where personid not in (select player_personid from recover)" );
            ResultSet resultSet2=P2.executeQuery();
            int a=0;
            while (resultSet2.next()) {
                a=resultSet2.getInt(1);
                playerList.add(a);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void RecoveryCount(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            PreparedStatement P2=conn.prepareStatement("select max(recoveryid) from recover" );
            ResultSet resultSet2=P2.executeQuery();
            int a=0;
            while (resultSet2.next()) {
                a=resultSet2.getInt(1);
            }
            recoveryIDCount=a;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void loadData (ActionEvent event){
            try {
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                this.RecoverData = FXCollections.observableArrayList();
                ResultSet rs = conn.createStatement().executeQuery("select recoveryid,diet,remainingtime,player_personid,medical_staff_personid from recover");
                while (rs.next()) {
                    this.RecoverData.add(new Recover(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        this.iRecoverID.setCellValueFactory(new PropertyValueFactory<Recover, Integer>("RecoverID"));
        this.iDiet.setCellValueFactory(new PropertyValueFactory<Recover, String>("Diet"));
        this.iRemainingTime.setCellValueFactory(new PropertyValueFactory<Recover, String>("remainingTime"));
        this.iPlayerID.setCellValueFactory(new PropertyValueFactory<Recover, String>("Player_personID"));
        this.iMedicalStaffID.setCellValueFactory(new PropertyValueFactory<Recover, String>("MedStaff_PersonID"));
        this.RecoverTableID.setItems(this.RecoverData);
        }

        public void clear(){
            RecoverIDText.clear();
            RemainingTimeText.clear();
            DietText.clear();
            PlayerID.getSelectionModel().clearSelection();
            playerList.clear();
        }

    public void add (ActionEvent event) throws IOException {
        if(!RecoverIDText.getText().isEmpty() && !RemainingTimeText.getText().isEmpty() && !DietText.getText().isEmpty() && PlayerID.getValue()!=null) {
            try {
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                PreparedStatement insertEmp = conn.prepareStatement("insert into Recover values (?,?,?,?,?,?)");
                insertEmp.setInt(1, Integer.parseInt(RecoverIDText.getText()));
                insertEmp.setString(2, DietText.getText());
                insertEmp.setString(3, RemainingTimeText.getText());
                insertEmp.setInt(4, (Integer) PlayerID.getValue());
                insertEmp.setInt(5, StatusPersonID);
                insertEmp.setNull(6,0);
                insertEmp.execute();
                AlertBox("Added Successfully","Added Successfully");
                loadData(event);
                clear();
                RecoveryCount();
                PlayerCount();
                RecoverIDText.setText(String.valueOf(recoveryIDCount+1));
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
        if(RecoverTableID.getSelectionModel().getSelectedItem()!=null) {
            deleteID = RecoverTableID.getSelectionModel().getSelectedItem().getRecoverID();
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                PreparedStatement del = conn.prepareStatement("delete from recover where recoveryid = ?");
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

    public void update (ActionEvent event) throws IOException {
        int updateID = 0;
        if(RecoverTableID.getSelectionModel().getSelectedItem()!=null) {
            if(!RecoverIDText.getText().isEmpty() && !RemainingTimeText.getText().isEmpty() && !DietText.getText().isEmpty()){
                RecoverIDText.setEditable(false);
                RecoverIDText.setText(String.valueOf(RecoverTableID.getSelectionModel().getSelectedItem().getRecoverID()));
                PlayerID.setValue(RecoverTableID.getSelectionModel().getSelectedItem().getPlayer_personID());
                updateID = RecoverTableID.getSelectionModel().getSelectedItem().getRecoverID();
                Connection conn = null;
                try {
                    conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
                    PreparedStatement del = conn.prepareStatement("update recover set recoveryid = ? ,diet = ? ,remainingtime = ?   where recoveryid = ?");
                    del.setInt(1, updateID);
                    del.setString(2, DietText.getText());
                    del.setString(3, RemainingTimeText.getText());
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

    public void backStatus (ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/Status.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        window.centerOnScreen();
    }
}
