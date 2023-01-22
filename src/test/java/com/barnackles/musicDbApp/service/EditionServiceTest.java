package com.barnackles.musicDbApp.service;

import com.barnackles.musicDbApp.helpermethods.DurationCalculator;
import com.barnackles.musicDbApp.model.Album;
import com.barnackles.musicDbApp.model.Edition;
import com.barnackles.musicDbApp.model.Performer;
import com.barnackles.musicDbApp.model.Track;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EditionServiceTest {


    @Test
    void calculateEditionDuration() {

        DurationCalculator durationCalculator = new DurationCalculator();


        Performer performer = new Performer();
        performer.setId(1L);
        performer.setProfessionalName("TestProfName");
        performer.setFirstName("TestFName");
        performer.setMiddleName("TestMidName");
        performer.setLastName("TestLastName");


        Album album = new Album();
        album.setId(1L);
        album.setTitle("testTitle");
        album.setReleaseDate(LocalDate.of(1999, 1, 1));
        album.setPerformer(performer);

        Edition testEdition = new Edition();
        testEdition.setId(1L);
        testEdition.setDescription("TestEdition");
        testEdition.setAlbum(album);

        Track testTrack1 = new Track();
        testTrack1.setId(1L);
        testTrack1.setTrackNumber(1);
        testTrack1.setTitle("testTrackOne");
        testTrack1.setDurationInSeconds(257);
        testTrack1.setEdition(testEdition);

        Track testTrack2 = new Track();
        testTrack2.setId(2L);
        testTrack2.setTrackNumber(2);
        testTrack2.setTitle("testTrackTwo");
        testTrack2.setDurationInSeconds(242);
        testTrack2.setEdition(testEdition);

        Track testTrack3 = new Track();
        testTrack3.setId(3L);
        testTrack3.setTrackNumber(3);
        testTrack3.setTitle("testTrackThree");
        testTrack3.setDurationInSeconds(61);
        testTrack3.setEdition(testEdition);


        Track testTrack4 = new Track();
        testTrack4.setId(4L);
        testTrack4.setTrackNumber(4);
        testTrack4.setTitle("testTrackFour");
        testTrack4.setDurationInSeconds(15);
        testTrack4.setEdition(testEdition);

        List<Track> testTrackList = List.of(
                testTrack1,
                testTrack2
        );

        List<Track> secondTestTrackList = List.of(
                testTrack3
        );

        List<Track> thirdTestTrackList = List.of(
                testTrack4
        );

        String duration = durationCalculator.calculateEditionDuration(testTrackList);
        String duration2 = durationCalculator.calculateEditionDuration(secondTestTrackList);
        String duration3 = durationCalculator.calculateEditionDuration(thirdTestTrackList);


        assertEquals("8 min 19 sec", duration);
        assertEquals("1 min 1 sec", duration2);
        assertEquals("0 min 15 sec", duration3);
    }

}