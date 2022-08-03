package com.kimilm.expert.service;

import com.kimilm.expert.model.user.User;
import com.kimilm.expert.model.user.dto.SignupRequestDto;
import com.kimilm.expert.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User register(SignupRequestDto requestDto) {
        // 패스워드 일치하지 않는다면
        if (!requestDto.getPassword().equals(requestDto.getPasswordConfirm())) {
            return null;
        }

        return userRepository.save(new User(requestDto));
    }
}
