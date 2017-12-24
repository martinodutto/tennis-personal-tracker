package com.martinodutto.tpt.controllers.entities;

public class SetResult {

    private int firstPlayerGames;

    private int secondPlayerGames;

    public int getFirstPlayerGames() {
        return firstPlayerGames;
    }

    public void setFirstPlayerGames(int firstPlayerGames) {
        this.firstPlayerGames = firstPlayerGames;
    }

    public int getSecondPlayerGames() {
        return secondPlayerGames;
    }

    public void setSecondPlayerGames(int secondPlayerGames) {
        this.secondPlayerGames = secondPlayerGames;
    }

    @Override
    public String toString() {
        return "SetResult{" +
                "firstPlayerGames=" + firstPlayerGames +
                ", secondPlayerGames=" + secondPlayerGames +
                '}';
    }
}
