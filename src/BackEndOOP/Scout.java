package BackEndOOP;

public class Scout {
    private int PersonID;
    private String Name;
    private String Gender;
    private String Skills;


    public Scout() {
    }

    public Scout(int personID, String n, String g,String skills) {
        PersonID = personID;
        Name=n;
        Gender=g;
        Skills = skills;
    }

    public Scout(String skills) {
        Skills = skills;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public int getPersonID() {
        return PersonID;
    }

    public void setPersonID(int personID) {
        PersonID = personID;
    }

    public String getSkills() {
        return Skills;
    }

    public void setSkills(String skills) {
        Skills = skills;
    }
}
