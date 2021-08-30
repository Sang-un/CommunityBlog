package com.sanbon.backend.service.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public abstract class AbsBulletinService<Res, Entity> {

    @Autowired(required = false)
    protected JpaRepository<Entity, Long> baseRepository;

    public abstract ResponseEntity<Res> searchByWriter(String writer, Pageable pageable);

    public abstract ResponseEntity<Res> searchByTitle(String title, Pageable pageable);

    public abstract ResponseEntity<Res> searchByTitleOrContent(String title, String content, Pageable pageable);
}
