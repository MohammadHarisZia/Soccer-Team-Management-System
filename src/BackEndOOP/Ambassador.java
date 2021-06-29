package BackEndOOP;

public class Ambassador {
    private int PersonID;
    private int Contract;
    private int CountryID;

    public Ambassador() {

    }

    public Ambassador(int personID, int contract, int countryID) {
        PersonID = personID;
        Contract = contract;
        CountryID = countryID;
    }

    public Ambassador(Ambassador a) {
        PersonID = a.PersonID;
        Contract = a.Contract;
        CountryID = a.CountryID;
    }

    public int getPersonID() {
        return PersonID;
    }

    public void setPersonID(int personID) {
        PersonID = personID;
    }

    public int getContract() {
        return Contract;
    }

    public void setContract(int contract) {
        Contract = contract;
    }

    public int getCountryID() {
        return CountryID;
    }

    public void setCountryID(int countryID) {
        CountryID = countryID;
    }
}
