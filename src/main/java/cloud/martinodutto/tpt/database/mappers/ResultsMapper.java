package cloud.martinodutto.tpt.database.mappers;

import cloud.martinodutto.tpt.database.entities.Result;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@Mapper
@Repository
public interface ResultsMapper {

    List<Result> selectAll();

    @Nullable
    Result selectByPk(@Param("activityId") int activityId);

    int insert(@Nonnull @Param("result") Result result);

    int update(@Nonnull @Param("result") Result result);

    int delete(@Nonnull @Param("result") Result result);
}
