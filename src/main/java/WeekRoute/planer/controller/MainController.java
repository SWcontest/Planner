package WeekRoute.planer.controller;

import WeekRoute.planer.domain.Plan;
import WeekRoute.planer.domain.user.User;
import WeekRoute.planer.domain.user.UserPrincipal;
import WeekRoute.planer.service.Plan.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class MainController {
    private final static Logger log = Logger.getGlobal();

    @Autowired
    private PlanService planService;

    @GetMapping("main")
    public String getLoginPage(@AuthenticationPrincipal UserPrincipal user) {
        log.info("user: " + user.getId());
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK);
        List<Plan> planList = planService.getPlanList(user.getId(), day);
        return "index";
    }

}
