package org.test.testodessa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.test.testodessa.config.JwtUtil;
import org.test.testodessa.model.DTO.AuthenticationResponse;
import org.test.testodessa.model.DTO.LoginRequest;
import org.test.testodessa.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse loginUser(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = JwtUtil.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse registerUser(LoginRequest request) { //Probably need to create another class for this
        String passwordEncode = passwordEncoder.encode(request.getPassword());
        try {
            var user = userRepository.save(request.toUser(passwordEncode));
            var jwtToken = JwtUtil.generateToken(user);
            return AuthenticationResponse.builder().token(jwtToken).message("Success").build();
        } catch (Exception e) {
            return AuthenticationResponse.builder().token(null).message("Username is taken").build();
        }
    }
}
