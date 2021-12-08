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
    List<Plan> getPlanList(@Param("loginId") String loginId, @Param("plan_date") String plan_date, @Param("start_time") String start_time, @Param("place") String place);
    Plan addPlan(@Param("param") Plan param);

}