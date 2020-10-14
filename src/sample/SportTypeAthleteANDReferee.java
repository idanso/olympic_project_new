package sample;

public enum SportTypeAthleteANDReferee {
        Runner,
        Jumper,
        RunnerAndJumper;

        public static String[] getAllSportTypes() {

                SportTypeAthleteANDReferee[] eArray = SportTypeAthleteANDReferee.values();
                String[] str = new String[SportTypeAthleteANDReferee.values().length - 1];
                for (int i = 0; i < SportTypeAthleteANDReferee.values().length - 1; i++) {
                        str[i] = eArray[i].toString();
                }

                return str;
        }
}