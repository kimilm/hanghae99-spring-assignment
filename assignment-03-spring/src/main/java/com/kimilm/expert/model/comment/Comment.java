package com.kimilm.expert.model.comment;

import com.kimilm.expert.model.Timestamped;
import com.kimilm.expert.model.comment.dto.CommentRequestDto;
import com.kimilm.expert.model.post.Post;
import com.kimilm.expert.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @Column(name="COMMENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    public Comment(CommentRequestDto requestDto, Post post, User user) {
        this.contents = requestDto.getContents();
        this.post = post;
        this.user = user;
    }

    public void updateComment(CommentRequestDto requestDto) {
        this.contents = requestDto.getContents();
    }
}
