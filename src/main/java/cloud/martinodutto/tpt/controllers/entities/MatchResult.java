package cloud.martinodutto.tpt.controllers.entities;

import java.util.List;

public class MatchResult {

    private List<SetResult> sets;

    public List<SetResult> getSets() {
        return sets;
    }

    public void setSets(List<SetResult> sets) {
        this.sets = sets;
    }

    @Override
    public String toString() {
        return "MatchResult{" +
                "sets=" + sets +
                '}';
    }
}
