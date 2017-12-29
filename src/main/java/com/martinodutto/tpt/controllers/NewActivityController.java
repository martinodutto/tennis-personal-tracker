package com.martinodutto.tpt.controllers;

import com.martinodutto.tpt.controllers.entities.ActivityForm;
import com.martinodutto.tpt.database.entities.Activity;
import com.martinodutto.tpt.database.entities.Player;
import com.martinodutto.tpt.database.entities.Result;
import com.martinodutto.tpt.exceptions.EmptyInputException;
import com.martinodutto.tpt.exceptions.NoDataFoundOnDatabaseException;
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

@RestController
public class NewActivityController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewActivityController.class);

    private final ActivityService activityService;

    private final PlayerService playerService;

    @Autowired
    public NewActivityController(ActivityService activityService, PlayerService playerService) {
        this.activityService = activityService;
        this.playerService = playerService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/activity/getCurrentUserPlayer")
    public Player getCurrentUserPlayer() throws NoDataFoundOnDatabaseException {

        LOGGER.info("Finding current user's player");
        final Player currentPlayer = playerService.getCurrentPlayer();

        if (currentPlayer == null) {
            throw new NoDataFoundOnDatabaseException();
        }

        return currentPlayer;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/activity/getKnownPlayers")
    public List<Player> getKnownPlayers() {

        LOGGER.info("Finding current user's known players");
        return playerService.getKnownPlayers();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/activity/createActivity")
    public void createActivity(@RequestBody ActivityForm form) throws EmptyInputException {

        LOGGER.info("Received {} as a form input from the frontend", form);
        if (form != null) {
            Activity activity = new Activity(form);
            if ("Match".equals(activity.getActivityType())) {
                Result result = new Result(form);
                activityService.addActivityWithResult(activity, result);
            } else {
                activityService.addActivity(activity);
            }
        } else {
            throw new EmptyInputException();
        }
        LOGGER.info("Successfully created a new activity");
    }
}
