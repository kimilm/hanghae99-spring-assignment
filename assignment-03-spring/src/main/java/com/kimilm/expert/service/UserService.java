package com.kimilm.expert.service;

import com.kimilm.expert.model.user.User;
import com.kimilm.expert.model.user.dto.LoginRequestDto;
import com.kimilm.expert.model.user.dto.SignupRequestDto;
import com.kimilm.expert.repository.UserRepository;
import com.kimilm.expert.security.UserDetailsImpl;
import com.kimilm.expert.security.jwt.JwtDecoder;
import com.kimilm.expert.security.jwt.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    public User register(SignupRequestDto requestDto) {
        // 사용자가 존재한다면
        if (userRepository.existsByUsername(requestDto.getUsername())) {
            throw new IllegalArgumentException("이미 존재하는 사용자 아이디 입니다.");
        }
        // 패스워드 일치하지 않는다면
        if (!requestDto.getPassword().equals(requestDto.getPasswordConfirm())) {
            throw new IllegalArgumentException("비밀번호 입력값이 일치하지 않습니다.");
        }

        String password = passwordEncoder.encode(requestDto.getPassword());
        requestDto.setPassword(password);

        return userRepository.save(new User(requestDto));
    }

    @Transactional
    public List<String> login(LoginRequestDto requestDto) {
        User user = userRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("사용자 아이디가 잘못되었습니다."));

        boolean match = passwordEncoder.matches(requestDto.getPassword(), user.getPassword());

        if (!match) {
            throw new IllegalArgumentException("사용자 비밀번호가 잘못되었습니다.");
        }

        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        List<String> tokens = new ArrayList<>();
        // accessToken
        tokens.add(JwtTokenUtils.generateJwtToken(userDetails));
        // 리프레시 토큰이 없거나 만료되었다면 리프레시 토큰 발급
        String refreshToken = user.getRefreshToken();
        if (refreshToken == null || JwtDecoder.isExpired(refreshToken)) {
            refreshToken = JwtTokenUtils.generateRefreshToken(userDetails);

            tokens.add(refreshToken);
            user.setRefreshToken(refreshToken);
        }

        return tokens;
    }
}
