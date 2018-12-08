package cloud.martinodutto.tpt.controllers;

import cloud.martinodutto.tpt.database.entities.ActivityAndResult;
import cloud.martinodutto.tpt.database.entities.Player;
import cloud.martinodutto.tpt.pagination.ActivitiesAndResultsSortModelEntry;
import cloud.martinodutto.tpt.pagination.PaginatedResponse;
import cloud.martinodutto.tpt.pagination.PagingOptions;
import cloud.martinodutto.tpt.services.ActivityService;
import cloud.martinodutto.tpt.services.PlayerService;
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
}
