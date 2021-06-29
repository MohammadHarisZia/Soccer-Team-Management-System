package BackEndOOP;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Recover {
    private IntegerProperty RecoverID;
    private StringProperty Diet;
    private StringProperty RemainingTime;
    private IntegerProperty Player_personID;
    private IntegerProperty MedStaff_PersonID;
    private IntegerProperty Trainer_PersonID;

    public Recover() {

    }

    public Recover(int recoverID, String diet, String remainingTime, int player_personID, int medStaff_PersonID, int trainer_PersonID) {
        RecoverID = new SimpleIntegerProperty(recoverID);
        Diet = new SimpleStringProperty(diet);
        RemainingTime = new SimpleStringProperty(remainingTime);
        Player_personID = new SimpleIntegerProperty(player_personID);
        MedStaff_PersonID = new SimpleIntegerProperty(medStaff_PersonID);
        Trainer_PersonID = new SimpleIntegerProperty(trainer_PersonID);
    }
    public Recover(int recoverID, String diet, String remainingTime, int player_personID, int medStaff_PersonID) {
        RecoverID = new SimpleIntegerProperty(recoverID);
        Diet = new SimpleStringProperty(diet);
        RemainingTime = new SimpleStringProperty(remainingTime);
        Player_personID = new SimpleIntegerProperty(player_personID);
        MedStaff_PersonID = new SimpleIntegerProperty(medStaff_PersonID);
    }

    public Recover(Recover r) {
        RecoverID = r.RecoverID;
        Diet = r.Diet;
        RemainingTime = r.RemainingTime;
        Player_personID = r.Player_personID;
        MedStaff_PersonID = r.MedStaff_PersonID;
        Trainer_PersonID = r.Trainer_PersonID;
    }

    public int getRecoverID() {
        return RecoverID.get();
    }

    public IntegerProperty recoverIDProperty() {
        return RecoverID;
    }

    public void setRecoverID(int recoverID) {
        this.RecoverID.set(recoverID);
    }

    public String getDiet() {
        return Diet.get();
    }

    public StringProperty dietProperty() {
        return Diet;
    }

    public void setDiet(String diet) {
        this.Diet.set(diet);
    }

    public String getRemainingTime() {
        return RemainingTime.get();
    }

    public StringProperty remainingTimeProperty() {
        return RemainingTime;
    }

    public void setRemainingTime(String remainingTime) {
        this.RemainingTime.set(remainingTime);
    }

    public int getPlayer_personID() {
        return Player_personID.get();
    }

    public IntegerProperty player_personIDProperty() {
        return Player_personID;
    }

    public void setPlayer_personID(int player_personID) {
        this.Player_personID.set(player_personID);
    }

    public int getMedStaff_PersonID() {
        return MedStaff_PersonID.get();
    }

    public IntegerProperty medStaff_PersonIDProperty() {
        return MedStaff_PersonID;
    }

    public void setMedStaff_PersonID(int medStaff_PersonID) {
        this.MedStaff_PersonID.set(medStaff_PersonID);
    }

    public int getTrainer_PersonID() {
        return Trainer_PersonID.get();
    }

    public IntegerProperty trainer_PersonIDProperty() {
        return Trainer_PersonID;
    }

    public void setTrainer_PersonID(int trainer_PersonID) {
        this.Trainer_PersonID.set(trainer_PersonID);
    }
}
