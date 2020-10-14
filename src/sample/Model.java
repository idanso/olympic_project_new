package sample;

import java.util.ArrayList;

public class Model {

    private ArrayList<State> allStates = new ArrayList<>();
    private ArrayList<Stadium> allStadiums = new ArrayList<>();
    private ArrayList<Referee> allReferees = new ArrayList<>();

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
            if (i.getSportType().equals(sportType))
                str.add(i.getName());
        }
        return str;
    }

}
