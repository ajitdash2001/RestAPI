package com.app.ems.service;

import com.app.ems.dto.LoginDto;
import com.app.ems.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
