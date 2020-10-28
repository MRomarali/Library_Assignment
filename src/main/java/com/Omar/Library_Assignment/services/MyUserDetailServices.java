package com.Omar.Library_Assignment.services;

import com.Omar.Library_Assignment.entities.Borrower;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MyUserDetailServices implements UserDetailsService {
    private final BorrowerService userService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Borrower user = userService.findByUsername(name);
        if(user == null) {
            throw new UsernameNotFoundException("Username " + name + " not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user));
    }


    private Collection<GrantedAuthority> getGrantedAuthorities(Borrower user) {
        return user.getAcl().stream()
                .map(authority -> new SimpleGrantedAuthority("ROLE_" + authority))
                .collect(Collectors.toList());
    }
}
