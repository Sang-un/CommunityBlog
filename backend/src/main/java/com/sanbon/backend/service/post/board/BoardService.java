package com.sanbon.backend.service.post.board;

import com.sanbon.backend.model.dto.Header;
import com.sanbon.backend.model.dto.Pagination;
import com.sanbon.backend.model.dto.response.post.board.BoardListResponse;
import com.sanbon.backend.model.dto.response.post.board.BoardResponseDto;
import com.sanbon.backend.model.enumclass.BulletinStatus;
import com.sanbon.backend.model.post.board.Board;
import com.sanbon.backend.repository.post.BoardRepository;
import com.sanbon.backend.service.post.AbsBulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService extends AbsBulletinService<BoardResponseDto, Entity> {

    @Autowired
    private BoardRepository<Board> boardRepository;

    public Header<BoardResponseDto> readAll(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board> boardsByStatus = searchByStatus(pageable);
        return getListHeader(boards, boardsByStatus);
    }

    @Override
    public Header<BoardResponseDto> searchByWriter(String writer, Pageable pageable) {
        Page<Board> boards = boardRepository.findAllByWriterContaining(writer, pageable);
        Page<Board> boardsByStatus = searchByStatus(pageable);
        return getListHeader(boards, boardsByStatus);
    }

    @Override
    public Header<BoardResponseDto> searchByTitle(String title, Pageable pageable) {
        Page<Board> boards = boardRepository.findAllByTitleContaining(title, pageable);
        Page<Board> boardByStatus = searchByStatus(pageable);
        return getListHeader(boards, boardByStatus);
    }

    @Override
    public Header<BoardResponseDto> searchByTitleOrContent(String title, String content, Pageable pageable) {
        Page<Board> boards = boardRepository.findAllByTitleContainingOrContentContaining(title, content, pageable);
        Page<Board> boardByStatus = searchByStatus(pageable);
        return getListHeader(boards, boardByStatus);
    }

    public Page<Board> searchByStatus(Pageable pageable) {
        return boardRepository.findAllByStatusIn(Arrays.asList(BulletinStatus.URGENT, BulletinStatus.NOTICE), pageable);
    }

    private Header<BoardResponseDto> getListHeader(Page<Board> boards, Page<Board> boardsByStatus) {
        BoardResponseDto boardResponseDto = BoardResponseDto.builder()
                .boardApiResponseList(boards.stream()
                        .map(BoardListResponse::toEntity).collect(Collectors.toList())).build();

        List<BoardListResponse> boardApiNoticeResponseList = new ArrayList<>();
        List<BoardListResponse> boardApiUrgentResponseList = new ArrayList<>();

        boardsByStatus.stream().forEach(board -> {
            BoardListResponse boardListResponse = BoardListResponse.toEntity(board);
            if (boardListResponse.getStatus() == BulletinStatus.NOTICE) {
                boardApiNoticeResponseList.add(boardListResponse);
            } else if (boardListResponse.getStatus() == BulletinStatus.URGENT) {
                boardApiUrgentResponseList.add(boardListResponse);
            }
        });

        boardResponseDto.setBoardApiNoticeResponseList(boardApiNoticeResponseList);
        boardResponseDto.setBoardApiUrgentResponseList(boardApiUrgentResponseList);

        Pagination pagination = Pagination.builder()
                .totalElements(boards.getTotalElements())
                .totalPage(boards.getTotalPages())
                .currentElements(boards.getNumberOfElements())
                .currentPage(boards.getNumber())
                .build();

        return Header.OK(boardResponseDto, pagination);

    }


}
