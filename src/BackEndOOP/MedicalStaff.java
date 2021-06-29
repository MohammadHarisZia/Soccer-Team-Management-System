package BackEndOOP;

public class MedicalStaff {
    private int PersonID;
    private String Type;
    private int TeamID;

    public MedicalStaff() {
    }

    public MedicalStaff(int personID, String type, int teamID) {
        PersonID = personID;
        Type = type;
        TeamID = teamID;
    }

    public MedicalStaff(MedicalStaff ms) {
        PersonID = ms.PersonID;
        Type = ms.Type;
        TeamID = ms.TeamID;
    }

    public int getPersonID() {
        return PersonID;
    }

    public void setPersonID(int personID) {
        PersonID = personID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getTeamID() {
        return TeamID;
    }

    public void setTeamID(int teamID) {
        TeamID = teamID;
    }
}
