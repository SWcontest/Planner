package WeekRoute.planer.controller;

import WeekRoute.planer.domain.Plan;
import WeekRoute.planer.service.Plan.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PlanApiController {

    private final PlanService planService;

    // 일정 조회
    @GetMapping("/api/v1/plan/{login_id}/{day}")
    public List<Plan> findByDay(@PathVariable String login_id, @PathVariable int day) {
        return planService.getPlanList(login_id, day);
    }

    // 일정 등록
    @PostMapping("/api/v1/plan")
    public int save(@RequestBody Plan plan) {
        planService.addPlan(plan);
        System.out.println(">>>>>>>>>>>>>>>>>일정등록 완료>>>>>>>>>>>>>>>>>>>>>>>");

        return plan.getId();
    }
}
