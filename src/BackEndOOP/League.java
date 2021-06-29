package BackEndOOP;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

public class League {
    private int MatchID;
    private String Fixture;
    private String ToBePlayed;
    private int NoOfSeats;
    private String date;
    private int TournamentID;
    private String TournamentName;



    public League(int matchID, String fixture, String toBePlayed, int noOfSeats, String playDate, int tournamentID, String tournamentName) {
        MatchID = matchID;
        Fixture = fixture;
        ToBePlayed = toBePlayed;
        NoOfSeats = noOfSeats;
        date = playDate;
        TournamentID = tournamentID;
        TournamentName = tournamentName;
    }

    public int getMatchID() {
        return MatchID;
    }

    public void setMatchID(int matchID) {
        MatchID = matchID;
    }

    public String getFixture() {
        return Fixture;
    }

    public void setFixture(String fixture) {
        Fixture = fixture;
    }

    public String getToBePlayed() {
        return ToBePlayed;
    }

    public void setToBePlayed(String toBePlayed) {
        ToBePlayed = toBePlayed;
    }

    public int getNoOfSeats() {
        return NoOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        NoOfSeats = noOfSeats;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
