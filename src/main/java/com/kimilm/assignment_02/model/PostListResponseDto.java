package com.kimilm.assignment_02.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostListResponseDto {
    private String title;
    private String author;
    private LocalDateTime createdAt;

    public PostListResponseDto(Post post) {
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.createdAt = post.getCreatedAt();
    }
}
