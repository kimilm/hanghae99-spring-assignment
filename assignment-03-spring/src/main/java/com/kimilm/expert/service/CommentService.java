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

    // 조회
    public List<CommentResponseDto> getCommentsByPostId(Long postId) {
        List<CommentResponseDto> comments = commentRepository.findByPost_Id(postId).stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());

        return comments;
    }

    // 생성
    @Transactional
    public Long createComment(Long postId, CommentRequestDto requestDto, User user) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        Comment comment = new Comment(requestDto, post, user);

        return commentRepository.save(comment).getId();
    }

    // 수정
    @Transactional
    public Long updateComment(Long commentId, CommentRequestDto requestDto, User user) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));

        if (!comment.getUser().getUsername().equals(user.getUsername())) {
            throw new IllegalArgumentException("댓글을 작성한 사용자만 수정할 수 있습니다.");
        }

        comment.updateComment(requestDto);

        return comment.getId();
    }
}
