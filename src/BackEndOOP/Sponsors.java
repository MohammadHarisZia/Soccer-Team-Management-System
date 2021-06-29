package BackEndOOP;

public class Sponsors {
    private int TeamID;
    private int SponsorID;

    public Sponsors() {
    }

    public Sponsors(int teamID, int sponsorID) {
        TeamID = teamID;
        SponsorID = sponsorID;
    }

    public Sponsors(Sponsors s) {
        TeamID = s.TeamID;
        SponsorID = s.SponsorID;
    }

    public int getTeamID() {
        return TeamID;
    }

    public void setTeamID(int teamID) {
        TeamID = teamID;
    }

    public int getSponsorID() {
        return SponsorID;
    }

    public void setSponsorID(int sponsorID) {
        SponsorID = sponsorID;
    }
}
