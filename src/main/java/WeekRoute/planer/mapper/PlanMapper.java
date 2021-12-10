package WeekRoute.planer.mapper;

import WeekRoute.planer.domain.Plan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface PlanMapper {

    // 다음 ID 값 RETURN
    int getCurrentId();
    // 일정 목록 RETURN
    List<Plan> getPlanList(@Param("loginId") String loginId, @Param("plan_date") String plan_date, @Param("start_time") String start_time, @Param("place") String place);
    // 일정 추가
    void addPlan(@Param("param") Plan param);
    // 날짜 정해지지 않은 일정 RETRUN
    Plan getNonePlanById(@Param("loginId") String loginId, @Param("plan_id") String plan_id);
}