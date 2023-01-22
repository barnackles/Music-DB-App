package com.barnackles.musicDbApp.helpermethods;

import com.barnackles.musicDbApp.model.Track;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DurationCalculator {


    public String calculateEditionDuration(List<Track> editionTracklist) {

        int editionDurationInSeconds = editionTracklist.stream()
                .reduce(0, (partialDuration, edition) -> partialDuration + edition.getDurationInSeconds(), Integer::sum);


        return getDurationInMinsAndSecs(editionDurationInSeconds);
    }

    public String calculateTrackDuration(int durationInSeconds) {
        return getDurationInMinsAndSecs(durationInSeconds);
    }

    public String getDurationInMinsAndSecs(int durationInSeconds) {

        int sec = durationInSeconds % 60;
        int min = durationInSeconds / 60;

        return String.format("%d min %d sec", min, sec);
    }


}
