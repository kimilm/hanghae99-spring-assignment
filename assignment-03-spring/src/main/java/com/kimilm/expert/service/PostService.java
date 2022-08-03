package com.kimilm.expert.service;

import com.kimilm.expert.model.post.Post;
import com.kimilm.expert.model.post.dto.PostRequestDto;
import com.kimilm.expert.model.user.User;
import com.kimilm.expert.repository.PostRepository;
import com.kimilm.expert.util.PostUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 게시글 생성
    @Transactional
    public Long createPost(PostRequestDto requestDto, User user) {
        Post post = new Post(requestDto, user);
        return postRepository.save(post).getId();
    }


    // 게시글 수정
    @Transactional
    public Long updatePost(Long postId, PostRequestDto requestDto, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("잘못된 게시글 아이디를 입력하였습니다.")
        );

        if (!post.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("게시글 작성자만 수정할 수 있습니다.");
        }

        post.update(requestDto);

        return postId;
    }

    // 게시글 삭제
    public Long deletePost(Long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("잘못된 게시글 아이디를 입력하였습니다.")
        );

        if (!post.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("게시글 작성자만 삭제할 수 있습니다.");
        }

        postRepository.delete(post);

        return postId;
    }
}
