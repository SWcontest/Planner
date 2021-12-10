package WeekRoute.planer.mapper;

import WeekRoute.planer.domain.user.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface RoleMapper {
    // 권한 RETURN
    Role getRoleInfo(@Param("role") String role);
}