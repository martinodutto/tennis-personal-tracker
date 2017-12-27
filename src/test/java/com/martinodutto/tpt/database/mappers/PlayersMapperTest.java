package com.martinodutto.tpt.database.mappers;

import com.martinodutto.tpt.database.entities.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class PlayersMapperTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(PlayersMapperTest.class);

    @Autowired
    private PlayersMapper playersMapper;

    @Test
    public void selectAllWorks() {
        final List<Player> players = playersMapper.selectAll();
        LOGGER.info("Successfully selected {} players", players.size());
        assertNotNull(players);
        assertEquals(3, players.size());

        final Optional<Player> firstPlayer = players.stream().filter(p -> "Martino".equals(p.getName())).findFirst();
        assertTrue(firstPlayer.isPresent());
        final Player player = firstPlayer.get();
        assertEquals("Dutto", player.getSurname());
        assertEquals("M", player.getGender());
    }

    @Test
    public void selectByPkWorks() {
        final Optional<Player> martino = playersMapper.selectAll().stream().filter(p -> "Martino".equals(p.getName())).findFirst();
        assertTrue(martino.isPresent());
        final Player martinoAgain = playersMapper.selectByPk(martino.get().getPlayerId());
        assertNotNull(martinoAgain);
        assertEquals("Martino", martinoAgain.getName());
    }

    @Test
    public void selectingAnInexistentPkReturnsNull() {
        assertNull(playersMapper.selectByPk(12));
    }

    @Test
    public void insertWorks() {
        Player player = new Player();
        player.setName("Alessia");
        player.setSurname("Nardozzi");
        player.setGender("F");
        player.setGuest("Y");
        assertEquals(1, playersMapper.insert(player));
        assertNotNull(playersMapper.selectByPk(player.getPlayerId())); // this also lets us verify that the id is attached correctly to the entity
        LOGGER.info("Inserted new player with id {}", player.getPlayerId());
    }

    @Test
    public void updateWorks() {
        final Player playerBefore = new Player();
        playerBefore.setName("Giacomo");
        playerBefore.setSurname("Vercelli");
        playerBefore.setGender("M");
        playerBefore.setGuest("N");
        playersMapper.insert(playerBefore);
        playerBefore.setName("Carlo");
        assertEquals(1, playersMapper.update(playerBefore));
        final Player playerAfter = playersMapper.selectByPk(playerBefore.getPlayerId());
        assertNotNull(playerAfter);
        assertEquals("Carlo", playerAfter.getName());
    }

    @Test
    public void deleteWorks() {
        final Player player = new Player();
        player.setName("Giovanni");
        player.setSurname("Roncato");
        player.setGender("M");
        player.setGuest("Y");
        playersMapper.insert(player);
        int playerId = player.getPlayerId();
        assertEquals(1, playersMapper.delete(player));
        assertEquals(3, playersMapper.selectAll().size());
        assertNull(playersMapper.selectByPk(playerId));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void aViolatedGuestConstraintProducesAnError() {
        final Player player = new Player();
        player.setName("Giovanni");
        player.setSurname("Roncato");
        player.setGender("M");
        player.setGuest("A"); // "A" is not an admitted value
        playersMapper.insert(player);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void aNullGuestConstraintProducesAnError() {
        final Player player = new Player();
        player.setName("Giovanni");
        player.setSurname("Roncato");
        player.setGender("M");
        // we don't expressly set a guest
        playersMapper.insert(player);
    }
}