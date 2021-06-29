package BackEndOOP;

public class Referee {
    private int PersonID;
    private int MatchesAsReferee;
    private int NoOfYellowCards;
    private int NoOfRedCards;
    private int MatchID;

    public Referee() {
    }

    public Referee(int personID, int matchesAsReferee, int noOfYellowCards, int noOfRedCards, int matchID) {
        PersonID = personID;
        MatchesAsReferee = matchesAsReferee;
        NoOfYellowCards = noOfYellowCards;
        NoOfRedCards = noOfRedCards;
        MatchID = matchID;
    }

    public Referee(Referee r) {
        PersonID = r.PersonID;
        MatchesAsReferee = r.MatchesAsReferee;
        NoOfYellowCards = r.NoOfYellowCards;
        NoOfRedCards = r.NoOfRedCards;
        MatchID = r.MatchID;
    }

    public int getPersonID() {
        return PersonID;
    }

    public void setPersonID(int personID) {
        PersonID = personID;
    }

    public int getMatchesAsReferee() {
        return MatchesAsReferee;
    }

    public void setMatchesAsReferee(int matchesAsReferee) {
        MatchesAsReferee = matchesAsReferee;
    }

    public int getNoOfYellowCards() {
        return NoOfYellowCards;
    }

    public void setNoOfYellowCards(int noOfYellowCards) {
        NoOfYellowCards = noOfYellowCards;
    }

    public int getNoOfRedCards() {
        return NoOfRedCards;
    }

    public void setNoOfRedCards(int noOfRedCards) {
        NoOfRedCards = noOfRedCards;
    }

    public int getMatchID() {
        return MatchID;
    }

    public void setMatchID(int matchID) {
        MatchID = matchID;
    }
}
