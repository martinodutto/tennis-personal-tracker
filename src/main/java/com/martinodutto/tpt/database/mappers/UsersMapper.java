package com.martinodutto.tpt.database.mappers;

import com.martinodutto.tpt.database.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@Mapper
@Repository
public interface UsersMapper {

    List<User> selectAll();

    @Nullable
    User selectByPk(@Param("userId") int userId);

    @Nullable
    User selectByUsername(@Param("username") String username);

    int insert(@Nonnull @Param("user") User user);

    int update(@Nonnull @Param("user") User user);

    int delete(@Nonnull @Param("user") User user);
}
