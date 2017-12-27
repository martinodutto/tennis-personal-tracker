package com.martinodutto.tpt.database.mappers;

import com.martinodutto.tpt.database.entities.Activity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class ActivitiesMapperTest {

    @Autowired
    private ActivitiesMapper activitiesMapper;

    @Test
    public void selectAllWorks() {
        final List<Activity> activities = activitiesMapper.selectAll();
        assertNotNull(activities);
        assertEquals(4, activities.size());
    }

    @Test
    public void selectByPkWorks() {
        final Activity activity = activitiesMapper.selectByPk(2);
        assertNotNull(activity);

        final LocalDate expectedActivityDate = LocalDate.parse("20171227", DateTimeFormatter.BASIC_ISO_DATE);
        final LocalTime expectedActivityTime = LocalTime.parse("17:00:00", DateTimeFormatter.ISO_TIME);
        final LocalTime expectedDuration = LocalTime.parse("01:00:00", DateTimeFormatter.ISO_TIME);

        assertEquals(expectedActivityDate, activity.getActivityDate());
        assertEquals(expectedActivityTime, activity.getActivityTime());
        assertEquals(expectedDuration, activity.getDuration());
        assertEquals(2, activity.getFirstPlayerId());
        assertEquals(3, activity.getSecondPlayerId());
        assertEquals("Match", activity.getActivityType());
        assertEquals("Tennis Park", activity.getClub());
        assertEquals("-", activity.getTournament());
        assertEquals("Bella partita", activity.getNotes());
    }

    @Test
    public void selectingAnInexistentPkReturnsNull() {
        assertNull(activitiesMapper.selectByPk(6));
    }

    @Test
    public void insertWorks() {
        Activity activity = new Activity();
        activity.setActivityDate(LocalDate.parse("20170512", DateTimeFormatter.BASIC_ISO_DATE));
        activity.setActivityTime(LocalTime.parse("14:00:00", DateTimeFormatter.ISO_TIME));
        activity.setDuration(LocalTime.parse("01:32:28", DateTimeFormatter.ISO_TIME));
        activity.setFirstPlayerId(2);
        activity.setSecondPlayerId(3);
        activity.setActivityType("Match");
        activity.setClub("Country Club de Monaco");
        activity.setTournament("Montecarlo Rolex Masters 2017");
        activity.setNotes("Entertaining match");

        assertEquals(1, activitiesMapper.insert(activity));
        assertNotNull(activitiesMapper.selectByPk(activity.getActivityId())); // this also lets us verify that the id is attached correctly to the entity
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void insertingANullActivityDateProducesAnError() {
        Activity activity = new Activity();
        // we expressly "forget" the activity date
        activity.setActivityTime(LocalTime.parse("14:00:00", DateTimeFormatter.ISO_TIME));
        activity.setDuration(LocalTime.parse("01:32:28", DateTimeFormatter.ISO_TIME));
        activity.setFirstPlayerId(2);
        activity.setSecondPlayerId(3);
        activity.setActivityType("Match");
        activity.setClub("Country Club de Monaco");
        activity.setTournament("Montecarlo Rolex Masters 2017");
        activity.setNotes("Entertaining match");

        activitiesMapper.insert(activity);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void insertingANullActivityTypeProducesAnError() {
        Activity activity = new Activity();
        activity.setActivityDate(LocalDate.parse("20170512", DateTimeFormatter.BASIC_ISO_DATE));
        activity.setActivityTime(LocalTime.parse("14:00:00", DateTimeFormatter.ISO_TIME));
        activity.setDuration(LocalTime.parse("01:32:28", DateTimeFormatter.ISO_TIME));
        activity.setFirstPlayerId(2);
        activity.setSecondPlayerId(3);
        // we expressly "forget" the activity type
        activity.setClub("Country Club de Monaco");
        activity.setTournament("Montecarlo Rolex Masters 2017");
        activity.setNotes("Entertaining match");

        activitiesMapper.insert(activity);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void aViolatedPlayerFkProducesAnError() {
        Activity activity = new Activity();
        activity.setActivityDate(LocalDate.parse("20170703", DateTimeFormatter.BASIC_ISO_DATE));
        activity.setActivityTime(LocalTime.parse("15:00:00", DateTimeFormatter.ISO_TIME));
        activity.setDuration(LocalTime.parse("01:30:00", DateTimeFormatter.ISO_TIME));
        activity.setFirstPlayerId(8);
        activity.setSecondPlayerId(3);
        activity.setActivityType("Match");
        activity.setClub("All England Lawn Tennis and Croquet Club");
        activity.setTournament("Wimbledon 2017");
        activity.setNotes("Great match");

        activitiesMapper.insert(activity);
    }

    @Test
    public void updateWorks() {
        final Activity activity = activitiesMapper.selectByPk(5);
        assertNotNull(activity);
        activity.setActivityType("Training");
        activity.setClub("Caja Magica");
        activity.setTournament("N.A.");
        activity.setNotes("Intense training");

        assertEquals(1, activitiesMapper.update(activity));

        final Activity updatedActivity = activitiesMapper.selectByPk(5);
        assertNotNull(updatedActivity);
        assertEquals("Training", updatedActivity.getActivityType());
        assertEquals("Caja Magica", updatedActivity.getClub());
        assertEquals("N.A.", updatedActivity.getTournament());
        assertEquals("Intense training", updatedActivity.getNotes());
    }

    @Test
    public void updatingAnInexistentPkDoesNothing() {
        final Activity activity = activitiesMapper.selectByPk(2);
        assertNotNull(activity);
        activity.setActivityId(34); // inexistent
        assertEquals(0, activitiesMapper.update(activity));
        assertNull(activitiesMapper.selectByPk(34));
    }

    @Test
    public void deleteWorks() {
        final Activity activity = activitiesMapper.selectByPk(3);
        assertNotNull(activity);
        assertEquals(1, activitiesMapper.delete(activity));
        assertEquals(3, activitiesMapper.selectAll().size());
        assertNull(activitiesMapper.selectByPk(3));
    }
}