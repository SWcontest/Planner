package WeekRoute.planer.controller;

import WeekRoute.planer.domain.user.User;
import WeekRoute.planer.domain.user.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.logging.Logger;

@Controller
public class MainController {
    private final static Logger log = Logger.getGlobal();

    @GetMapping("main")
    public String getLoginPage(@AuthenticationPrincipal UserPrincipal user) {
        log.info("user: " + user.getId());
        return "index";
    }
}
