package sample;

public abstract class Person {
    protected String name;
    protected State StateName;
    private String stateString; //for the refereeTableView

    public Person(String name, State StateName){
        setName(name);
        setStateName(StateName);
        this.stateString = this.StateName.getName();
    }

public String getStateString(){ return stateString;}
    public String getName(){
        return name;
    }
    public String getStateName(){
        return StateName.getName();
    }
    public abstract SportTypeAthleteANDReferee getSportType();

    public void setName(String name) {
        this.name = name;
    }

    public void setStateName(State stateName) {
        StateName = stateName;
    }
}
