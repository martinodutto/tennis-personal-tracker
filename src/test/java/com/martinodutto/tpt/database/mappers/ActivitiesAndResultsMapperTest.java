package com.martinodutto.tpt.database.mappers;

import com.martinodutto.tpt.database.entities.ActivityAndResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class ActivitiesAndResultsMapperTest {

    @Autowired
    private ActivitiesAndResultsMapper activitiesAndResultsMapper;

    @Test
    public void selectPaginatedByFirstPlayerIdWorks() {
        final List<ActivityAndResult> activitiesAndResults = activitiesAndResultsMapper.selectPaginatedByFirstPlayerId(1, 10, 0);
        assertEquals(2, activitiesAndResults.size());

        // to test the mapping and the ordering of the fields
        final ActivityAndResult latestActivity = activitiesAndResults.get(0); // we expect to get the latest in time
        assertNotNull(latestActivity);
        assertEquals(4, latestActivity.getActivityId());
        assertEquals(LocalDate.parse("20171223", DateTimeFormatter.BASIC_ISO_DATE), latestActivity.getActivityDate());
        assertEquals(LocalTime.parse("16:30:00", DateTimeFormatter.ISO_TIME), latestActivity.getActivityTime());
        assertEquals(LocalTime.parse("02:00:00", DateTimeFormatter.ISO_TIME), latestActivity.getDuration());
        assertEquals("Match", latestActivity.getActivityType());
        assertEquals("Country Club", latestActivity.getClub());
        assertEquals("Torneo Belvedere", latestActivity.getTournament());
        assertEquals("Partita intensa", latestActivity.getNotes());
        assertEquals(1, latestActivity.getFirstPlayerId());
        assertEquals("Martino", latestActivity.getFirstPlayerName());
        assertEquals("Dutto", latestActivity.getFirstPlayerSurname());
        assertEquals(2, latestActivity.getSecondPlayerId());
        assertEquals("Carlo", latestActivity.getSecondPlayerName());
        assertEquals("Pantaleo", latestActivity.getSecondPlayerSurname());
        assertEquals(6, (long) latestActivity.getSet1P1());
        assertEquals(3, (long) latestActivity.getSet1P2());
        assertEquals(5, (long) latestActivity.getSet2P1());
        assertEquals(7, (long) latestActivity.getSet2P2());
        assertEquals(2, (long) latestActivity.getSet3P1());
        assertEquals(6, (long) latestActivity.getSet3P2());
        assertEquals(7, (long) latestActivity.getSet4P1());
        assertEquals(6, (long) latestActivity.getSet4P2());
        assertEquals(6, (long) latestActivity.getSet5P1());
        assertEquals(8, (long) latestActivity.getSet5P2());
    }

    @Test
    public void anOutOfBoundsOffsetReturnsNoData() {
        final List<ActivityAndResult> activitiesAndResults = activitiesAndResultsMapper.selectPaginatedByFirstPlayerId(1, 10, 314);
        assertEquals(0, activitiesAndResults.size());
    }

    @Test
    public void aLimitOf0ReturnsNoData() {
        final List<ActivityAndResult> activitiesAndResults = activitiesAndResultsMapper.selectPaginatedByFirstPlayerId(1, 0, 0);
        assertEquals(0, activitiesAndResults.size());
    }

    @Test
    public void aLimitOf1ReturnsOnlyARecord() {
        final List<ActivityAndResult> activitiesAndResults = activitiesAndResultsMapper.selectPaginatedByFirstPlayerId(1, 1, 1);
        assertEquals(1, activitiesAndResults.size());
    }

    @Test
    public void anInexistentPlayerHasNoData() {
        final List<ActivityAndResult> activitiesAndResults = activitiesAndResultsMapper.selectPaginatedByFirstPlayerId(171, 10, 0);
        assertEquals(0, activitiesAndResults.size());
    }
}