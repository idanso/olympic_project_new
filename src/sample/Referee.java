package sample;

public class Referee extends Person{
    private SportTypeAthleteANDReferee sportType;
    public Referee(String name, State StateName, SportTypeAthleteANDReferee sportType){
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
        return "Referee{" +
                "name= " + getName()+
                " Sport Type= " + sportType +
                '}';
    }
}
