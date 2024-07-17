package com.app.ems.service.impl;

import com.app.ems.dto.LoginDto;
import com.app.ems.dto.RegisterDto;
import com.app.ems.entity.Role;
import com.app.ems.entity.User;
import com.app.ems.exception.EmployeeAPIException;
import com.app.ems.repository.RoleRepository;
import com.app.ems.repository.UserRepository;
import com.app.ems.security.JwtTokenProvider;
import com.app.ems.service.AuthService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           JwtTokenProvider jwtTokenProvider,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {
        //check username
        if(userRepository.existsByUsername(registerDto.getUsername()))
            throw new EmployeeAPIException(HttpStatus.BAD_REQUEST,"Username already exists");

        //check email
        if(userRepository.existsByEmail(registerDto.getEmail()))
            throw new EmployeeAPIException(HttpStatus.BAD_REQUEST,"Email already exists");

        //set the user object
        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        //set the role from role entity
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByRole("ROLE_USER").get();
        roles.add(role);
        user.setRoles(roles);

        //user is registered in the database
        userRepository.save(user);

        return "User registered successfully";
    }
}
