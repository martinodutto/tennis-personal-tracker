package com.martinodutto.tpt.services;

import com.martinodutto.tpt.database.entities.Player;
import com.martinodutto.tpt.database.mappers.PlayersMapper;
import com.martinodutto.tpt.exceptions.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.List;

@Service
public class PlayerService {

    private final PlayersMapper playersMapper;

    private final CurrentUserHelper currentUserHelper;

    @Autowired
    public PlayerService(PlayersMapper playersMapper, CurrentUserHelper currentUserHelper) {
        this.playersMapper = playersMapper;
        this.currentUserHelper = currentUserHelper;
    }

    @Transactional
    public int addPlayer(@Nonnull Player player) throws DuplicateKeyException {
        try {
            return playersMapper.insert(player);
        } catch (org.springframework.dao.DuplicateKeyException d) {
            // makes this a checked exception, with an HTTP response code
            throw new DuplicateKeyException(d);
        }
    }

    @Transactional
    public Player getCurrentPlayer() {
        return playersMapper.selectUserPlayer(currentUserHelper.getUserId());
    }

    @Transactional
    public List<Player> getKnownPlayers() {
        final List<Player> players = playersMapper.selectUserKnownPlayers(currentUserHelper.getUserId());
        prependEmptyPlayer(players);

        return players;
    }

    /**
     * Prepends an empty player to the passed list. An empty player is needed to let the dropdowns have a default.
     *
     * @param players List of players to which prepend the empty player.
     */
    private void prependEmptyPlayer(@Nonnull List<Player> players) {
        Player emptyPlayer = new Player();
        emptyPlayer.setPlayerId(-1); // inexistent and invalid player id
        emptyPlayer.setName("");
        emptyPlayer.setSurname("");
        players.add(0, emptyPlayer);
    }
}
