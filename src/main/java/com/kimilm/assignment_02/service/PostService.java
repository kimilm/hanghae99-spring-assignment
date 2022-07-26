package com.kimilm.assignment_02.service;

import com.kimilm.assignment_02.model.Post;
import com.kimilm.assignment_02.model.PostRequestDto;
import com.kimilm.assignment_02.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long updatePost(Long id, PostRequestDto requestDto) throws NoSuchAlgorithmException {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("잘못된 게시글 아이디를 입력하였습니다.")
        );
        post.update(requestDto);

        return id;
    }
}
