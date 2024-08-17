package com.devgang.marketduck.auth.service;


import com.devgang.marketduck.domain.user.entity.User;
import com.devgang.marketduck.domain.user.service.UserService;
import com.devgang.marketduck.exception.ServiceLogicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
//            User findUser = userService.findUserByUsername(username);
//            userService.verifyUserStatus(findUser.getUserStatus());
//            log.info("#### CustomUserDetailService loadUserByUsername()");
//            return new UserDetail(findUser);
            return null;
        } catch (ServiceLogicException e) {
            throw e;
        }
    }

    private final class UserDetail extends User implements UserDetails {

        public UserDetail(User user) {
            setUserId(user.getUserId());
            setUsername(user.getUsername());
            setPassword(user.getPassword());
            setUserStatus(user.getUserStatus());
            setLoginType(user.getLoginType());
            setAuthority(user.getAuthority());
            setRoles(user.getRoles());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}