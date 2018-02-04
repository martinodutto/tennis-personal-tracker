package com.martinodutto.tpt.database.entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class ActivityAndResult {

    private int activityId;

    private LocalDate activityDate;

    private LocalTime activityTime;

    private LocalTime duration;

    private String activityType;

    private String club;

    private String tournament;

    private String notes;

    private int firstPlayerId;

    private String firstPlayerName;

    private String firstPlayerSurname;

    private int secondPlayerId;

    private String secondPlayerName;

    private String secondPlayerSurname;

    private Integer set1P1;

    private Integer set1P2;

    private Integer set2P1;

    private Integer set2P2;

    private Integer set3P1;

    private Integer set3P2;

    private Integer set4P1;

    private Integer set4P2;

    private Integer set5P1;

    private Integer set5P2;

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

    public int getFirstPlayerId() {
        return firstPlayerId;
    }

    public void setFirstPlayerId(int firstPlayerId) {
        this.firstPlayerId = firstPlayerId;
    }

    public String getFirstPlayerName() {
        return firstPlayerName;
    }

    public void setFirstPlayerName(String firstPlayerName) {
        this.firstPlayerName = firstPlayerName;
    }

    public String getFirstPlayerSurname() {
        return firstPlayerSurname;
    }

    public void setFirstPlayerSurname(String firstPlayerSurname) {
        this.firstPlayerSurname = firstPlayerSurname;
    }

    public int getSecondPlayerId() {
        return secondPlayerId;
    }

    public void setSecondPlayerId(int secondPlayerId) {
        this.secondPlayerId = secondPlayerId;
    }

    public String getSecondPlayerName() {
        return secondPlayerName;
    }

    public void setSecondPlayerName(String secondPlayerName) {
        this.secondPlayerName = secondPlayerName;
    }

    public String getSecondPlayerSurname() {
        return secondPlayerSurname;
    }

    public void setSecondPlayerSurname(String secondPlayerSurname) {
        this.secondPlayerSurname = secondPlayerSurname;
    }

    public Integer getSet1P1() {
        return set1P1;
    }

    public void setSet1P1(Integer set1P1) {
        this.set1P1 = set1P1;
    }

    public Integer getSet1P2() {
        return set1P2;
    }

    public void setSet1P2(Integer set1P2) {
        this.set1P2 = set1P2;
    }

    public Integer getSet2P1() {
        return set2P1;
    }

    public void setSet2P1(Integer set2P1) {
        this.set2P1 = set2P1;
    }

    public Integer getSet2P2() {
        return set2P2;
    }

    public void setSet2P2(Integer set2P2) {
        this.set2P2 = set2P2;
    }

    public Integer getSet3P1() {
        return set3P1;
    }

    public void setSet3P1(Integer set3P1) {
        this.set3P1 = set3P1;
    }

    public Integer getSet3P2() {
        return set3P2;
    }

    public void setSet3P2(Integer set3P2) {
        this.set3P2 = set3P2;
    }

    public Integer getSet4P1() {
        return set4P1;
    }

    public void setSet4P1(Integer set4P1) {
        this.set4P1 = set4P1;
    }

    public Integer getSet4P2() {
        return set4P2;
    }

    public void setSet4P2(Integer set4P2) {
        this.set4P2 = set4P2;
    }

    public Integer getSet5P1() {
        return set5P1;
    }

    public void setSet5P1(Integer set5P1) {
        this.set5P1 = set5P1;
    }

    public Integer getSet5P2() {
        return set5P2;
    }

    public void setSet5P2(Integer set5P2) {
        this.set5P2 = set5P2;
    }

    @Override
    public String toString() {
        return "ActivityAndResult{" +
                "activityId=" + activityId +
                ", activityDate=" + activityDate +
                ", activityTime=" + activityTime +
                ", duration=" + duration +
                ", activityType='" + activityType + '\'' +
                ", club='" + club + '\'' +
                ", tournament='" + tournament + '\'' +
                ", notes='" + notes + '\'' +
                ", firstPlayerId=" + firstPlayerId +
                ", firstPlayerName='" + firstPlayerName + '\'' +
                ", firstPlayerSurname='" + firstPlayerSurname + '\'' +
                ", secondPlayerId=" + secondPlayerId +
                ", secondPlayerName='" + secondPlayerName + '\'' +
                ", secondPlayerSurname='" + secondPlayerSurname + '\'' +
                ", set1P1=" + set1P1 +
                ", set1P2=" + set1P2 +
                ", set2P1=" + set2P1 +
                ", set2P2=" + set2P2 +
                ", set3P1=" + set3P1 +
                ", set3P2=" + set3P2 +
                ", set4P1=" + set4P1 +
                ", set4P2=" + set4P2 +
                ", set5P1=" + set5P1 +
                ", set5P2=" + set5P2 +
                '}';
    }
}
