package WeekRoute.planer.controller;

import WeekRoute.planer.domain.Plan;
import WeekRoute.planer.domain.user.Coordinate;
import WeekRoute.planer.service.Plan.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PlanApiController {

    private final PlanService planService;

    /**
     * 일정조회
     *
     * @param login_id
     * @param plan_date
     * @return
     */
    @GetMapping("/api/v1/plan/{login_id}/{plan_date}")
    @ResponseBody
    public List<Plan> findByDay(@PathVariable String login_id, @PathVariable String plan_date) {
        return planService.getPlanListAll(login_id, plan_date);
    }

    /**
     * 일정등록
     *
     * @param plan
     * @return
     */
    @PostMapping("/api/v1/plan")
    public int save(@RequestBody Plan plan) {
        planService.addPlan(plan);
        System.out.println(">>>>>>>>>>>>>>>>>일정등록 완료>>>>>>>>>>>>>>>>>>>>>>>");

        return plan.getId();
    }

    /**
     * 루트생성
     *
     * @param login_id
     * @param plan_date
     * @return
     * @throws Exception
     */
    @GetMapping("/api/v1/plan_route/{login_id}/{plan_date}")
    @ResponseBody
    public List<Coordinate> findRouteByDay(@PathVariable String login_id, @PathVariable String plan_date) throws Exception {
        return planService.getRoute(login_id, plan_date);
    }

    /**
     * 미정일정추가해서 루트 생성
     * @param login_id
     * @param plan_date
     * @return
     * @throws Exception
     */
    @GetMapping("/api/v1/plan_route_none/{login_id}/{plan_date}/{plan_id}")
    @ResponseBody

    public List<Coordinate> findRouteByDayIncludeNone(@PathVariable String login_id, @PathVariable String plan_date, @PathVariable String plan_id) throws Exception {
        return planService.getRoute(login_id, plan_date, plan_id);
    }
}
