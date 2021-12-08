package WeekRoute.planer;

import WeekRoute.planer.domain.Plan;
import WeekRoute.planer.service.Plan.PlanService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanServiceTest {

    @Autowired
    PlanService planService;

    @Test
    public void load_plan() {
        String login_id = "root";
        String plan_date = "2021-12-08";

        List<Plan> plan_list = planService.getPlanList(login_id, plan_date);

        assertThat(plan_list.get(0).getLogin_id()).isEqualTo("root");
    }
}
