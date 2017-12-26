package com.martinodutto.tpt.database.entities;

import com.martinodutto.tpt.controllers.entities.PlayerForm;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;

public class Player {

    private int playerId;

    private String name;

    private String surname;

    private String gender;

    private String guest;

    private LocalDateTime creationTimestamp;

    public Player() {
    }

    public Player(@Nonnull PlayerForm form) {
        this.name = form.get_name();
        this.surname = (form.get_surname());
        this.gender = form.get_gender();
        this.guest = form.get_guest();
        this.creationTimestamp = LocalDateTime.now();
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    public LocalDateTime getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(LocalDateTime creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", guest='" + guest + '\'' +
                ", creationTimestamp=" + creationTimestamp +
                '}';
    }
}
