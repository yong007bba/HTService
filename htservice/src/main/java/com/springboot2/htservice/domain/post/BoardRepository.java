package com.springboot2.htservice.domain.post;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("SELECT p FROM Board p order by p.id DESC ")
    List<Board> findAllDesc();
}
