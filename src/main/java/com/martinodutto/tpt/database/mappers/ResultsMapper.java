package com.martinodutto.tpt.database.mappers;

import com.martinodutto.tpt.database.entities.Result;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.List;

@Mapper
@Repository
public interface ResultsMapper {

    List<Result> selectAll();

    @Nullable
    Result selectByPk(@Param("activityId") int activityId);

    int insert(@Param("result") Result result);

    int update(@Param("result") Result result);

    int delete(@Param("result") Result result);
}
