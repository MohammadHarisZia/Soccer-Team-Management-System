package BackEndOOP;

import javafx.beans.property.*;

import java.sql.Date;

public class Match {
    private IntegerProperty MatchID;
    private StringProperty Fixture;
    private StringProperty ToBePlayed;
    private IntegerProperty NoOfSeats;
    private StringProperty date;
    private IntegerProperty TournamentID;

    public Match() {
    }

    public Match(int matchID, String fixture, String toBePlayed, int noOfSeats, String date, int tournamentID) {
        MatchID = new SimpleIntegerProperty(matchID);
        Fixture = new SimpleStringProperty(fixture);
        ToBePlayed = new SimpleStringProperty(toBePlayed);
        NoOfSeats = new SimpleIntegerProperty(noOfSeats);
        this.date = new SimpleStringProperty(date);
        TournamentID = new SimpleIntegerProperty(tournamentID);
    }

    public Match(int matchID, String fixture, String toBePlayed, int noOfSeats, String date) {
        MatchID = new SimpleIntegerProperty(matchID);
        Fixture = new SimpleStringProperty(fixture);
        ToBePlayed = new SimpleStringProperty(toBePlayed);
        NoOfSeats = new SimpleIntegerProperty(noOfSeats);
        this.date = new SimpleStringProperty(date);
    }

    public Match(Match m) {
        MatchID = m.MatchID;
        Fixture = m.Fixture;
        ToBePlayed = m.ToBePlayed;
        NoOfSeats = m.NoOfSeats;
        this.date = m.date;
        TournamentID = m.TournamentID;
    }

    public int getMatchID() {
        return MatchID.get();
    }

    public IntegerProperty matchIDProperty() {
        return MatchID;
    }

    public void setMatchID(int matchID) {
        this.MatchID.set(matchID);
    }

    public String getFixture() {
        return Fixture.get();
    }

    public StringProperty fixtureProperty() {
        return Fixture;
    }

    public void setFixture(String fixture) {
        this.Fixture.set(fixture);
    }

    public String getToBePlayed() {
        return ToBePlayed.get();
    }

    public StringProperty toBePlayedProperty() {
        return ToBePlayed;
    }

    public void setToBePlayed(String toBePlayed) {
        this.ToBePlayed.set(toBePlayed);
    }

    public int getNoOfSeats() {
        return NoOfSeats.get();
    }

    public IntegerProperty noOfSeatsProperty() {
        return NoOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.NoOfSeats.set(noOfSeats);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
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
