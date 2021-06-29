package BackEndOOP;

public class Plays {
    private int TeamID;
    private int MatchID;

    public Plays() {
    }

    public Plays(int teamID, int matchID) {
        TeamID = teamID;
        MatchID = matchID;
    }

    public Plays(Plays p) {
        TeamID = p.TeamID;
        MatchID = p.MatchID;
    }

    public int getTeamID() {
        return TeamID;
    }

    public void setTeamID(int teamID) {
        TeamID = teamID;
    }

    public int getMatchID() {
        return MatchID;
    }

    public void setMatchID(int matchID) {
        MatchID = matchID;
    }
}
