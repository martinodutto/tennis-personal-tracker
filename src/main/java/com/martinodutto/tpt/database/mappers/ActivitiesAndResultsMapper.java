package com.martinodutto.tpt.database.mappers;

import com.martinodutto.tpt.database.entities.ActivityAndResult;
import com.martinodutto.tpt.pagination.PagingOptions;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ActivitiesAndResultsMapper {

    List<ActivityAndResult> selectPaginatedByFirstPlayerId(@Param("firstPlayerId") int firstPlayerId,
                                                           @Param("paging") PagingOptions pagingOptions);

    long countByFirstPlayerId(@Param("firstPlayerId") int firstPlayerId);
}
