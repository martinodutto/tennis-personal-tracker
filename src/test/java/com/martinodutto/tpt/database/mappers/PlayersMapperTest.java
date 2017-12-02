package com.martinodutto.tpt.database.mappers;

import com.martinodutto.tpt.database.entities.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    public void selectAllWorks() throws Exception {
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
    public void selectByPkWorks() throws Exception {
        final Player player = playersMapper.selectByPk(1); // TODO this is unsafe: we can't be certain this exists
        assertNotNull(player);
        assertNotNull(player.getName());
    }

    @Test
    public void insertWorks() throws Exception {
        Player player = new Player();
        player.setName("Alessia");
        player.setSurname("Nardozzi");
        player.setGender("F");
        assertEquals(1, playersMapper.insert(player));
        LOGGER.info("Inserted new player with id {}", player.getPlayerId());
    }

    @Test
    public void updateWorks() throws Exception {
        final Player player1 = new Player();
        player1.setName("Giacomo");
        player1.setSurname("Vercelli");
        player1.setGender("M");
        playersMapper.insert(player1);
        final Optional<Player> originalPlayer = playersMapper.selectAll().stream().filter(p -> "Giacomo Vercelli".equals(p.getName() + " " + p.getSurname())).findFirst();
        assertTrue(originalPlayer.isPresent());
        final Player player2 = originalPlayer.get();
        player2.setName("Carlo");
        assertEquals(1, playersMapper.update(player2));
    }

    @Test
    public void deleteWorks() throws Exception {
        Player player = playersMapper.selectByPk(1); // TODO this is unsafe: we can't be certain this exists
        assertEquals(1, playersMapper.delete(player));
    }
}