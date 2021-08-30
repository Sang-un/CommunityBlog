package com.sanbon.backend.controller;

import com.sanbon.backend.service.post.AbsBulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@CrossOrigin("*")
public abstract class AbsBulletinController<Res, Entity> {

    @Autowired(required = false)
    protected AbsBulletinService<Res, Entity> absBulletinService;

    @GetMapping("/search/writer")
    public ResponseEntity<Res> searchByWriter(@RequestParam String writer,
                                              @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return absBulletinService.searchByWriter(writer, pageable);
    }

    @GetMapping("/search/title")
    public ResponseEntity<Res> searchByTitle(@RequestParam String title,
                                             @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return absBulletinService.searchByTitle(title, pageable);
    }

    @GetMapping("/search/title&content")
    public ResponseEntity<Res> searchByTitleOrWriter(@RequestParam String title, @RequestParam String content,
                                                     @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return absBulletinService.searchByTitleOrContent(title, content, pageable);
    }


}
