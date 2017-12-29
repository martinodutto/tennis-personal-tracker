package com.martinodutto.tpt.services;

import com.martinodutto.tpt.database.entities.Activity;
import com.martinodutto.tpt.database.entities.Result;
import com.martinodutto.tpt.database.mappers.ActivitiesMapper;
import com.martinodutto.tpt.database.mappers.ResultsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;

@Service
public class ActivityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityService.class);

    private final ActivitiesMapper activitiesMapper;

    private final ResultsMapper resultsMapper;

    @Autowired
    public ActivityService(ActivitiesMapper activitiesMapper, ResultsMapper resultsMapper) {
        this.activitiesMapper = activitiesMapper;
        this.resultsMapper = resultsMapper;
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
}
