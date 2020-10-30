package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Model <T>{

    private static ArrayList<State> allStates = new ArrayList<>();
    private static ArrayList<Stadium> allStadiums = new ArrayList<>();
    private static ArrayList<Referee> allReferees = new ArrayList<>();
    public ObservableList<Athlete> athleteList = FXCollections.observableArrayList();//athlete list for the tableView of the "athlete view" tab
    public ObservableList<Referee> refereeList = FXCollections.observableArrayList();//referee list for the tableView of the "referee view" tab
    public ObservableList<Athlete> tournametAthleteList = FXCollections.observableArrayList();
    public ObservableList<State> tournametStateList = FXCollections.observableArrayList();
    private ArrayList<Tournament> allTournaments = new ArrayList<>();
    private T t;


    public void switchScenes (Button btn) {//switching between the scenes
        Parent root;
        Stage stage;
        stage = (Stage) btn.getScene().getWindow();
        try {
            if (btn.getId().equals("changeTomanagerPage")) {
                root = FXMLLoader.load(getClass().getResource("EditPage.fxml"));
            } else
                root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
    public eDialogMassage addTournament(SportTypeAthleteANDReferee sportTypeOfTournament, Stadium stadium, Referee referee, ArrayList <T> list) {//creating new tournament and adding it to the allTournaments array
        for (Tournament i : allTournaments) {
            boolean isEqualTypeOfTournament = i.getSportTypeOfTournament().equals(sportTypeOfTournament);
            boolean isEqualStadium = i.getStadium().equals(stadium);
            boolean isEqualReferee = i.getReferee().equals(referee);
            if (isEqualTypeOfTournament && isEqualStadium && isEqualReferee) {
                if (t instanceof State) {
                    if (i.getAllTeams().equals(list)) {
                        return eDialogMassage.IN_SYSTEM;
                    }
                } else {
                    if (i.getAllAthletes().equals(list)) {
                        return eDialogMassage.IN_SYSTEM;
                    }
                }
            }
        }
        allTournaments.add(new Tournament<T>(sportTypeOfTournament, stadium, referee, list));
        allTournaments.get(allTournaments.size()-1).getPodiumAndUpdateTheWinners(list);
        return eDialogMassage.SUCCESS;
    }

    public Stadium getStadiumByName(String stadiumName){
        for (Stadium i : allStadiums){
            if (i.getName().equals(stadiumName))
                return i;
        }
        return null;
    }

    public Referee getRefereeByName(String refereeName){
        for (Referee i : allReferees){
            if (i.getName().equals(refereeName))
                return i;
        }
        return null;
    }

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
            if(i.getName().equalsIgnoreCase(stateName))
                return i;
        }
        return null;
    }

    public eDialogMassage addAthlete (String name, String stateName, SportTypeAthleteANDReferee sportType){//creating athlete and adding it the right state
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
                return eDialogMassage.DELETE_SUCCESS;
            }
        }
        return eDialogMassage.FAILED;
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

    public ArrayList<State> getStatesBysportType(SportTypeAthleteANDReferee sportType){
        ArrayList<State> states = new ArrayList<>();
        for (State i : allStates){
            if(sportType.equals(SportTypeAthleteANDReferee.Runner)) {
                if (!i.getAllTheRunners().isEmpty())
                    states.add(i);
            }
            else{
                if (!i.getAllTheJumpers().isEmpty())
                    states.add(i);
            }
        }
        return states;
    }

    public ArrayList<String> getStatesBysportTypeString(SportTypeAthleteANDReferee sportType){
        ArrayList<String> states = new ArrayList<>();
        for (State i : allStates){
            if(sportType.equals(SportTypeAthleteANDReferee.Runner)) {
                if (!i.getAllTheRunners().isEmpty())
                    states.add(i.getName());
            }
            else{
                if (!i.getAllTheJumpers().isEmpty())
                    states.add(i.getName());
            }
        }
        return states;
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

    public ArrayList<State> getPodium (){//returning the three first places of the states for the "medal view" tab
        ArrayList<State> podium = new ArrayList<>();
        if(getAllStatesWithAthletes().size() >= 3){
            Collections.sort(allStates, new SortByMedal());
            int counter = 0,counter2 = 0;
            while(counter < 3) {
                if (!getStateByName(getAllStatesWithAthletes().get(counter2)).getAthletes().isEmpty()) {
                    podium.add(allStates.get(counter2));
                    counter++;
                }
                counter2++;
            }
        }
        return podium;
    }

    public ArrayList<Referee> getAllReferees (){
        return allReferees;
    }
}
