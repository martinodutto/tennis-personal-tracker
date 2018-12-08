package cloud.martinodutto.tpt.database.mappers;

import cloud.martinodutto.tpt.database.entities.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@Mapper
@Repository
public interface ActivitiesMapper {

    List<Activity> selectAll();

    @Nullable
    Activity selectByPk(@Param("activityId") int activityId);

    List<String> selectUserClubs(@Param("firstPlayerId") int firstPlayerId);

    int insert(@Nonnull @Param("activity") Activity activity);

    int update(@Nonnull @Param("activity") Activity activity);

    int delete(@Nonnull @Param("activity") Activity activity);
}
