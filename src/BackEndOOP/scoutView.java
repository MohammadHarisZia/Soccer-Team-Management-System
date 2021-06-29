package BackEndOOP;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class scoutView {

    private IntegerProperty personID;
    private StringProperty ScoutName;
    private StringProperty SkillsName;

    public scoutView() {
    }

    public scoutView(int personID, String scoutName, String skillsName) {
        this.personID = new SimpleIntegerProperty(personID);
        ScoutName = new SimpleStringProperty(scoutName);
        SkillsName = new SimpleStringProperty(skillsName);
    }

    public int getPersonID() {
        return personID.get();
    }

    public IntegerProperty personIDProperty() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID.set(personID);
    }

    public String getScoutName() {
        return ScoutName.get();
    }

    public StringProperty scoutNameProperty() {
        return ScoutName;
    }

    public void setScoutName(String scoutName) {
        this.ScoutName.set(scoutName);
    }

    public String getSkillsName() {
        return SkillsName.get();
    }

    public StringProperty skillsNameProperty() {
        return SkillsName;
    }

    public void setSkillsName(String skillsName) {
        this.SkillsName.set(skillsName);
    }
}
