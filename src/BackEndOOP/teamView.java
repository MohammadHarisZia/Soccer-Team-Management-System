package BackEndOOP;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class teamView {
    private IntegerProperty teamID;
    private StringProperty teamName;
    private StringProperty arenaName;

    public teamView() {
    }

    public teamView(int tID,String tName,String aName) {
        this.teamID= new SimpleIntegerProperty(tID);
        this.teamName= new SimpleStringProperty(tName);
        this.arenaName= new SimpleStringProperty(aName);
    }

    public int getTeamID() {
        return teamID.get();
    }

    public IntegerProperty teamIDProperty() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID.set(teamID);
    }

    public String getTeamName() {
        return teamName.get();
    }

    public StringProperty teamNameProperty() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName.set(teamName);
    }

    public String getArenaName() {
        return arenaName.get();
    }

    public StringProperty arenaNameProperty() {
        return arenaName;
    }

    public void setArenaName(String arenaName) {
        this.arenaName.set(arenaName);
    }
}
