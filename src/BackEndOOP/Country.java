package BackEndOOP;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Country {
    private IntegerProperty CountryID;
    private StringProperty Region;
    private IntegerProperty TournamentID;
    private StringProperty Country_Name;

    public Country() {

    }

    public Country(int countryID, String region, int tournamentID, String country_Name) {
        CountryID = new SimpleIntegerProperty(countryID);
        Region = new SimpleStringProperty(region);
        TournamentID = new SimpleIntegerProperty(tournamentID);
        Country_Name = new SimpleStringProperty(country_Name);
    }

    public Country(int countryID, String region,String country_Name) {
        CountryID = new SimpleIntegerProperty(countryID);
        Region = new SimpleStringProperty(region);
        Country_Name = new SimpleStringProperty(country_Name);
    }

    public Country(Country c) {
        CountryID = c.CountryID;
        Region = c.Region;
        TournamentID = c.TournamentID;
        Country_Name = c.Country_Name;
    }

    public int getCountryID() {
        return CountryID.get();
    }

    public IntegerProperty countryIDProperty() {
        return CountryID;
    }

    public void setCountryID(int countryID) {
        this.CountryID.set(countryID);
    }

    public String getRegion() {
        return Region.get();
    }

    public StringProperty regionProperty() {
        return Region;
    }

    public void setRegion(String region) {
        this.Region.set(region);
    }

    public int getTournamentID() {
        return TournamentID.get();
    }

    public IntegerProperty tournamentIDProperty() {
        return TournamentID;
    }

    public void setTournamentID(int tournamentID) {
        this.TournamentID.set(tournamentID);
    }

    public String getCountry_Name() {
        return Country_Name.get();
    }

    public StringProperty country_NameProperty() {
        return Country_Name;
    }

    public void setCountry_Name(String country_Name) {
        this.Country_Name.set(country_Name);
    }
}
