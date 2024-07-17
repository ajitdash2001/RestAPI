package com.app.ems.security;

import com.app.ems.entity.User;
import com.app.ems.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    //Constructor based dependency injection
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        //database interaction
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(
                () -> new UsernameNotFoundException("User doesn't exists by username or email: " + usernameOrEmail)
        );
        //converting the roles to authorities
        Set<GrantedAuthority> authorities =
                user.getRoles().stream().map(r->new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toSet());

        //returning the user object of security framework with email, password and roles in the form of authorities
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }
}
