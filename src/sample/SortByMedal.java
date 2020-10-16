package sample;

import java.util.Comparator;

public class SortByMedal implements Comparator<State> {

        public int compare(State a, State b)
        {
            return a.getTotalPoints() - b.getTotalPoints();
        }
}
