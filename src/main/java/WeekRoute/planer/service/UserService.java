package WeekRoute.planer.service;

import WeekRoute.planer.domain.user.UserPrincipal;
import WeekRoute.planer.domain.user.Role;
import WeekRoute.planer.domain.user.User;
import WeekRoute.planer.domain.user.UserRole;
import WeekRoute.planer.mapper.RoleMapper;
import WeekRoute.planer.mapper.UserMapper;
import WeekRoute.planer.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findUserByLoginId(String loginId) {
        return userMapper.findUserByLoginId(loginId);
    }

    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        userMapper.setUserInfo(user);

        Role role = roleMapper.getRoleInfo("ADMIN");

        UserRole userRole = new UserRole();
        userRole.setRoleId(role.getId());
        userRole.setUserId(user.getId());

        userRoleMapper.setUserRoleInfo(userRole);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findUserByLoginId(username);
        return new UserPrincipal(user);
    }

}