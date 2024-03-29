package com.danny.makewebalone.web;

import com.danny.makewebalone.config.auth.LoginUser;
import com.danny.makewebalone.config.auth.dto.SessionUser;
import com.danny.makewebalone.service.posts.PostsService;
import com.danny.makewebalone.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    // private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());
        // SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user!=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("ficture", user.getPicture());
        }

        return "index";
    }
    @GetMapping("/posts/save")
    public String postsSava(Model model, @LoginUser SessionUser user){
        if(user!=null){
            model.addAttribute("userName", user.getName());
        }
        return "posts-save";
    }
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }

}
