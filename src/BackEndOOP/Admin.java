package BackEndOOP;

import java.util.Date;

public class Admin {
    private int PersonID;
    private Date DateHired;
    private String Skills;

    public Admin() {
    }

    public Admin(int personID, Date dateHired, String skills) {
        PersonID = personID;
        DateHired = dateHired;
        Skills = skills;
    }

    public Admin(Admin a) {
        PersonID= a.PersonID;
        DateHired = a.DateHired;
        Skills = a.Skills;
    }

    public int getPersonID() {
        return PersonID;
    }

    public void setPersonID(int personID) {
        PersonID = personID;
    }

    public java.sql.Date getDateHired() {
        return (java.sql.Date) DateHired;
    }

    public void setDateHired(Date dateHired) {
        DateHired = dateHired;
    }

    public String getSkills() {
        return Skills;
    }

    public void setSkills(String skills) {
        Skills = skills;
    }
}
