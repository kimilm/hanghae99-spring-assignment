package com.kimilm.expert.controller;

import com.kimilm.expert.model.user.User;
import com.kimilm.expert.model.user.dto.SignupRequestDto;
import com.kimilm.expert.service.UserService;
import com.kimilm.expert.util.PostUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.kimilm.expert.controller.ApiPath.NAME_SPACE;

@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    // 회원가입
    @PostMapping(NAME_SPACE + "/api/users/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequestDto requestDto) {
        User user = userService.register(requestDto);

        Map<String, Object> response = new HashMap<>();
        response.put(PostUtils.MESSAGE, "가입이 완료되었습니다");

        return ResponseEntity.ok().body(response);
    }
}
