package BackEndOOP;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Team {
    private IntegerProperty TeamID;
    private StringProperty TeamName;
    private IntegerProperty ArenaID;
    private IntegerProperty Admin_PersonID;
    private IntegerProperty President_PersonID;
    private IntegerProperty Manager_PersonID;
    private IntegerProperty TournamentID;

    public Team() {
    }

    public Team(int teamID, String teamName, int arenaID, int admin_PersonID, int president_PersonID, int manager_PersonID, int tournamentID) {
        this.TeamID = new SimpleIntegerProperty (teamID);
        TeamName = new SimpleStringProperty(teamName);
        ArenaID = new SimpleIntegerProperty (arenaID);
        Admin_PersonID = new SimpleIntegerProperty (admin_PersonID);
        President_PersonID = new SimpleIntegerProperty (president_PersonID);
        Manager_PersonID = new SimpleIntegerProperty (manager_PersonID);
        TournamentID = new SimpleIntegerProperty (tournamentID);
    }

    public Team(Team t) {
        TeamID = t.TeamID;
        TeamName = t.TeamName;
        ArenaID = t.ArenaID;
        Admin_PersonID = t.Admin_PersonID;
        President_PersonID = t.President_PersonID;
        Manager_PersonID = t.Manager_PersonID;
        TournamentID = t.TournamentID;
    }

    public int getTeamID() {
        return TeamID.get();
    }

    public IntegerProperty teamIDProperty() {
        return TeamID;
    }

    public void setTeamID(int teamID) {
        this.TeamID.set(teamID);
    }

    public String getTeamName() {
        return TeamName.get();
    }

    public StringProperty teamNameProperty() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        this.TeamName.set(teamName);
    }

    public int getArenaID() {
        return ArenaID.get();
    }

    public IntegerProperty arenaIDProperty() {
        return ArenaID;
    }

    public void setArenaID(int arenaID) {
        this.ArenaID.set(arenaID);
    }

    public int getAdmin_PersonID() {
        return Admin_PersonID.get();
    }

    public IntegerProperty admin_PersonIDProperty() {
        return Admin_PersonID;
    }

    public void setAdmin_PersonID(int admin_PersonID) {
        this.Admin_PersonID.set(admin_PersonID);
    }

    public int getPresident_PersonID() {
        return President_PersonID.get();
    }

    public IntegerProperty president_PersonIDProperty() {
        return President_PersonID;
    }

    public void setPresident_PersonID(int president_PersonID) {
        this.President_PersonID.set(president_PersonID);
    }

    public int getManager_PersonID() {
        return Manager_PersonID.get();
    }

    public IntegerProperty manager_PersonIDProperty() {
        return Manager_PersonID;
    }

    public void setManager_PersonID(int manager_PersonID) {
        this.Manager_PersonID.set(manager_PersonID);
    }

    public int getTournamentID() {
        return TournamentID.get();
    }

    public IntegerProperty tournamentIDProperty() {
        return TournamentID;
    }

    public void setTournamentID(int tournamentID) {
        this.TournamentID.set(tournamentID);
    }
}
