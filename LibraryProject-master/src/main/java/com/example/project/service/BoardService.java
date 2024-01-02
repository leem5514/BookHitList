package com.example.project.service;

import com.example.project.dto.BoardDTO;
import com.example.project.entity.BoardEntity;
import com.example.project.entity.BoardFileEntity;
import com.example.project.repository.BoardFileRepository;
import com.example.project.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    @Autowired
    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;

    public void save(BoardDTO boardDTO) throws IOException {

        if (boardDTO.getBoardFile().isEmpty()) {
            BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
            boardRepository.save(boardEntity);
        } else {
            //첨부파일 있을 경우
            MultipartFile boardFile = boardDTO.getBoardFile(); // 1.
            String originalFilename = boardFile.getOriginalFilename(); // 2.
            String storedFileName = System.currentTimeMillis() + "_" + originalFilename; // 3.
            String savePath = "C:/springboot_img/" + storedFileName; // 4. C:/springboot_img/9802398403948_내사진.jpg
//            String savePath = "/Users/사용자이름/springboot_img/" + storedFileName; // C:/springboot_img/9802398403948_내사진.jpg
            boardFile.transferTo(new File(savePath)); // 5.
            BoardEntity boardEntity = BoardEntity.toSaveFileEntity(boardDTO);
            Long savedId = boardRepository.save(boardEntity).getId();
            BoardEntity board = boardRepository.findById(savedId).get();

            BoardFileEntity boardFileEntity = BoardFileEntity.toBoardFileEntity(board, originalFilename, storedFileName);
            boardFileRepository.save(boardFileEntity);
        }
    }



    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    @Transactional
    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if(optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        } else {
            return null;
        }
    }

    public BoardDTO update(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity);
        return findById(boardDTO.getId());
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<BoardDTO> paging(Pageable pageable, String keyword, String sortField) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3; // 한 페이지에 보여줄 글의 갯수

//        Page<BoardEntity> boardEntities;
//        if (keyword != null && !keyword.isEmpty()) {
//            boardEntities = boardRepository.findByBoardTitleContainingIgnoreCase(keyword, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
//        } else {
//            boardEntities = boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
//        }
        Page<BoardEntity> boardEntities;
        if (keyword != null && !keyword.isEmpty()) {
            boardEntities = boardRepository.findByBoardTitleContainingIgnoreCase(
                    keyword, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, sortField)));
        } else {
            boardEntities = boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, sortField)));
        }
        System.out.println("boardEntities.getContent() = " + boardEntities.getContent());
        System.out.println("boardEntities.getTotalElements() = " + boardEntities.getTotalElements());
        System.out.println("boardEntities.getNumber() = " + boardEntities.getNumber());
        System.out.println("boardEntities.getTotalPages() = " + boardEntities.getTotalPages());
        System.out.println("boardEntities.getSize() = " + boardEntities.getSize());
        System.out.println("boardEntities.hasPrevious() = " + boardEntities.hasPrevious());
        System.out.println("boardEntities.isFirst() = " + boardEntities.isFirst());
        System.out.println("boardEntities.isLast() = " + boardEntities.isLast());

        Page<BoardDTO> boardDTOS = boardEntities.map(board -> new BoardDTO(
                board.getId(), board.getBoardWriter(), board.getBoardTitle(),
                board.getBoardHits(), board.getCreatedTime(), board.getPrice(), board.getCategory(), board.getDetailCategory(), board.getStoredFileName()
        ));
        return boardDTOS;
    }
    @Transactional
    public List<BoardDTO> searchPosts(String keyword, Pageable pageable) {
        Page<BoardEntity> searchList = boardRepository.findByBoardTitleContainingIgnoreCase(keyword, pageable);
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity: searchList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }
    @Transactional
    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity: boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }
//@Transactional
//public Page<BoardDTO> search(String keyword, Pageable pageable) {
//    System.out.println("Keyword: " + keyword);
//    Page<BoardEntity> postList = boardRepository.findByBoardTitleContainingIgnoreCase(keyword, pageable);
//    List<BoardDTO> searchResult = new ArrayList<>();
//    System.out.println("postList : " + postList);
//    for (BoardEntity boardEntity : postList.getContent()) {
//        searchResult.add(BoardDTO.toBoardDTO(boardEntity));
//    }
//    return new PageImpl<>(searchResult, pageable, postList.getTotalElements());
//}

    @Transactional
    public List<BoardDTO> findByBoardWriter(String email) {
         List<BoardEntity> boardList = boardRepository.findByBoardWriter(email);
         System.out.println(boardList);
         List<BoardDTO> boardDTOList = new ArrayList<>();
         for (BoardEntity boardEntity: boardList) {
             boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
         }
         return boardDTOList;
    }
}