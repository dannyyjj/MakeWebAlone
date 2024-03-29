package com.danny.makewebalone.web;

import com.danny.makewebalone.config.auth.LoginUser;
import com.danny.makewebalone.config.auth.dto.SessionUser;
import com.danny.makewebalone.service.posts.PostsService;
import com.danny.makewebalone.web.dto.PostsResponseDto;
import com.danny.makewebalone.web.dto.PostsSaveRequestDto;
import com.danny.makewebalone.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id, @LoginUser SessionUser user) {
        // 삭제시 검증루틴 추가할 것
        log.error("user===>{}",user);
        postsService.delete(id);
        return id;
    }
}