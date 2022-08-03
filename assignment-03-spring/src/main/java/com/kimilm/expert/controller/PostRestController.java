package com.kimilm.expert.controller;

import com.kimilm.expert.model.post.Post;
import com.kimilm.expert.model.post.dto.PostListResponseDto;
import com.kimilm.expert.model.post.dto.PostRequestDto;
import com.kimilm.expert.model.post.dto.PostResponseDto;
import com.kimilm.expert.repository.PostRepository;
import com.kimilm.expert.security.UserDetailsImpl;
import com.kimilm.expert.service.PostService;
import com.kimilm.expert.util.PostUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.kimilm.expert.controller.ApiPath.NAME_SPACE;

@RestController
@RequiredArgsConstructor
public class PostRestController {

    private final PostRepository postRepository;
    private final PostService postService;

    // 게시글 전체 목록 조회 api
    @GetMapping(NAME_SPACE + "/api/posts")
    public ResponseEntity<?> getPostList() {
        List<PostListResponseDto> postListResponseDtoList = postRepository
                .findAllByOrderByCreatedAtDesc().stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put(PostUtils.DATA, postListResponseDtoList);

        return ResponseEntity.ok().body(result);
    }

    // 게시글 조회 api
    @GetMapping(NAME_SPACE + "/api/posts/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("잘못된 게시글 아이디를 입력하였습니다.")
        );
        PostResponseDto responseDto = new PostResponseDto(post);

        Map<String, Object> result = new HashMap<>();
        result.put(PostUtils.DATA, responseDto);

        return ResponseEntity.ok().body(result);
    }

    // 게시글 작성 api
    @PostMapping(NAME_SPACE + "/api/posts")
    public ResponseEntity<?> createPost(
            @RequestBody PostRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        Long savedPostId = postService.createPost(requestDto, userDetails.getUser());

        Map<String, Object> result = new HashMap<>();
        result.put(PostUtils.MESSAGE, "작성 완료");
        result.put("postId", savedPostId);

        return ResponseEntity.ok().body(result);
    }

    // 게시글 수정 api
    @PutMapping(NAME_SPACE + "/api/posts/{postId}")
    public ResponseEntity<?> updatePost(
            @PathVariable Long postId,
            @RequestBody PostRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        Long updatedId = postService.updatePost(postId, requestDto, userDetails.getUser());

        Map<String, Object> result = new HashMap<>();
        result.put(PostUtils.MESSAGE, "수정 성공");
        result.put("id", updatedId);

        return ResponseEntity.ok().body(result);
    }

    // 게시글 삭제 api
    @DeleteMapping(NAME_SPACE + "/api/posts/{postId}")
    public ResponseEntity<?> updatePost(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long deletedId = postService.deletePost(postId, userDetails.getUser());

        Map<String, Object> result = new HashMap<>();
        result.put(PostUtils.MESSAGE, "삭제 성공");
        result.put("postId", deletedId);

        return ResponseEntity.ok().body(result);
    }
}
