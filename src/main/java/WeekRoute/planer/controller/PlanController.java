package WeekRoute.planer.controller;

import WeekRoute.planer.domain.Plan;
import WeekRoute.planer.domain.user.UserPrincipal;
import WeekRoute.planer.service.Plan.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PlanController {
    @Autowired
    private PlanService planService;

    /**
     *
     * @return 일정추가
     */
    @GetMapping("plan/add_schedule")
    public String add_schedule() {
        return "";
    }

    /**
     *
     * @param user
     * @param request
     * @return 오늘의 일정페이지
     */
    @GetMapping("plan/todaySchedule")
    public String all_schedule(@AuthenticationPrincipal UserPrincipal user, HttpServletRequest request) {
        String id = user.getId();
        int day = Integer.parseInt(request.getParameter("day"));
        List<Plan> route = planService.getPlanList(id, day);
        return "";
    }
}
