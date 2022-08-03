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

    public User register(SignupRequestDto requestDto) {
        // 패스워드 일치하지 않는다면
        if (!requestDto.getPassword().equals(requestDto.getPasswordConfirm())) {
            return null;
        }

        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        return userRepository.save(new User(requestDto));
    }
}
