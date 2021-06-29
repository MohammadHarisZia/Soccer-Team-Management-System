package BackEndOOP;

public class Manager {
    private int PersonID;
    private String Strength;

    public Manager() {
    }

    public Manager(int personID, String strength) {
        PersonID = personID;
        Strength = strength;
    }

    public Manager(Manager m) {
        PersonID = m.PersonID;
        Strength = m.Strength;
    }

    public int getPersonID() {
        return PersonID;
    }

    public void setPersonID(int personID) {
        PersonID = personID;
    }

    public String getStrength() {
        return Strength;
    }

    public void setStrength(String strength) {
        Strength = strength;
    }
}
