package com.danny.makewebalone.web.domain.posts;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

//    @After
//    public void cleanup() {
//        postsRepository.deleteAll();
//    }

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/danny";
    private static final String USER = "root";
    private static final String PW = "1234";

    @Test
    public void testConnection() throws ClassNotFoundException {
        Class.forName(DRIVER);
        try (Connection con = DriverManager.getConnection(URL, USER, PW)) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "test";
        String content = "content";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("danny@gmail.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2022, 9, 20, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>> createDate=" + posts.getCreatedDate() + ", modifiedData=" + posts.getModifiedData());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedData().isAfter(now));
    }
}