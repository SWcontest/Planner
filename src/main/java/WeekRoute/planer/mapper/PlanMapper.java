package WeekRoute.planer.mapper;

import WeekRoute.planer.domain.Plan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface PlanMapper {

    int getCurrentId();
    List<Plan> getPlanList(@Param("loginId") String loginId, @Param("day") int day, @Param("time") int time, @Param("place") String place);
    Plan addPlan(@Param("param") Plan param);

}