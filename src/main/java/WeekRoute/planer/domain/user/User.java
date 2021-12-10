package WeekRoute.planer.domain.user;

import lombok.Getter;
import lombok.Setter;

/**
 * 유저 DTO
 */
@Getter
@Setter
public class User {
    private int id;
    private String password;
    private String passwordConfirm;
    private String loginId;
    private String userName;
    private int active;
}