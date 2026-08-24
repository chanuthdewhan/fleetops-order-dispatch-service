package com.fleetops.orderdispatchservice.service.impl;

import com.fleetops.orderdispatchservice.dto.auth.AuthResponse;
import com.fleetops.orderdispatchservice.dto.auth.LoginRequest;
import com.fleetops.orderdispatchservice.dto.auth.RegisterRequest;
import com.fleetops.orderdispatchservice.entity.User;
import com.fleetops.orderdispatchservice.exception.DuplicateResourceException;
import com.fleetops.orderdispatchservice.exception.InvalidCredentialsException;
import com.fleetops.orderdispatchservice.repository.UserRepository;
import com.fleetops.orderdispatchservice.security.JwtService;
import com.fleetops.orderdispatchservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private static final String INVALID_CREDENTIALS_MSG = "Invalid username or password";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        userRepository.findByUsername(request.getUsername()).ifPresent(u -> {
            throw new DuplicateResourceException("Username already taken: " + request.getUsername());
        });

        User user = User.builder()
                .username(request.getUsername())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        User saved = userRepository.save(user);
        log.info("User registered: id={}, role={}", saved.getId(), saved.getRole());

        String token = jwtService.generateToken(saved);
        return AuthResponse.builder()
                .token(token)
                .username(saved.getUsername())
                .role(saved.getRole())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new InvalidCredentialsException(INVALID_CREDENTIALS_MSG));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new InvalidCredentialsException(INVALID_CREDENTIALS_MSG);
        }

        log.info("User logged in: id={}, username={}", user.getId(), user.getUsername());

        String token = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(token)
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }
}