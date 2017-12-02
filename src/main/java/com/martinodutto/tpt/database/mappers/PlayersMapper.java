package com.martinodutto.tpt.database.mappers;

import com.martinodutto.tpt.database.entities.Player;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PlayersMapper {

    List<Player> selectAll();

    Player selectByPk(@Param("playerId") int playerId);

    int insert(@Param("player") Player player);

    int update(@Param("player") Player player);

    int delete(@Param("player") Player player);
}
