package cloud.martinodutto.tpt.controllers;

import cloud.martinodutto.tpt.controllers.entities.ActivityForm;
import cloud.martinodutto.tpt.database.entities.Activity;
import cloud.martinodutto.tpt.database.entities.Player;
import cloud.martinodutto.tpt.database.entities.Result;
import cloud.martinodutto.tpt.exceptions.EmptyInputException;
import cloud.martinodutto.tpt.exceptions.InvalidInputException;
import cloud.martinodutto.tpt.services.ActivityService;
import cloud.martinodutto.tpt.services.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @RequestMapping(method = RequestMethod.GET, value = "/activity/getKnownPlayers")
    public List<Player> getKnownPlayers() {

        LOGGER.info("Finding current user's known players");
        return playerService.getKnownPlayers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/activity/getUserClubs")
    public List<String> getUserClubs(@RequestParam("firstPlayerId") int firstPlayerId) {

        LOGGER.info("Finding current user's clubs");
        return activityService.getUserClubs(firstPlayerId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/activity/createActivity")
    public void createActivity(@Valid @RequestBody ActivityForm form, BindingResult bindingResult) throws EmptyInputException, InvalidInputException {

        LOGGER.info("Received {} as a form input from the frontend", form);

        if (bindingResult.hasErrors()) {
            throw new InvalidInputException(bindingResult.getAllErrors());
        }

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
