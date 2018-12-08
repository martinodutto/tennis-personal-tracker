package cloud.martinodutto.tpt.database.mappers;

import cloud.martinodutto.tpt.database.entities.Player;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@Mapper
@Repository
public interface PlayersMapper {

    List<Player> selectAll();

    @Nullable
    Player selectByPk(@Param("playerId") int playerId);

    /**
     * Extracts THE player associated to the current user.
     * The player must be at most (and, actually, even at least) one.
     *
     * @param userId Id of the current user.
     * @return Player associated to the user.
     */
    @Nullable
    Player selectUserPlayer(@Param("userId") int userId);

    List<Player> selectUserKnownPlayers(@Param("userId") int userId);

    int insert(@Nonnull @Param("player") Player player);

    int update(@Nonnull @Param("player") Player player);

    int delete(@Nonnull @Param("player") Player player);
}
