package com.springboot2.htservice.service;

import com.springboot2.htservice.domain.board.Board;
import com.springboot2.htservice.domain.board.BoardRepository;
import com.springboot2.htservice.web.dto.BoardListResponseDto;
import com.springboot2.htservice.web.dto.BoardResponseDto;
import com.springboot2.htservice.web.dto.BoardSaveRequestDto;
import com.springboot2.htservice.web.dto.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardSaveRequestDto requestDto){
        return boardRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, BoardUpdateRequestDto requestDto){
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자 없음 id= " + id));


        board.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete(Long id){
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자 없음 id= " + id));
        boardRepository.delete(board);
    }

    @Transactional(readOnly = true)
    public BoardResponseDto findById(long id){
        Board entity = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자 없음 id= " + id));

        return new BoardResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<BoardListResponseDto> findAllDesc() {
        return boardRepository.findAllDesc().stream()
                .map(BoardListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateView (Long id) {
        boardRepository.updateView(id);
    }

    @Transactional
    public Page<Board> getBoardList(Pageable pageable){
        return boardRepository.findAll(pageable);
     }

     @Transactional
    public boolean getListCheck(Pageable pageable){
        Page<Board> saved = getBoardList(pageable);
         return saved.hasNext();
     }

}
