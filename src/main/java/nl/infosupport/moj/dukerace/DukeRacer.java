package nl.infosupport.moj.dukerace;

import java.util.HashMap;
import java.util.Map;

public class DukeRacer {

    int distance = 10;
    int accel[] = {2, 2};
    int spd[] = {2, 4};

    public int[] racers(int distance, int[] accel, int[] spd) {
        if (!(accel.length > 0 && spd.length > 0) || !(accel.length == spd.length)) {
            return null;
        }

        int amountOfRacers = accel.length;

        Map<Integer, Double> racerFinishTimes = new HashMap<Integer, Double>();
        for (int i = 0; i < amountOfRacers; i++) {
            double accelerationTime = (double) spd[i] / accel[i];
            double accelerationDistance = spd[i];
            double distanceToFinish = distance - accelerationDistance;
            double timeToFinish = distanceToFinish / spd[i];
            double fullTimeToFinish = accelerationTime + timeToFinish;

            racerFinishTimes.put(i, fullTimeToFinish);
        }

        int[] scores = new int[amountOfRacers];
        for (int position = 0; position < amountOfRacers; position++) {

            int racerThatWonThisPosition = racerFinishTimes.keySet().iterator().next();
            for (int racerNum : racerFinishTimes.keySet()) {
                if (racerFinishTimes.get(racerNum) < racerFinishTimes.get(racerThatWonThisPosition)) {
                    racerThatWonThisPosition = racerNum;
                }
            }

            scores[racerThatWonThisPosition] = position;
            racerFinishTimes.remove(racerThatWonThisPosition);
        }

        return scores;
    }

}
