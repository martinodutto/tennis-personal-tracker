package com.martinodutto.tpt.controllers;

import com.martinodutto.tpt.controllers.entities.PlayerForm;
import com.martinodutto.tpt.database.entities.Player;
import com.martinodutto.tpt.services.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewPlayerController {

    private final static Logger LOGGER = LoggerFactory.getLogger(NewPlayerController.class);

    private final PlayerService playerService;

    @Autowired
    public NewPlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/player/createPlayer")
    public void createPlayer(@RequestBody PlayerForm form) {

        LOGGER.info("Creating new player with following submitted data: {}", form);

        Player player = new Player(form);
        final int insert = playerService.insert(player);

        LOGGER.info("Successfully inserted {} records", insert);
    }
}
