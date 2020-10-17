package sample;

public class Athlete extends Person {
    private SportTypeAthleteANDReferee sportType;
    public Athlete(String name, State StateName, SportTypeAthleteANDReferee sportType){
        super(name, StateName);
        setSportType(sportType);
    }

    public void addMedal(int place) {//מוסיף מדלייה לאוסף של המדינה
        this.getState().addMedal(place);
    }

    @Override
    public SportTypeAthleteANDReferee getSportType() {
        return sportType;
    }

    public void setSportType(SportTypeAthleteANDReferee sportType) {
        this.sportType = sportType;
    }
    public State getState(){
        return StateName;
    }

    @Override
    public String toString() {
        return "Athlete{" +
                "name= " + getName() +
                "Sport Type=" + sportType +
                '}';
    }
}
