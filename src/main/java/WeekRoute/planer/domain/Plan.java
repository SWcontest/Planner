package WeekRoute.planer.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Plan {
    private int id;
    private String login_id;
    private int day; // 1~7 0은 미정, 1~7은 일~토
    private String title;
    private String place;
    private String coordinate;
    private Date plan_date;
    private String start_time;
    private String total_time;
    private boolean activate;
}