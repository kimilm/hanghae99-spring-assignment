package com.kimilm.expert.testdata;

import com.kimilm.expert.model.comment.Comment;
import com.kimilm.expert.model.comment.dto.CommentRequestDto;
import com.kimilm.expert.model.post.Post;
import com.kimilm.expert.model.post.dto.PostRequestDto;
import com.kimilm.expert.model.user.User;
import com.kimilm.expert.model.user.dto.SignupRequestDto;
import com.kimilm.expert.repository.CommentRepository;
import com.kimilm.expert.repository.PostRepository;
import com.kimilm.expert.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TestDataRunner implements ApplicationRunner {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        TestDataInsert();
    }

    @Transactional
    public void TestDataInsert() {
        User testUser = saveTestUser();
        List<Post> testPosts = saveTestPosts(testUser);
        List<Comment> testComments = saveTestComment(testPosts, testUser);
    }

    private User saveTestUser() {
        SignupRequestDto signupRequestDto = new SignupRequestDto("TestUser", "TestPassword", "TestPassword");
        User user = new User(signupRequestDto);

        return userRepository.save(user);
    }

    private List<Post> saveTestPosts(User user) {
        int testDataSize = 20;
        List<Post> postList = new ArrayList<>();
        for (int i = 1; i <= testDataSize; i++) {
            PostRequestDto requestDto = new PostRequestDto(
                    "title" + i,
                    "contents" + i
            );

            postList.add(new Post(requestDto, user));
        }

        return postRepository.saveAll(postList);
    }

    private List<Comment> saveTestComment(List<Post> testPosts, User testUser) {
        int testDataSize = 10;
        List<Comment> commentList = new ArrayList<>();
        for (int i = 1; i <= testDataSize; i++) {
            CommentRequestDto commentRequestDto = new CommentRequestDto("Comment contents" + i);

            commentList.add(new Comment(commentRequestDto, testPosts.get((i % 5)), testUser));
        }

        return commentRepository.saveAll(commentList);
    }

}
