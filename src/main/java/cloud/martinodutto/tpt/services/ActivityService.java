package cloud.martinodutto.tpt.services;

import cloud.martinodutto.tpt.database.entities.Activity;
import cloud.martinodutto.tpt.database.entities.ActivityAndResult;
import cloud.martinodutto.tpt.database.entities.Result;
import cloud.martinodutto.tpt.database.mappers.ActivitiesAndResultsMapper;
import cloud.martinodutto.tpt.database.mappers.ActivitiesMapper;
import cloud.martinodutto.tpt.database.mappers.ResultsMapper;
import cloud.martinodutto.tpt.pagination.PagingOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.List;

@Service
public class ActivityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityService.class);

    private final ActivitiesMapper activitiesMapper;

    private final ResultsMapper resultsMapper;

    private final ActivitiesAndResultsMapper activitiesAndResultsMapper;

    @Autowired
    public ActivityService(ActivitiesMapper activitiesMapper, ResultsMapper resultsMapper, ActivitiesAndResultsMapper activitiesAndResultsMapper) {
        this.activitiesMapper = activitiesMapper;
        this.resultsMapper = resultsMapper;
        this.activitiesAndResultsMapper = activitiesAndResultsMapper;
    }

    @Transactional
    public void addActivityWithResult(@Nonnull Activity activity, @Nonnull Result result) {
        int activityOutcome = activitiesMapper.insert(activity);
        LOGGER.info("Persisted {} new activities", activityOutcome);
        if (activityOutcome > 0) {
            result.setActivityId(activity.getActivityId()); // the auto-generated activity id must be set in the result
            int resultOutcome = resultsMapper.insert(result);
            LOGGER.info("Persisted {} new results for activity {}", resultOutcome, activity.getActivityId());
        }
    }

    @Transactional
    public void addActivity(@Nonnull Activity activity) {
        int activityOutcome = activitiesMapper.insert(activity);
        LOGGER.info("Persisted {} new activities", activityOutcome);
    }

    @Transactional
    public List<String> getUserClubs(int userPlayerId) {
        return activitiesMapper.selectUserClubs(userPlayerId);
    }

    @Transactional
    public List<ActivityAndResult> getPastActivities(int playerId, PagingOptions pagingOptions) {
        return activitiesAndResultsMapper.selectPaginatedByFirstPlayerId(playerId, pagingOptions);
    }

    @Transactional
    public long countPastActivities(int playerId) {
        return activitiesAndResultsMapper.countByFirstPlayerId(playerId);
    }
}
