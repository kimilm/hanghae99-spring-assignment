package com.kimilm.expert.controller;

import com.kimilm.expert.model.user.User;
import com.kimilm.expert.model.user.dto.LoginRequestDto;
import com.kimilm.expert.model.user.dto.SignupRequestDto;
import com.kimilm.expert.service.UserService;
import com.kimilm.expert.util.PostUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kimilm.expert.controller.ApiPath.NAME_SPACE;
import static com.kimilm.expert.util.PostUtils.ACCESS_TOKEN;
import static com.kimilm.expert.util.PostUtils.REFRESH_TOKEN;

@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    // 로그인
    @PostMapping(NAME_SPACE + "/api/users/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto requestDto) {

        List<String> tokens = userService.login(requestDto);

        // 헤더에 발급받은 토큰 추가
        HttpHeaders headers = new HttpHeaders();
        headers.add(ACCESS_TOKEN, tokens.get(0));

        if (tokens.size() == 2) {
            headers.add(REFRESH_TOKEN, tokens.get(1));
        }
        // body
        Map<String, Object> response = new HashMap<>();
        response.put(PostUtils.MESSAGE, "로그인 성공");

        return ResponseEntity.ok()
                .headers(headers)
                .body(response);
    }

    // 회원가입
    @PostMapping(NAME_SPACE + "/api/users/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequestDto requestDto) {
        User user = userService.register(requestDto);

        Map<String, Object> response = new HashMap<>();
        response.put(PostUtils.MESSAGE, "가입이 완료되었습니다");

        return ResponseEntity.ok().body(response);
    }
}
