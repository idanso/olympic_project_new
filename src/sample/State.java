package sample;

import java.util.ArrayList;
import java.util.Comparator;

public class State{
    private String name;
    private ArrayList<Integer> finalPlaceAtTheTournament;
    private ArrayList<Athlete> athletes;

    public State (String name){
        setName(name);
        finalPlaceAtTheTournament = new ArrayList<Integer>();
        athletes = new ArrayList<>();
    }
    public State(String name, ArrayList athlets){
        setName(name);
        setAthletes(athlets);
        finalPlaceAtTheTournament = new ArrayList<Integer>();
    }

    public ArrayList<Athlete> getAthletes() {
        return athletes;
    }

    public ArrayList getAllTheRunners(){
        ArrayList <Athlete> allTheRunners = new ArrayList<Athlete>();
        for(int i =0 ; i< athletes.size() ; i++) {
            if(athletes.get(i).getSportType().equals(SportTypeAthleteANDReferee.Runner) || athletes.get(i).getSportType().equals(SportTypeAthleteANDReferee.RunnerAndJumper))
                allTheRunners.add(athletes.get(i));
        }
        return allTheRunners;
    }
    public ArrayList getAllTheJumpers(){
        ArrayList <Athlete> allTheJumpers = new ArrayList<>();
        for(int i =0 ; i< athletes.size() ; i++) {
            if(athletes.get(i).getSportType().equals(SportTypeAthleteANDReferee.Jumper) || athletes.get(i).getSportType().equals(SportTypeAthleteANDReferee.RunnerAndJumper))
                allTheJumpers.add(athletes.get(i));
        }
        return allTheJumpers;
    }

    public void addMedal(int place) {//מוסיף מדלייה לאוסף של המדינה
        this.finalPlaceAtTheTournament.add(place);
    }

    public void addPlayer(Athlete athlete){
        athletes.add(athlete);
    }
    public void deletePlayer(String name){
        for (int i=0 ; i<athletes.size() ; i++) {
            if(athletes.get(i).getName().equalsIgnoreCase(name))
            athletes.remove(i);
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
            if(finalPlaceAtTheTournament.get(i) == 1)
                totalPoints += 3;
            else if (finalPlaceAtTheTournament.get(i) == 2)
                    totalPoints += 2;
            else totalPoints += 1;
            }
        return totalPoints;
    }

    public String getName(){
        return name;
    }

    public Athlete getAthleteByName(String name){
        for(Athlete i : athletes){
            if(i.getName().equalsIgnoreCase(name))
                return i;

        }
        return null;
    }

    public void setAthletes(ArrayList<Athlete> athletes) {
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
                ", The running team=" + getAllTheRunners() +
                ", The jumping team=" + getAllTheJumpers() +
                '}';
    }

}
