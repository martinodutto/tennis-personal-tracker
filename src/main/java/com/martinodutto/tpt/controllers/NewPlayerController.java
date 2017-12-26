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

import javax.annotation.Nonnull;
import java.time.LocalDateTime;

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

        Player player = mapToPlayer(form);
        final int insert = playerService.insert(player);

        LOGGER.info("Successfully inserted {} records", insert);
    }

    private Player mapToPlayer(@Nonnull PlayerForm playerForm) {

        Player player = new Player();
        player.setName(playerForm.get_name());
        player.setSurname(playerForm.get_surname());
        player.setGender(playerForm.get_gender());
        player.setGuest(playerForm.get_guest());
        player.setCreationTimestamp(LocalDateTime.now());

        return player;
    }
}
