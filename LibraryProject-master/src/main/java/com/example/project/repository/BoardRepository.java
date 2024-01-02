package com.example.project.repository;

import com.example.project.dto.BoardDTO;
import com.example.project.entity.BoardEntity;
import com.example.project.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Wrapper;
import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    // update board_table set boar_hits=board_hits+1 where id=?
    @Modifying
    @Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id")
    void updateHits(@Param("id") Long id);


    //    @Query("SELECT b FROM BoardEntity b WHERE LOWER(b.boardTitle) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<BoardEntity> findByBoardTitleContainingIgnoreCase(String keyword, Pageable pageable);
//    @Query("SELECT b FROM BoardEntity b WHERE LOWER(b.boardTitle) LIKE LOWER(CONCAT('%', :keyword, '%'))")
//    List<BoardEntity> findByBoardTitleContainingIgnoreCase(String keyword, PageRequest id);

    List<BoardEntity> findByBoardWriter(String email);
}