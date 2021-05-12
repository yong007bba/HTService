package com.springboot2.htservice.domain.board;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("SELECT b FROM Board b order by b.id DESC ")
    List<Board> findAllDesc();

    @Modifying
    @Query("update Board b set b.viewCount = b.viewCount +1 where b.id= :id")
    int updateView(Long id);
}
