package com.martinodutto.tpt.database.entities;

import com.martinodutto.tpt.controllers.entities.ActivityForm;
import com.martinodutto.tpt.controllers.entities.SetResult;

import javax.annotation.Nonnull;
import java.util.List;

public class Result {

    private int activityId;

    private int threeOrFiveSetter;

    private String lastSetTiebreak;

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

    public Result() {
    }

    public Result(@Nonnull ActivityForm activityForm) {
        this.threeOrFiveSetter = activityForm.get_bestOf();
        this.lastSetTiebreak = activityForm.get_lastSetTiebreak();
        final List<SetResult> sets = activityForm.get_match().getSets();
        if (sets != null && sets.size() > 0) {
            SetResult set;
            set = sets.get(0);
            this.set1P1 = set.getFirstPlayerGames();
            this.set1P2 = set.getSecondPlayerGames();
            if (sets.size() > 1) {
                set = sets.get(1);
                this.set2P1 = set.getFirstPlayerGames();
                this.set2P2 = set.getSecondPlayerGames();
                if (sets.size() > 2) {
                    set = sets.get(2);
                    this.set3P1 = set.getFirstPlayerGames();
                    this.set3P2 = set.getSecondPlayerGames();
                    if (sets.size() > 3) {
                        set = sets.get(3);
                        this.set4P1 = set.getFirstPlayerGames();
                        this.set4P2 = set.getSecondPlayerGames();
                        if (sets.size() > 4) {
                            set = sets.get(4);
                            this.set5P1 = set.getFirstPlayerGames();
                            this.set5P2 = set.getSecondPlayerGames();
                        }
                    }
                }
            }
        }

    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public int getThreeOrFiveSetter() {
        return threeOrFiveSetter;
    }

    public void setThreeOrFiveSetter(int threeOrFiveSetter) {
        this.threeOrFiveSetter = threeOrFiveSetter;
    }

    public String getLastSetTiebreak() {
        return lastSetTiebreak;
    }

    public void setLastSetTiebreak(String lastSetTiebreak) {
        this.lastSetTiebreak = lastSetTiebreak;
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
        return "Result{" +
                "activityId=" + activityId +
                ", threeOrFiveSetter=" + threeOrFiveSetter +
                ", lastSetTiebreak='" + lastSetTiebreak + '\'' +
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
