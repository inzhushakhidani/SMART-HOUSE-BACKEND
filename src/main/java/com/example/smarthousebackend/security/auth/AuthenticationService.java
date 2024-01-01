package com.example.smarthousebackend.security.auth;

import com.example.smarthousebackend.entity.User;
import com.example.smarthousebackend.enums.Role;
import com.example.smarthousebackend.exceptions.UserAlreadyExistAuthenticationException;
import com.example.smarthousebackend.repository.UserRepository;
import com.example.smarthousebackend.security.config.JwtService;
import com.example.smarthousebackend.security.token.Token;
import com.example.smarthousebackend.security.token.TokenRepository;
import com.example.smarthousebackend.security.token.TokenType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registerUser(RegisterRequest request) {
        return register(request, Role.USER);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public AuthenticationResponse registerAdmin(RegisterRequest request) {
        return register(request, Role.ADMIN);
    }

    @Transactional
    public AuthenticationResponse register(RegisterRequest request, Role role) {
        if(repository.findByEmail(request.getEmail()).isPresent()) throw new UserAlreadyExistAuthenticationException("User is already exists with this email");
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        var savedUser = repository.save(user);
        var jwtAccessToken = jwtService.genarateAccessToken(user);
        var jwtRefreshToken = jwtService.genarateRefreshToken(user);


        saveUserToken(savedUser, jwtAccessToken);
        saveUserToken(savedUser, jwtRefreshToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtAccessToken)
                .refreshToken(jwtRefreshToken)
                .build();
    }

    @Transactional
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword())
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtAccessToken = jwtService.genarateAccessToken(user);
        var jwtRefreshToken = jwtService.genarateRefreshToken(user);


        revokeAllUserTokens(user);
        saveUserToken(user, jwtAccessToken);
        saveUserToken(user, jwtRefreshToken);
        return AuthenticationResponse.builder().accessToken(jwtAccessToken).refreshToken(jwtRefreshToken).build();
    }
    private void revokeAllUserTokens(User user){
        var validUserTokens = tokenRepository.findAllValidTokensByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(t->{
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
}
