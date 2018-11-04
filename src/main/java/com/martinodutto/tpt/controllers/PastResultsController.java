package com.martinodutto.tpt.controllers;

import com.martinodutto.tpt.database.entities.ActivityAndResult;
import com.martinodutto.tpt.database.entities.Player;
import com.martinodutto.tpt.pagination.ActivitiesAndResultsSortModelEntry;
import com.martinodutto.tpt.pagination.PaginatedResponse;
import com.martinodutto.tpt.pagination.PagingOptions;
import com.martinodutto.tpt.services.ActivityService;
import com.martinodutto.tpt.services.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PastResultsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PastResultsController.class);

    private final ActivityService activityService;

    private final PlayerService playerService;

    @Autowired
    public PastResultsController(ActivityService activityService, PlayerService playerService) {
        this.activityService = activityService;
        this.playerService = playerService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/activitiesAndResults/getPastActivities")
    public PaginatedResponse<ActivityAndResult> getPastActivities(
            @RequestBody() PagingOptions<ActivitiesAndResultsSortModelEntry> pagingOptions
    ) {

        LOGGER.info("Retrieving user activities");

        int currentPlayerId = Optional.ofNullable(playerService.getCurrentPlayer())
                .map(Player::getPlayerId).orElseThrow(() -> new RuntimeException("Current user's player not found. This is unexpected!"));
        final List<ActivityAndResult> pastActivities = activityService.getPastActivities(currentPlayerId, pagingOptions);
        final long totalPastActivities = activityService.countPastActivities(currentPlayerId);

        return new PaginatedResponse<>(pastActivities, totalPastActivities);

    }

    /**
     * Handles the mapping between grid properties and database fields, needed for user column sorting.
     *
     * Please keep fields aligned here after any change to the model/view.
     *
     * @param sortProperty Property name, as received from the view.
     * @return Database field (or fields, concatenated).
     */
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private String mapSortPropertyToDatabaseField(Optional<String> sortProperty) {
        String dbField = null;
        if (sortProperty.isPresent()) {
            switch (sortProperty.get()) {
                case "activityType": {
                    dbField = "ACTIVITY_TYPE";
                    break;
                }
                case "secondPlayer": {
                    dbField = "SECOND_PLAYER_NAME || SECOND_PLAYER_SURNAME";
                    break;
                }
                case "activityDate": {
                    dbField = "ACTIVITY_DATE || ACTIVITY_TIME";
                    break;
                }
                default: {
                    dbField = null; // needed in order to clean input data from potential malicious SQL
                }
            }
        }
        return dbField;
    }
}
