package sample;

public class Tournament {
    private SportTypeAthleteANDReferee sportTypeOfTournament;
    private TypeOfTournament typeOfTournament;
    private Stadium stadium;
    private Referee referee;
    private State state1;
    private State state2;
    public enum TypeOfTournament {
        Personal,
        Team
    }
    public Tournament(SportTypeAthleteANDReferee sportTypeOfTournament,  TypeOfTournament typeOfTournament, Stadium stadium, Referee referee, State state1, State state2){
        this.sportTypeOfTournament = sportTypeOfTournament;
        this.typeOfTournament = typeOfTournament;
        this.stadium = stadium;
        this.referee = referee;
        this.state1 = state1;
        this.state2 = state2;
    }


    public SportTypeAthleteANDReferee getSportTypeOfTournament() {
        return sportTypeOfTournament;
    }
    public TypeOfTournament getTypeOfTournament() {
        return typeOfTournament;
    }
    public Stadium getStadium() {
        return stadium;
    }

    public State getState1() {
        return state1;
    }

    public State getState2() {
        return state2;
    }

    public void setReferee(Referee referee) {
        this.referee = referee;
    }

    public void setSportTypeOfTournament(SportTypeAthleteANDReferee sportTypeOfTournament) {
        this.sportTypeOfTournament = sportTypeOfTournament;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public void setState1(State state1) {
        this.state1 = state1;
    }

    public void setState2(State state2) {
        this.state2 = state2;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Tournament))
            return false;
        Tournament temp = (Tournament) other;
        return stadium.equals(temp.stadium) && referee.equals(temp.referee);
    }
}