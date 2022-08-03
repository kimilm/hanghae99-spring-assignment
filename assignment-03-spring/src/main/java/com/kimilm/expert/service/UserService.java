package com.kimilm.expert.service;

import com.kimilm.expert.model.user.User;
import com.kimilm.expert.model.user.dto.SignupRequestDto;
import com.kimilm.expert.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        return userRepository.save(new User(requestDto));
    }
}
