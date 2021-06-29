package BackEndOOP;

import BackEndOOP.Person;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ScoutManagement {
    private IntegerProperty PersonID;
    private StringProperty Name;
    private StringProperty ContactNO;
    private StringProperty Email;
    private StringProperty Address;
    private StringProperty Gender;
    private StringProperty Password;
    private StringProperty Skills;

    public ScoutManagement() {
        System.out.println("Values cant be null");
    }

    public ScoutManagement(int personID, String name, String contactNO, String email, String address, String gender, String password, String skills) {
        this.PersonID = new SimpleIntegerProperty(personID);
        this.Name = new SimpleStringProperty(name);
        this.ContactNO = new SimpleStringProperty(contactNO);
        this.Email = new SimpleStringProperty(email);
        this.Address = new SimpleStringProperty(address);
        this.Gender = new SimpleStringProperty(gender);
        this.Password = new SimpleStringProperty(password);
        this.Skills = new SimpleStringProperty(skills);
    }

    public int getPersonID() {
        return PersonID.get();
    }

    public IntegerProperty personIDProperty() {
        return PersonID;
    }

    public void setPersonID(int personID) {
        this.PersonID.set(personID);
    }

    public String getName() {
        return Name.get();
    }

    public StringProperty nameProperty() {
        return Name;
    }

    public void setName(String name) {
        this.Name.set(name);
    }

    public String getContactNO() {
        return ContactNO.get();
    }

    public StringProperty contactNOProperty() {
        return ContactNO;
    }

    public void setContactNO(String contactNO) {
        this.ContactNO.set(contactNO);
    }

    public String getEmail() {
        return Email.get();
    }

    public StringProperty emailProperty() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email.set(email);
    }

    public String getAddress() {
        return Address.get();
    }

    public StringProperty addressProperty() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address.set(address);
    }

    public String getGender() {
        return Gender.get();
    }

    public StringProperty genderProperty() {
        return Gender;
    }

    public void setGender(String gender) {
        this.Gender.set(gender);
    }

    public String getPassword() {
        return Password.get();
    }

    public StringProperty passwordProperty() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password.set(password);
    }

    public String getSkills() {
        return Skills.get();
    }

    public StringProperty skillsProperty() {
        return Skills;
    }

    public void setSkills(String skills) {
        this.Skills.set(skills);
    }
}
