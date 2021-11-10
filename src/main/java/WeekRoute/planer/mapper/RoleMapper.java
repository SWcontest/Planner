package WeekRoute.planer.mapper;

import WeekRoute.planer.domain.member.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface RoleMapper {
    Role getRoleInfo(@Param("role") String role);
}