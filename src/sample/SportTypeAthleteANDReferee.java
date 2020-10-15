package sample;

import java.util.ArrayList;

public enum SportTypeAthleteANDReferee {
        Runner,
        Jumper,
        RunnerAndJumper;

        public static SportTypeAthleteANDReferee[] getAllSportTypes() {

                SportTypeAthleteANDReferee eArray[] = new SportTypeAthleteANDReferee[SportTypeAthleteANDReferee.values().length-1];

                for (int i = 0; i < SportTypeAthleteANDReferee.values().length - 1; i++) {
                        eArray[i] = SportTypeAthleteANDReferee.values()[i];
                }

                return eArray;
        }
}