package com.kimilm.expert.service;

import com.kimilm.expert.model.comment.Comment;
import com.kimilm.expert.model.comment.dto.CommentResponseDto;
import com.kimilm.expert.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<CommentResponseDto> getCommentsByPostId(Long postId) {
        List<CommentResponseDto> comments = commentRepository.findByPost_Id(postId).stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());

        return comments;
    }

}
