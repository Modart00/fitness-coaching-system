package com.modart00.fitness_coaching_system.service;

import com.modart00.fitness_coaching_system.dto.request.LoginRequest;
import com.modart00.fitness_coaching_system.dto.response.RegisterResponse;
import com.modart00.fitness_coaching_system.entity.User;
import com.modart00.fitness_coaching_system.entity.VerificationToken;
import com.modart00.fitness_coaching_system.entity.enums.Role;
import com.modart00.fitness_coaching_system.exception.AccountNotVerifiedException;
import com.modart00.fitness_coaching_system.repository.UserRepository;
import com.modart00.fitness_coaching_system.security.JwtService;
import lombok.RequiredArgsConstructor;
import com.modart00.fitness_coaching_system.dto.response.LoginResponse;
import com.modart00.fitness_coaching_system.dto.request.RegisterRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final MailService mailService;



    public RegisterResponse register(RegisterRequest request){
        if (userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        if (userRepository.existsByUserName(request.getUserName())) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ROLE_USER);
        VerificationToken token = mailService.generateVerificationToken();
        token.setUser(user);
        user.setToken(token);
        user.setEnabled(false);
        mailService.sendMail(user.getEmail(),token.getToken());
        userRepository.save(user);

        return new RegisterResponse("Registration successfull. Please verify your email.");
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email veya şifre hatalı"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Email veya şifre hatalı");
        }

        if (!user.isEnabled()){
            throw new AccountNotVerifiedException("Lütfen hesabınızı doğrulayınız");
        }

        String accessToken = jwtService.generateToken(user);
        String refreshToken = UUID.randomUUID().toString();
        user.setRefreshToken(refreshToken);
        user.setRefreshTokenExpiryDate(LocalDateTime.now().plusDays(7));
        userRepository.save(user);

        return new LoginResponse(accessToken,refreshToken);

    }

    public LoginResponse refreshToken(String refreshToken){
        User user = userRepository.findByRefreshToken(refreshToken).orElseThrow(() -> new RuntimeException("Refresh Token bulunamadı."));
        if (user.getRefreshTokenExpiryDate().isBefore(LocalDateTime.now())){throw new RuntimeException("Refresh Token süresi doldu");}
        String accessToken = jwtService.generateToken(user);
        return new LoginResponse(accessToken,refreshToken);
    }

    public void verifyAccount(String token){
    User user = userRepository.findByToken_Token(token).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
    if (user.getToken().getToken().equals(token)){
        user.setEnabled(true);
        userRepository.save(user);
    }

    }


}

