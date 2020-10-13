package sample;

public class Athlete extends Person {
    private SportTypeAthleteANDReferee sportType;
    public Athlete(String name, State StateName, SportTypeAthleteANDReferee sportType){
        super(name, StateName);
        setSportType(sportType);
    }

    @Override
    public SportTypeAthleteANDReferee getSportType() {
        return sportType;
    }

    public void setSportType(SportTypeAthleteANDReferee sportType) {
        this.sportType = sportType;
    }

    @Override
    public String toString() {
        return "Athlete{" +
                "name= " + getName() +
                "Sport Type=" + sportType +
                '}';
    }
}
