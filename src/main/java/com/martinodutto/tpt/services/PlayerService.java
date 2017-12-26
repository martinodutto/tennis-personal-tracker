package com.martinodutto.tpt.services;

import com.martinodutto.tpt.database.entities.Player;
import com.martinodutto.tpt.database.mappers.PlayersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerService {

    private final PlayersMapper playersMapper;

    @Autowired
    public PlayerService(PlayersMapper playersMapper) {
        this.playersMapper = playersMapper;
    }

    @Transactional
    public int insert(Player player) {
        return playersMapper.insert(player);
    }
}
