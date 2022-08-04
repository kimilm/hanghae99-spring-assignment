package com.kimilm.expert.service;

import com.kimilm.expert.model.comment.Comment;
import com.kimilm.expert.model.comment.dto.CommentRequestDto;
import com.kimilm.expert.model.comment.dto.CommentResponseDto;
import com.kimilm.expert.model.post.Post;
import com.kimilm.expert.model.user.User;
import com.kimilm.expert.repository.CommentRepository;
import com.kimilm.expert.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public List<CommentResponseDto> getCommentsByPostId(Long postId) {
        List<CommentResponseDto> comments = commentRepository.findByPost_Id(postId).stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());

        return comments;
    }

    @Transactional
    public Long createComment(Long postId, CommentRequestDto requestDto, User user) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        Comment comment = new Comment(requestDto, post, user);

        return commentRepository.save(comment).getId();
    }
}
