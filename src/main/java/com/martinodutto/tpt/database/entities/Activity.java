package com.martinodutto.tpt.database.entities;

import com.martinodutto.tpt.controllers.entities.ActivityForm;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Activity {

    private int activityId;

    private LocalDate activityDate;

    private LocalTime activityTime;

    private LocalTime duration;

    private int firstPlayerId;

    private int secondPlayerId;

    private String activityType;

    private String club;

    private String tournament;

    private String notes;

    private LocalDateTime creationTimestamp;

    public Activity() {
    }

    public Activity(@Nonnull ActivityForm activityForm) {
        this.activityDate = activityForm.get_activityDate();
        this.activityTime = activityForm.get_activityTime();
        this.duration = activityForm.get_duration();
        this.firstPlayerId = activityForm.get_firstPlayerId();
        this.secondPlayerId = activityForm.get_secondPlayerId();
        this.activityType = activityForm.get_activityType();
        this.club = activityForm.get_club();
        this.tournament = activityForm.get_tournament();
        this.notes = activityForm.get_notes();
        this.creationTimestamp = LocalDateTime.now();
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public LocalDate getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(LocalDate activityDate) {
        this.activityDate = activityDate;
    }

    public LocalTime getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(LocalTime activityTime) {
        this.activityTime = activityTime;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public int getFirstPlayerId() {
        return firstPlayerId;
    }

    public void setFirstPlayerId(int firstPlayerId) {
        this.firstPlayerId = firstPlayerId;
    }

    public int getSecondPlayerId() {
        return secondPlayerId;
    }

    public void setSecondPlayerId(int secondPlayerId) {
        this.secondPlayerId = secondPlayerId;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getTournament() {
        return tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(LocalDateTime creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "activityId=" + activityId +
                ", activityDate=" + activityDate +
                ", activityTime=" + activityTime +
                ", duration=" + duration +
                ", firstPlayerId=" + firstPlayerId +
                ", secondPlayerId=" + secondPlayerId +
                ", activityType='" + activityType + '\'' +
                ", club='" + club + '\'' +
                ", tournament='" + tournament + '\'' +
                ", notes='" + notes + '\'' +
                ", creationTimestamp=" + creationTimestamp +
                '}';
    }
}
