package com.sanbon.backend.service.post;

import com.sanbon.backend.component.advice.ErrorCode;
import com.sanbon.backend.component.advice.exception.PostNotFoundException;
import com.sanbon.backend.model.dto.request.post.PostsSaveRequestDto;
import com.sanbon.backend.model.dto.request.post.PostsUpdateRequestDto;
import com.sanbon.backend.model.dto.response.post.PostsListResponseDto;
import com.sanbon.backend.model.post.Post;
import com.sanbon.backend.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public ResponseEntity<Post> save(PostsSaveRequestDto postsSaveRequestDto) {
        return new ResponseEntity<>(postRepository.save(postsSaveRequestDto.toEntity()), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Post> update(Long id, PostsUpdateRequestDto postsUpdateRequestDto) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(ErrorCode.INVALID_PARAMETER));
        post.update(postsUpdateRequestDto.getTitle(), postsUpdateRequestDto.getContent());
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Post> delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(ErrorCode.INVALID_PARAMETER));
        postRepository.delete(post);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<List<PostsListResponseDto>> findAllDesc() {
        List<PostsListResponseDto> list = postRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


}
