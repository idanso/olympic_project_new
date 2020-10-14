package sample;

import java.util.ArrayList;

public class State {
    private String name;
    private ArrayList<Integer> finalPlaceAtTheTournament;
    private ArrayList<Person> athletes;

    public State (String name){
        setName(name);
        finalPlaceAtTheTournament = new ArrayList<Integer>();
    }
    public State(String name, ArrayList athlets){
        setName(name);
        setAthletes(athlets);
        finalPlaceAtTheTournament = new ArrayList<Integer>();
    }

    public ArrayList getAllTheRunners(ArrayList <Person> athletes){
        ArrayList <Person> allTheRunners = new ArrayList<Person>();
        for(int i =0 ; i< athletes.size() ; i++) {
            if(athletes.get(i).getSportType().equals("Runner") || athletes.get(i).getSportType().equals("RunnerANDJumper")) allTheRunners.add(athletes.get(i));
        }
        return allTheRunners;
    }
    public ArrayList getAllTheJumpers(ArrayList <Person> athletes){
        ArrayList <Person> allTheJumpers = new ArrayList<Person>();
        for(int i =0 ; i< athletes.size() ; i++) {
            if(athletes.get(i).getSportType().equals("Jumper") || athletes.get(i).getSportType().equals("RunnerANDJumper")) allTheJumpers.add(athletes.get(i));
        }
        return allTheJumpers;
    }

    public void addMedal(int place) {//מוסיף מדלייה לאוסף של המדינה
        this.finalPlaceAtTheTournament.add(place);
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
            if(finalPlaceAtTheTournament.get(i) == 1) totalPoints =+3;
            else {
                if (finalPlaceAtTheTournament.get(i) == 2) totalPoints =+ 2;
                else totalPoints =+ 1;
            }
        }
        return totalPoints;
    }
    public String getName(){
        return name;
    }

    public void setAthletes(ArrayList<Person> athletes) {
        this.athletes = athletes;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getFinalPlaceAtTheTournament() {
        return finalPlaceAtTheTournament;
    }



    @Override
    public String toString() {
        return "State{" +
                "Name='" + name + '\'' +
                ", The running team=" + getAllTheRunners(athletes) +
                ", The jumping team=" + getAllTheJumpers(athletes) +
                '}';
    }
}
