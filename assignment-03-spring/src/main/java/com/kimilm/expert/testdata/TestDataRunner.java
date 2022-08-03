package com.kimilm.expert.testdata;

import com.kimilm.expert.model.post.Post;
import com.kimilm.expert.model.post.dto.PostRequestDto;
import com.kimilm.expert.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TestDataRunner implements ApplicationRunner {

    private final PostRepository postRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        int testDataSize = 20;
        List<Post> postList = new ArrayList<>();
        for (int i = 1; i <= testDataSize; i++) {
            PostRequestDto requestDto = new PostRequestDto(
                    "title" + i,
                    "author" + i,
                    "contents" + i,
                    "password" + i
            );

            postList.add(new Post(requestDto));
        }

        postRepository.saveAll(postList);
    }
}
