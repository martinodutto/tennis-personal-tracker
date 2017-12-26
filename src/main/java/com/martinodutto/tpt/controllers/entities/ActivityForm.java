package com.martinodutto.tpt.controllers.entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class ActivityForm {

    private LocalDate _activityDate;

    private String _firstPlayerName;

    private String _secondPlayerName;

    private String _activityType;

    private String _bestOf;

    private String _lastSetTiebreak;

    private String _club;

    private String _tournament;

    private LocalTime _activityTime;

    private LocalTime _duration;

    private String _notes;

    private MatchResult _match;

    public LocalDate get_activityDate() {
        return _activityDate;
    }

    public void set_activityDate(LocalDate _activityDate) {
        this._activityDate = _activityDate;
    }

    public String get_firstPlayerName() {
        return _firstPlayerName;
    }

    public void set_firstPlayerName(String _firstPlayerName) {
        this._firstPlayerName = _firstPlayerName;
    }

    public String get_secondPlayerName() {
        return _secondPlayerName;
    }

    public void set_secondPlayerName(String _secondPlayerName) {
        this._secondPlayerName = _secondPlayerName;
    }

    public String get_activityType() {
        return _activityType;
    }

    public void set_activityType(String _activityType) {
        this._activityType = _activityType;
    }

    public String get_bestOf() {
        return _bestOf;
    }

    public void set_bestOf(String _bestOf) {
        this._bestOf = _bestOf;
    }

    public String get_lastSetTiebreak() {
        return _lastSetTiebreak;
    }

    public void set_lastSetTiebreak(String _lastSetTiebreak) {
        this._lastSetTiebreak = _lastSetTiebreak;
    }

    public String get_club() {
        return _club;
    }

    public void set_club(String _club) {
        this._club = _club;
    }

    public String get_tournament() {
        return _tournament;
    }

    public void set_tournament(String _tournament) {
        this._tournament = _tournament;
    }

    public LocalTime get_activityTime() {
        return _activityTime;
    }

    public void set_activityTime(LocalTime _activityTime) {
        this._activityTime = _activityTime;
    }

    public LocalTime get_duration() {
        return _duration;
    }

    public void set_duration(LocalTime _duration) {
        this._duration = _duration;
    }

    public String get_notes() {
        return _notes;
    }

    public void set_notes(String _notes) {
        this._notes = _notes;
    }

    public MatchResult get_match() {
        return _match;
    }

    public void set_match(MatchResult _match) {
        this._match = _match;
    }

    @Override
    public String toString() {
        return "ActivityForm{" +
                "_activityDate=" + _activityDate +
                ", _firstPlayerName='" + _firstPlayerName + '\'' +
                ", _secondPlayerName='" + _secondPlayerName + '\'' +
                ", _activityType='" + _activityType + '\'' +
                ", _bestOf='" + _bestOf + '\'' +
                ", _lastSetTiebreak='" + _lastSetTiebreak + '\'' +
                ", _club='" + _club + '\'' +
                ", _tournament='" + _tournament + '\'' +
                ", _activityTime=" + _activityTime +
                ", _duration=" + _duration +
                ", _notes='" + _notes + '\'' +
                ", _match=" + _match +
                '}';
    }
}
