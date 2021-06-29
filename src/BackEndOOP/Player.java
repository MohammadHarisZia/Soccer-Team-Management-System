package BackEndOOP;

public class Player {
    private int PlayerID;
    private String position;
    private int TeamID;
    private String InjuryDetails;
    private String Suspensions;
    private int Scout_PersonID;
    private String Available;

    public Player() {

    }

    public Player(int playerID, String position, int teamID, String injuryDetails, String suspensions, int scout_PersonID, String available) {
        PlayerID = playerID;
        this.position = position;
        TeamID = teamID;
        InjuryDetails = injuryDetails;
        Suspensions = suspensions;
        Scout_PersonID = scout_PersonID;
        Available = available;
    }
    public Player(int playerID, String position, int teamID, String injuryDetails, String suspensions, int scout_PersonID) {
        PlayerID = playerID;
        this.position = position;
        TeamID = teamID;
        InjuryDetails = injuryDetails;
        Suspensions = suspensions;
        Scout_PersonID = scout_PersonID;
    }

    public Player(Player p) {
        PlayerID = p.PlayerID;
        this.position = p.position;
        TeamID = p.TeamID;
        InjuryDetails = p.InjuryDetails;
        Suspensions = p.Suspensions;
        Scout_PersonID = p.Scout_PersonID;
        Available = p.Available;
    }

    public int getPlayerID() {
        return PlayerID;
    }

    public void setPlayerID(int playerID) {
        PlayerID = playerID;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getTeamID() {
        return TeamID;
    }

    public void setTeamID(int teamID) {
        TeamID = teamID;
    }

    public String getInjuryDetails() {
        return InjuryDetails;
    }

    public void setInjuryDetails(String injuryDetails) {
        InjuryDetails = injuryDetails;
    }

    public String getSuspensions() {
        return Suspensions;
    }

    public void setSuspensions(String suspensions) {
        Suspensions = suspensions;
    }

    public int getScout_PersonID() {
        return Scout_PersonID;
    }

    public void setScout_PersonID(int scout_PersonID) {
        Scout_PersonID = scout_PersonID;
    }

    public String getAvailable() {
        return Available;
    }

    public void setAvailable(String available) {
        Available = available;
    }
}
