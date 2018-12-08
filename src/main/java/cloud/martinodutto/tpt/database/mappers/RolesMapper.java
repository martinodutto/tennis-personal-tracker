package cloud.martinodutto.tpt.database.mappers;

import cloud.martinodutto.tpt.database.entities.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.List;

@Mapper
@Repository
public interface RolesMapper {

    List<Role> selectAll();

    @Nullable
    Role selectByPk(@Param("roleId") int roleId);
}
