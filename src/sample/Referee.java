package sample;

public class Referee extends Person{
    private SportTypeAthleteANDReferee sportType;
    private String stateString; //for the refereeTableView
    public Referee(String name, State StateName, SportTypeAthleteANDReferee sportType){
        super(name, StateName);
        this.stateString = super.StateName.getName();
        setSportType(sportType);
    }

    public String getStateString() { //for the refereeTableView
        return stateString;
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
