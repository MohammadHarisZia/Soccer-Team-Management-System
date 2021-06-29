package BackEndOOP;

public class Tournament {
    private int TournamentID;
    private String TournamentName;

    public Tournament() {
    }

    public Tournament(int tournamentID, String tournamentName) {
        TournamentID = tournamentID;
        TournamentName = tournamentName;
    }

    public Tournament(Tournament t) {
        TournamentID = t.TournamentID;
        TournamentName = t.TournamentName;
    }

    public int getTournamentID() {
        return TournamentID;
    }

    public void setTournamentID(int tournamentID) {
        TournamentID = tournamentID;
    }

    public String getTournamentName() {
        return TournamentName;
    }

    public void setTournamentName(String tournamentName) {
        TournamentName = tournamentName;
    }
}
