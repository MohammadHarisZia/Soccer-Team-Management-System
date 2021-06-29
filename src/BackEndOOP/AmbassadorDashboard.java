package BackEndOOP;

public class AmbassadorDashboard {
    public int teamID;
    public String teamName;
    public int sponsorID;
    public String sponsorName;

    public AmbassadorDashboard() {
    }

    public AmbassadorDashboard(int teamID, String teamName, int sponsorID, String sponsorName) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.sponsorID = sponsorID;
        this.sponsorName = sponsorName;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getSponsorID() {
        return sponsorID;
    }

    public void setSponsorID(int sponsorID) {
        this.sponsorID = sponsorID;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }
}
