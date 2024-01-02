package com.example.project.dto;

import com.example.project.entity.BoardEntity;
import com.example.project.entity.BoardFileEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String boardTitle;
    private String boardContents;
    private String category;
    private String detailCategory;
    private String deliveryFee;
    private String area;
    private String state;
    private String price;

    private int boardHits;
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;

    private MultipartFile boardFile; // save.html -> Controller 파일 담는 용도
    private String originalFileName; // 원본 파일 이름
    private String storedFileName; // 서버 저장용 파일 이름
    //    private List<MultipartFile> boardFile; // 컨트롤러로 파일 담는 용도
//    private List<String> originalFileName;
//    private List<String> storedFileName;
    private int fileAttached; // 파일 첨부 여부

    public BoardDTO(Long id, String boardWriter, String boardTitle, int boardHits, LocalDateTime boardCreatedTime, String price, String category, String detailCategory, String storedFileName) {
        this.id = id;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.category = category;
        this.detailCategory = detailCategory;
        this.price = price;
        this.boardHits = boardHits;
        this.boardCreatedTime = boardCreatedTime;
        this.storedFileName = storedFileName;
    }

//    public BoardDTO(Long id, String boardWriter, String boardTitle, int boardHits, LocalDateTime boardCreatedTime) {
//        this.id = id;
//        this.boardWriter = boardWriter;
//        this.boardTitle = boardTitle;
//        this.boardHits = boardHits;
//        this.boardCreatedTime = boardCreatedTime;
//    }

    public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setCategory(boardEntity.getCategory());
        boardDTO.setDetailCategory(boardEntity.getDetailCategory());
        boardDTO.setDeliveryFee(boardEntity.getDeliveryFee());
        boardDTO.setArea(boardEntity.getArea());
        boardDTO.setState(boardEntity.getState());
        boardDTO.setPrice(boardEntity.getPrice());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
        boardDTO.setBoardUpdatedTime(boardEntity.getUpdatedTime());

        if(boardEntity.getFileAttached() == 0 ) {
            boardDTO.setFileAttached(boardEntity.getFileAttached()); //0
        } else {
            boardDTO.setFileAttached(boardEntity.getFileAttached()); // 1
            boardDTO.setOriginalFileName(boardEntity.getBoardFileEntityList().get(0).getOriginalFileName());
            boardDTO.setStoredFileName(boardEntity.getBoardFileEntityList().get(0).getStoredFileName());
        }
        return boardDTO;
    }
}