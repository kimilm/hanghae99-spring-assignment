package com.kimilm.expert.model.post.dto;

import com.kimilm.expert.model.comment.dto.CommentResponseDto;
import com.kimilm.expert.model.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String author;
    private String contents;
    private List<CommentResponseDto> comments;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.author = post.getUser().getUsername();
        this.contents = post.getContents();
        this.comments = post.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }
}
