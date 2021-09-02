package com.sanbon.backend.repository.post;

import com.sanbon.backend.model.enumclass.BulletinStatus;
import com.sanbon.backend.model.post.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository<T extends Board> extends JpaRepository<T, Long> {
    Page<Board> findAllByStatusIn(List<BulletinStatus> statuses, Pageable pageable);

    Page<Board> findAllByWriterContaining(String writer, Pageable pageable);

    Page<Board> findAllByTitleContaining(String title, Pageable pageable);

    Page<Board> findAllByTitleContainingOrContentContaining(String title,String content,Pageable pageable);
}
