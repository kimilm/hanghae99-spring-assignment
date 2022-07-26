package com.kimilm.assignment_02.controller;

import com.kimilm.assignment_02.model.Post;
import com.kimilm.assignment_02.model.PostListResponseDto;
import com.kimilm.assignment_02.model.PostRequestDto;
import com.kimilm.assignment_02.repository.PostRepository;
import com.kimilm.assignment_02.util.PostUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PostRestController {

    private final PostRepository postRepository;

    // 게시글 전체 목록 조회 api
    @GetMapping("/api/posts")
    public ResponseEntity<Map<String, Object>> getPostList() {
        List<PostListResponseDto> postListResponseDtoList = postRepository
                .findAllByOrderByCreatedAtDesc().stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
        Map<String, Object> result = new HashMap<>();
        result.put(PostUtils.DATA, postListResponseDtoList);
        return ResponseEntity.ok().body(result);
    }

    // 게시글 작성 api
    @PostMapping("/api/posts")
    public ResponseEntity<Map<String, Object>> createPost(@RequestBody PostRequestDto requestDto) throws NoSuchAlgorithmException {
        Post post = new Post(requestDto);
        Post saved = postRepository.save(post);

        Map<String, Object> result = new HashMap<>();
        result.put(PostUtils.MESSAGE, "작성 완료");
        result.put("id", saved.getId());

    return ResponseEntity.ok().body(result);
    }
}
