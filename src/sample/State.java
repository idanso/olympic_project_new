package sample;

import java.util.ArrayList;

public class State {
    private String name;
    private ArrayList<FinalPlaces> finalPlaceAtTheTournament;
    private ArrayList<Person> RunningTeam;
    private ArrayList<Person> JumpingTeam;
    public enum FinalPlaces {
        FirstPlace3Points,
        SecondPlace2Points,
        ThirdPlace1Points
    }

    public State(String name, ArrayList RunningTeam, ArrayList JumpingTeam){
        this.RunningTeam = new ArrayList<Person>();
        this.JumpingTeam = new  ArrayList<Person>();
        finalPlaceAtTheTournament = new ArrayList<FinalPlaces>();
    }

    public void addPlayer(ArrayList list, Person athlete){
        list.add(athlete);
    }
    public void deletePlayer(ArrayList list, Person athlete){
        for (int i=0 ; i<list.size() ; i++) {
            if(list.get(i).equals(athlete))
            list.remove(i);
        }
    }
    public void updatePlayer(ArrayList list, Person athleteToRemove, Person athleteToAdd){
        for (int i=0 ; i<list.size() ; i++) {
            if(list.get(i).equals(athleteToRemove))
                list.set(i, athleteToAdd);
        }
    }
    public int getTotalPoints(){
        int totalPoints = 0;
        for(int i =0 ; i< finalPlaceAtTheTournament.size() ; i++){
            if(finalPlaceAtTheTournament.get(i).name().equalsIgnoreCase("FirstPlace3Points")) totalPoints =+3;
            else {
                if (finalPlaceAtTheTournament.get(i).name().equalsIgnoreCase("SecondPlace2Points")) totalPoints =+ 2;
                else totalPoints =+ 1;
            }
        }
        return totalPoints;
    }
    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return "State{" +
                "Name='" + name + '\'' +
                ", The running team=" + RunningTeam +
                ", The jumping team=" + JumpingTeam +
                '}';
    }
}
