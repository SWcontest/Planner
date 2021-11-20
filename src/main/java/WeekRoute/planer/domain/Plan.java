package WeekRoute.planer.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Plan {
    private int id;
    private String login_id;
    private int day; // 0~7 0은 미정, 1~7은 일~토
    private String title;
    private String contents;
    private String place;
    private Date start_time;
    private Date end_time;
    private int total_time;
    private boolean activate;
}