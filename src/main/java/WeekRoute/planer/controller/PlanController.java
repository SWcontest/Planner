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
     * @param user
     * @param request
     * @return 오늘의 일정페이지
     */
    @GetMapping("pages/todaySchedule")
    public String all_schedule(@AuthenticationPrincipal UserPrincipal user, HttpServletRequest request) {
        String id = user.getId();
        int day = Integer.parseInt(request.getParameter("day"));
        List<Plan> route = planService.getPlanList(id, day);
        return "";
    }

    /**
     *
     * @param user
     * @param request
     * @return 일정 등록
     */
    @GetMapping("pages/add_schedule")
    public String add_schedule(@AuthenticationPrincipal UserPrincipal user, HttpServletRequest request) {

        return "pages/add_schedule/add_schedule.html";
    }

    /**
     *
     * @param user
     * @param request
     * @return 요일별 일정
     */
    @GetMapping("pages/week_schedule")
    public String week_schedule(@AuthenticationPrincipal UserPrincipal user, HttpServletRequest request) {

        return "pages/week_schedule/week_schedule.html";
    }

    /**
     *
     * @param user
     * @param request
     * @return 지난일정 페이지
     */
    @GetMapping("pages/past_schedule")
    public String past_schedule(@AuthenticationPrincipal UserPrincipal user, HttpServletRequest request) {

        return "pages/past_schedule/past_schedule.html";
    }

    /**
     *
     * @param user
     * @param request
     * @return 일정경로 페이지
     */
    @GetMapping("pages/schedule_root")
    public String schedule_root(@AuthenticationPrincipal UserPrincipal user, HttpServletRequest request) {

        return "pages/schedule_root/schedule_root.html";
    }
}
