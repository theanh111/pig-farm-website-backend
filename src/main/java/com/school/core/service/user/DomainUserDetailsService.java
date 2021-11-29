package com.school.core.service.user;

import com.school.core.entity.UserPrinciple;
import com.school.core.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    @Autowired
    private final UserService userService;

    public DomainUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserPrinciple loadUserByUsername(String username) {
        log.debug("Authenticating {}", username);

        if (username != null) {
            return userService.findOneWithAuthoritiesByUsername(username)
                    .map(user -> createSpringSecurityUser(user))
                    .orElseThrow(() -> new UsernameNotFoundException("User name: " + username + " was not found in the database"));
        }
        return null;
    }

    private UserPrinciple createSpringSecurityUser(UserPrinciple user) {
        List<GrantedAuthority> grantedAuthorities = user
                .getAuthorities()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.toString()))
                .collect(Collectors.toList());
        return new UserPrinciple(user.getId(), user.getUsername(), user.getPassword(), grantedAuthorities, user.getStatus(), user.getParentId(), user.getRoleId());
    }
}
