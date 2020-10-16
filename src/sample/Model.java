package sample;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Model {

    public Referee getAllReferees;
    private ArrayList<State> allStates = new ArrayList<>();
    private ArrayList<Stadium> allStadiums = new ArrayList<>();
    private ArrayList<Referee> allReferees = new ArrayList<>();
    //need to add Array of tournaments

    public eDialogMassage addstadium(String name, String location, int numberOfSeats){
        for (Stadium i : allStadiums){
            if(i.equals(name))
                return eDialogMassage.IN_SYSTEM;
        }
            allStadiums.add(new Stadium(name,location,numberOfSeats));
        return eDialogMassage.SUCCESS;
    }

    public eDialogMassage addReferee (String name, String stateName, SportTypeAthleteANDReferee sportType){
        for (Referee i : allReferees){
            if(i.equals(name))
                return eDialogMassage.IN_SYSTEM;
        }
        State state = stateExistCheck(stateName);
        if (state != null){
            if(!allReferees.add(new Referee(name,state,sportType)))
                return eDialogMassage.FAILED;
        }
        else {
            allStates.add(new State(stateName));
            if(!allReferees.add(new Referee(name,allStates.get(allStates.size() -1),sportType)))
                return eDialogMassage.FAILED;
        }
        return eDialogMassage.SUCCESS;
    }

    public State stateExistCheck (String stateName ){
        for (State i : allStates){
            if(i.getName().equals(stateName))
                return i;
        }
        return null;
    }

    public eDialogMassage addAthlete (String name, String stateName, SportTypeAthleteANDReferee sportType){

        State state = stateExistCheck(stateName);
        if(state == null){
            allStates.add(new State(stateName));
            state = allStates.get(allStates.size()-1);
        }
        else{
            for( State i : allStates){
                if(i.getAthleteByName(name) !=null)
                    return eDialogMassage.IN_SYSTEM;
            }
        }
        state.addPlayer(new Athlete(name,state,sportType));
        return eDialogMassage.SUCCESS;
    }

    public eDialogMassage deleteAthlete(String name) {
        for (State i : allStates) {
            if (i.getAthleteByName(name) != null) {
                i.deletePlayer(name);
            }
        }
        return eDialogMassage.SUCCESS;
    }
    public ArrayList<String> getAllStateString (){
        ArrayList<String> str = new ArrayList<>();
        for (State i : allStates){
            str.add(i.getName());
        }
        return str;
    }

    public ArrayList<String> getAllStatesWithAthletes (){
        ArrayList<String> str = new ArrayList<>();
        for (State i : allStates){
            if(!i.getAthletes().isEmpty())
                str.add(i.getName());
        }
        return str;
    }

    public ArrayList<String> getAllRefereesString (){
        ArrayList<String> str = new ArrayList<>();
        for (Referee i : allReferees){
            str.add(i.getName());
        }
        return str;
    }
    public ArrayList<String> getRefereesBySportTypeString (SportTypeAthleteANDReferee sportType){
        ArrayList<String> str = new ArrayList<>();
        for (Referee i : allReferees){
            if (i.getSportType().equals(sportType) || i.getSportType().equals(SportTypeAthleteANDReferee.RunnerAndJumper))
                str.add(i.getName());
        }
        return str;
    }
    public ArrayList<String> getStadiumString(){
        ArrayList<String> str = new ArrayList<>();
        for (Stadium i : allStadiums){
                str.add(i.getName());
        }
        return str;
    }
    public ArrayList<String> getAthletesByCountryAndSportType (String country,SportTypeAthleteANDReferee sportType){
        ArrayList<String> str = new ArrayList<>();
        ArrayList<Athlete> athletes = new ArrayList<>();
        State state = getStateByName(country);
        if (sportType.equals(SportTypeAthleteANDReferee.Jumper))
            athletes = state.getAllTheJumpers();
        else if(sportType.equals(SportTypeAthleteANDReferee.Runner))
            athletes = state.getAllTheRunners();
        for(Athlete i : athletes){
            str.add(i.getName());
        }
        return str;
    }

    public State getStateByName (String name){
        for (State i : allStates)
            if (i.getName().equals(name))
                return i;
        return null;

    }

    public ArrayList<State> getPodium (){
        ArrayList<State> podium = new ArrayList<>();
        if(getAllStatesWithAthletes().size() >= 3){
            Collections.sort(allStates, new SortByMedal());
            podium = (ArrayList)getAllStatesWithAthletes().subList(0,2);
        } else return null;
        return podium;
    }

    public ArrayList<Referee> getAllReferees (){
        return allReferees;
    }
}
