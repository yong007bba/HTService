package com.springboot2.htservice.service;

import com.springboot2.htservice.domain.post.Board;
import com.springboot2.htservice.domain.post.BoardRepository;
import com.springboot2.htservice.web.dto.BoardResponseDto;
import com.springboot2.htservice.web.dto.BoardSaveRequestDto;
import com.springboot2.htservice.web.dto.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
