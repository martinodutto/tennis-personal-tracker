package com.martinodutto.tpt.controllers;

import com.martinodutto.tpt.controllers.entities.PlayerForm;
import com.martinodutto.tpt.database.entities.Player;
import com.martinodutto.tpt.exceptions.DuplicateKeyException;
import com.martinodutto.tpt.exceptions.InvalidInputException;
import com.martinodutto.tpt.services.PlayerService;
import com.martinodutto.tpt.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class NewPlayerController {

    private final static Logger LOGGER = LoggerFactory.getLogger(NewPlayerController.class);

    private final PlayerService playerService;

    private final UserService userService;

    @Autowired
    public NewPlayerController(PlayerService playerService, UserService userService) {
        this.playerService = playerService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/player/createPlayer")
    public Player createPlayer(@Valid @RequestBody PlayerForm form, BindingResult bindingResult) throws InvalidInputException, DuplicateKeyException {

        LOGGER.info("Creating new player with following submitted data: {}", form);

        if (bindingResult.hasErrors()) {
            throw new InvalidInputException(bindingResult.getAllErrors());
        }

        Player player = new Player(form, userService.getUserId());
        final int insert = playerService.addPlayer(player);

        LOGGER.info("Successfully inserted {} records", insert);

        return player;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/player/getCurrentUserPlayer")
    public Player getCurrentUserPlayer() {

        LOGGER.info("Finding current user's player");
        final Player currentPlayer = playerService.getCurrentPlayer();

        return currentPlayer;
    }
}
