package BackEndOOP;

public class Spectator {
    private int PersonID;
    private int Years;
    private int CountryID;
    private int ReservationID;

    public Spectator() {
    }

    public Spectator(int personID, int years, int countryID, int reservationID) {
        PersonID = personID;
        Years = years;
        CountryID = countryID;
        ReservationID = reservationID;
    }

    public Spectator(Spectator s ) {
        PersonID = s.PersonID;
        Years = s.Years;
        CountryID = s.CountryID;
        ReservationID = s.ReservationID;
    }

    public int getPersonID() {
        return PersonID;
    }

    public void setPersonID(int personID) {
        PersonID = personID;
    }

    public int getYears() {
        return Years;
    }

    public void setYears(int years) {
        Years = years;
    }

    public int getCountryID() {
        return CountryID;
    }

    public void setCountryID(int countryID) {
        CountryID = countryID;
    }

    public int getReservationID() {
        return ReservationID;
    }

    public void setReservationID(int reservationID) {
        ReservationID = reservationID;
    }
}
