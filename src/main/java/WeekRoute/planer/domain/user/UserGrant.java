package WeekRoute.planer.domain.user;

import org.springframework.security.core.GrantedAuthority;

/**
 * 권한 RETURN
 */
public class UserGrant implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return "ADMIN";
    }
}