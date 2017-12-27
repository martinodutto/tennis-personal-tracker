package com.martinodutto.tpt.database.mappers;

import com.martinodutto.tpt.database.entities.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.List;

@Mapper
@Repository
public interface ActivitiesMapper {

    List<Activity> selectAll();

    @Nullable
    Activity selectByPk(@Param("activityId") int activityId);

    int insert(@Param("activity") Activity activity);

    int update(@Param("activity") Activity activity);

    int delete(@Param("activity") Activity activity);
}
