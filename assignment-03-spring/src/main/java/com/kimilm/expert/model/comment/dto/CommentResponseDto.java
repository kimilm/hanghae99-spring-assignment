package com.kimilm.expert.model.comment.dto;

import com.kimilm.expert.model.comment.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private Long commentId;
    private Long postId;
    private String author;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getId();
        this.postId = comment.getPost().getId();
        this.author = comment.getUser().getUsername();
        this.contents = comment.getContents();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
