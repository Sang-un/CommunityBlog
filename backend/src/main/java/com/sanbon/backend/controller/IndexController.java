package com.sanbon.backend.controller;

import com.sanbon.backend.model.dto.response.post.PostsListResponseDto;
import com.sanbon.backend.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostService postService;

    @GetMapping("/")
    public ResponseEntity<List<PostsListResponseDto>> index(){
        return postService.findAllDesc();
    }
}
