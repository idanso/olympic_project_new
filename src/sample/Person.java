package sample;

public abstract class Person {
    protected String name;
    protected State StateName;

    public Person(String name, State StateName){
        setName(name);
        setStateName(StateName);
    }

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
