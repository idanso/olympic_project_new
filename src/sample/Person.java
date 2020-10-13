package sample;

public abstract class Person {
    protected String name;
    protected State StateName;

    public Person(String name, State StateName){
        this.name = name;
        this.StateName=StateName;
    }

    public String getName(){
        return name;
    }
    public String getStateName(){
        return StateName.getName();
    }
    public abstract SportTypeAthleteANDReferee getSportType();

}
