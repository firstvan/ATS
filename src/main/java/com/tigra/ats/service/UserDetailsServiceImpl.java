package com.tigra.ats.service;

import com.tigra.ats.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LoginAttemptService loginAttemptService;


    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("blocked");
        }
        try {
            com.tigra.ats.domain.User user = userRepository.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("No user found with username: " + username);
            } else {
                List grantList = new ArrayList();
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole().getRole());
                grantList.add(grantedAuthority);

                return (UserDetails) new User(user.getUsername(), user.getPassword(), grantList);
            }
        }catch (final Exception e)
        {
            throw new UsernameNotFoundException("No user found with username: " + username);

        }

    }
}
