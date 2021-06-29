package BackEndOOP;

import java.time.temporal.Temporal;

public class Trainer {
    private int PersonID;
    private String Grade;
    private int TypeOfPlayer;
    private int TeamID;

    public Trainer() {
    }

    public Trainer(int personID, String grade, int typeOfPlayer, int teamID) {
        PersonID = personID;
        Grade = grade;
        TypeOfPlayer = typeOfPlayer;
        TeamID = teamID;
    }

    public Trainer(Trainer t) {
        PersonID = t.PersonID;
        Grade = t.Grade;
        TypeOfPlayer = t.TypeOfPlayer;
        TeamID = t.TeamID;
    }

    public int getPersonID() {
        return PersonID;
    }

    public void setPersonID(int personID) {
        PersonID = personID;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public int getTypeOfPlayer() {
        return TypeOfPlayer;
    }

    public void setTypeOfPlayer(int typeOfPlayer) {
        TypeOfPlayer = typeOfPlayer;
    }

    public int getTeamID() {
        return TeamID;
    }

    public void setTeamID(int teamID) {
        TeamID = teamID;
    }
}
