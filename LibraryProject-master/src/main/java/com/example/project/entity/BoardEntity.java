package com.example.project.entity;

import com.example.project.dto.BoardDTO;
import com.mysql.cj.Session;
import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String boardWriter;

    @Column
    private String boardTitle;

    @Column(length = 500)
    private String boardContents;

    @Column
    private String category;

    @Column
    private String detailCategory;

    @Column
    private String deliveryFee;

    @Column
    private String area;

    @Column
    private String state;

    @Column
    private String price;

    @Column
    private int boardHits;
    @Column
    private int fileAttached; // 파일 유무 확인(0 / 1)

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<BoardFileEntity> boardFileEntityList = new ArrayList<>();

    public String getStoredFileName() {
        return boardFileEntityList.isEmpty() ? null : boardFileEntityList.get(0).getStoredFileName();
    }

    public static BoardEntity toSaveEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setCategory(boardDTO.getCategory());
        boardEntity.setDetailCategory(boardDTO.getDetailCategory());
        boardEntity.setDeliveryFee(boardDTO.getDeliveryFee());
        boardEntity.setArea(boardDTO.getArea());
        boardEntity.setState(boardDTO.getState());
        boardEntity.setPrice(boardDTO.getPrice());
        boardEntity.setBoardHits(0);
        boardEntity.setFileAttached(0);
        return boardEntity;
    }

    public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardDTO.getId());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(boardDTO.getBoardHits());
        return boardEntity;
    }

    public static BoardEntity toSaveFileEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setCategory(boardDTO.getCategory());
        boardEntity.setDetailCategory(boardDTO.getDetailCategory());
        boardEntity.setDeliveryFee(boardDTO.getDeliveryFee());
        boardEntity.setArea(boardDTO.getArea());
        boardEntity.setState(boardDTO.getState());
        boardEntity.setPrice(boardDTO.getPrice());
        boardEntity.setBoardHits(0);
        boardEntity.setFileAttached(1); //파일 있음
        return boardEntity;
    }
}