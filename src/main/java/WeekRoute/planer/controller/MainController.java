package WeekRoute.planer.controller;

import WeekRoute.planer.domain.user.UserPrincipal;
import WeekRoute.planer.domain.user.Coordinate;
import WeekRoute.planer.service.Plan.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String plan_date = now.format(formatter);
        List<Coordinate> route = planService.getRoute(user.getId(), plan_date);

        return "index";
    }
}
