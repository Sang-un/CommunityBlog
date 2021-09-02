package com.sanbon.backend.controller.post.board;


import com.sanbon.backend.controller.AbsBulletinController;
import com.sanbon.backend.model.dto.Header;
import com.sanbon.backend.model.dto.response.post.board.BoardResponseDto;
import com.sanbon.backend.model.post.Bulletin;
import com.sanbon.backend.service.post.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController extends AbsBulletinController<BoardResponseDto, Bulletin> {

    private final BoardService boardService;

    @GetMapping("/main")
    public Header<BoardResponseDto> readAll(@PageableDefault(sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return boardService.readAll(pageable);
    }

}
