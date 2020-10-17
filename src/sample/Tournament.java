package sample;

import java.util.ArrayList;
import java.util.Random;

public class Tournament <T>{
    private T t;//הטייפ שאנחנו מקבלים
    private ArrayList<State> allTeams;//רשימת המדינות שמתחרות בתחרות אם היא תחרות נבחרות
    private ArrayList<Athlete> allAthletes;//רשימת כל הספורטאים שמתחרים אם התחרות היא אישית
    private SportTypeAthleteANDReferee sportTypeOfTournament;
    private Stadium stadium;
    private Referee referee;

    public Tournament(SportTypeAthleteANDReferee sportTypeOfTournament, Stadium stadium, Referee referee, ArrayList<T> list){
        this.sportTypeOfTournament = sportTypeOfTournament;
        this.stadium = stadium;
        this.referee = referee;
        setTypeOfTournament(list);
    }

    public void getPodiumAndUpdateTheWinners (ArrayList <T> list){ //מחזיר מערך של 3 המקומות הראשונים מהתחרות, כשהראשון הוא מקום ראשון
        ArrayList <T> copyArray = new ArrayList<T>(list);//העתק של המערך שמקבלים
        Random randomNumber = new Random();
        ArrayList <T> podiumArray = new ArrayList<T>();
        for (int i=1 ; i<=3 ; i++) {
            int number = randomNumber.nextInt(copyArray.size());
            podiumArray.add(list.get(number));
            ((State) podiumArray.get(i - 1)).addMedal(i);
            copyArray.remove(number);//מוחק את מי שנבחר בהגרלה מהמערך הרזרבי שיצרתי בהתחלה ןלא מהמקורי
        }
    }
    public void setTypeOfTournament(ArrayList<T> list){
        if(t instanceof State)  allTeams = new ArrayList<>();
        else allAthletes = new ArrayList<>();
    }

    public SportTypeAthleteANDReferee getSportTypeOfTournament() {
        return sportTypeOfTournament;
    }
    public Stadium getStadium() {
        return stadium;
    }
    public Referee getReferee(){
        return referee;
    }
    public ArrayList<State> getAllTeams(){
        return allTeams;
    }
    public ArrayList<Athlete> getAllAthletes(){
        return allAthletes;
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


    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Tournament))
            return false;
        Tournament temp = (Tournament) other;
        return stadium.equals(temp.stadium) && referee.equals(temp.referee);
    }
}