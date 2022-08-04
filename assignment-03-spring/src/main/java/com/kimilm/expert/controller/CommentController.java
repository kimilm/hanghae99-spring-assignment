package com.kimilm.expert.controller;

import com.kimilm.expert.model.comment.dto.CommentRequestDto;
import com.kimilm.expert.model.comment.dto.CommentResponseDto;
import com.kimilm.expert.security.UserDetailsImpl;
import com.kimilm.expert.service.CommentService;
import com.kimilm.expert.util.PostUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kimilm.expert.controller.ApiPath.NAME_SPACE;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping(NAME_SPACE + "/api/comments")
    public ResponseEntity<?> getCommentByPostId(@RequestParam Long postId) {
        List<CommentResponseDto> comments = commentService.getCommentsByPostId(postId);

        Map<String, Object> result = new HashMap<>();
        result.put(PostUtils.DATA, comments);

        return ResponseEntity.ok().body(result);
    }

    @PostMapping(NAME_SPACE + "/api/comments")
    public ResponseEntity<?> createComment(
            @RequestParam Long postId,
            @RequestBody CommentRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        Long createdCommentId = commentService.createComment(postId, requestDto, userDetails.getUser());

        Map<String, Object> result = new HashMap<>();
        result.put(PostUtils.MESSAGE, "댓글 작성 성공");
        result.put("commentId", createdCommentId);

        return ResponseEntity.ok().body(result);
    }

    @PutMapping(NAME_SPACE + "/api/comments/{commentId}")
    public ResponseEntity<?> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        Long updatedCommentId = commentService.updateComment(commentId, requestDto, userDetails.getUser());

        Map<String, Object> result = new HashMap<>();
        result.put(PostUtils.MESSAGE, "댓글 수정 성공");
        result.put("commentId", updatedCommentId);

        return ResponseEntity.ok().body(result);
    }
}
