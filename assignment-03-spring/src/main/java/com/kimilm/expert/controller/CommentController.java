package com.kimilm.expert.controller;

import com.kimilm.expert.model.comment.dto.CommentResponseDto;
import com.kimilm.expert.service.CommentService;
import com.kimilm.expert.util.PostUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kimilm.expert.controller.ApiPath.NAME_SPACE;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping(NAME_SPACE + "/api/comments/{postId}")
    public ResponseEntity<?> getCommentByPostId(@PathVariable Long postId) {

        List<CommentResponseDto> comments = commentService.getCommentsByPostId(postId);

        Map<String, Object> result = new HashMap<>();
        result.put(PostUtils.DATA, comments);

        return ResponseEntity.ok().body(result);
    }
}
