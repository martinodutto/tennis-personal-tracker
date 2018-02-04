package com.martinodutto.tpt.database.mappers;

import com.martinodutto.tpt.database.entities.ActivityAndResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ActivitiesAndResultsMapper {

    List<ActivityAndResult> selectPaginatedByFirstPlayerId(@Param("firstPlayerId") int firstPlayerId,
                                                           @Param("limit") int limit,
                                                           @Param("offset") int offset);
}
