package BackEndOOP;

public class Sponsor {
    private int SponsorID;
    private String SponsorName;

    public Sponsor() {

    }

    public Sponsor(int sponsorID, String sponsorName) {
        SponsorID = sponsorID;
        SponsorName = sponsorName;
    }

    public Sponsor(Sponsor s) {
        SponsorID = s.SponsorID;
        SponsorName = s.SponsorName;
    }

    public int getSponsorID() {
        return SponsorID;
    }

    public void setSponsorID(int sponsorID) {
        SponsorID = sponsorID;
    }

    public String getSponsorName() {
        return SponsorName;
    }

    public void setSponsorName(String sponsorName) {
        SponsorName = sponsorName;
    }
}
