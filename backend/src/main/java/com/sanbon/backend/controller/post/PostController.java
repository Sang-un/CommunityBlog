package com.sanbon.backend.controller.post;


import com.sanbon.backend.model.dto.request.post.PostsSaveRequestDto;
import com.sanbon.backend.model.dto.request.post.PostsUpdateRequestDto;
import com.sanbon.backend.model.post.Post;
import com.sanbon.backend.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<Post> save(@RequestBody PostsSaveRequestDto postsSaveRequestDto) {
        return postService.save(postsSaveRequestDto);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto postsUpdateRequestDto) {
        return postService.update(id, postsUpdateRequestDto);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Post> delete(@PathVariable Long id) {
        return postService.delete(id);
    }



}
