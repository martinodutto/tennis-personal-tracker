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

}