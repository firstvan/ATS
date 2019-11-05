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

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            com.tigra.ats.domain.User appUser =
                    userRepository.findByUsername(username)
                            .orElseThrow(() -> new UsernameNotFoundException("Nem letezo felhasznalo"));
            List grantList = new ArrayList();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(appUser.getRole().getRole());
            grantList.add(grantedAuthority);

            UserDetails user = (UserDetails) new User(appUser.getUsername(), appUser.getPassword(), grantList);
            return user;
    }

}
