package com.martinodutto.tpt.controllers.entities;

import java.time.LocalDate;

public class ActivityForm {

    private LocalDate _activityDate;

    private String _firstPlayerName;

    private String _secondPlayerName;

    private String _club;

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

    public String get_club() {
        return _club;
    }

    public void set_club(String _club) {
        this._club = _club;
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
                ", _club='" + _club + '\'' +
                ", _match=" + _match +
                '}';
    }
}
