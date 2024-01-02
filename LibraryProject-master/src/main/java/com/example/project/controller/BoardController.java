package com.example.project.controller;


import com.example.project.dto.BoardDTO;
import com.example.project.entity.BoardEntity;
import com.example.project.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/save")
    public String saveForm() {
        return "board/boardSave";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) throws IOException {
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "redirect:/";
    }

    @GetMapping("/")
    public String findAll(
            @PageableDefault(page = 1) Pageable pageable,
            @RequestParam(name = "sortField", defaultValue = "id") String sortField,
            Model model) {
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);

        Page<BoardDTO> boardPage = boardService.paging(pageable, null, sortField);

        int blockLimit = 3;
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < boardPage.getTotalPages()) ? startPage + blockLimit - 1 : boardPage.getTotalPages();

        model.addAttribute("boardPage", boardPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("sortField", sortField);  // Add this line to pass the sortField to the view

        System.out.println("boardPage: " + boardPage);
        return "board/boardList";
    }


    @GetMapping("/search")
    public String search(
            @PageableDefault(page = 1) Pageable pageable,
            @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
            @RequestParam(name = "sortField", defaultValue = "id") String sortField,
            Model model) {
        List<BoardDTO> boardDTOList = boardService.searchPosts(keyword, pageable);
        model.addAttribute("boardList", boardDTOList);

        Page<BoardDTO> boardPage = boardService.paging(pageable, keyword, sortField);

        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < boardPage.getTotalPages()) ? startPage + blockLimit - 1 : boardPage.getTotalPages();

        model.addAttribute("boardPage", boardPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("sortField", sortField);
        model.addAttribute("keyword", keyword);

        System.out.println("boardPage : " + boardPage);
        return "board/boardList";
    }
//        Page<BoardDTO> searchResult = boardService.search(keyword, pageable);
//        model.addAttribute("boardList", searchResult);
//
////        int blockLimit = 3;
////        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
////        int endPage = ((startPage + blockLimit - 1) < searchResult.getTotalPages()) ? startPage + blockLimit - 1 : searchResult.getTotalPages();
//
//        System.out.println("searchResult : " +  searchResult);
//        model.addAttribute("searchResult", searchResult);
////        model.addAttribute("startPage", startPage);
////        model.addAttribute("endPage", endPage);
//
//        return "board/searchResult";
//    }

//    @GetMapping("/search")
//    public String search(@RequestParam(required = false) String keyword, @PageableDefault(page = 1) Pageable pageable, Model model) {
//        Page<BoardDTO> searchResult = boardService.search(keyword, pageable);
//        model.addAttribute("boardList", searchResult);
//
//        int blockLimit = 3;
//        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
//        int endPage = ((startPage + blockLimit - 1) < searchResult.getTotalPages()) ? startPage + blockLimit - 1 : searchResult.getTotalPages();
//
//        model.addAttribute("searchResult", searchResult);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//        model.addAttribute("keyword", keyword); // Adding keyword to the model to display in the view
//
//        return "board/boardList";
//    }



//@GetMapping("/search")
//public String search(
//        @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword, Model model, @PageableDefault(page = 1) Pageable pageable) {
//    Page<BoardDTO> boardPage = boardService.search(keyword, pageable);
//
//    int blockLimit = 3;
//    int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
//    int endPage = ((startPage + blockLimit - 1) < boardPage.getTotalPages()) ? startPage + blockLimit - 1 : boardPage.getTotalPages();
//
//    model.addAttribute("boardPage", boardPage.getContent());
//    model.addAttribute("startPage", startPage);
//    model.addAttribute("endPage", endPage);
//
//    return "board/boardPage";
//}

//    @GetMapping("/search")
//    public String search(String keyword, Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
//        Page<BoardEntity> searchList = boardService.search(keyword, pageable);
//        model.addAttribute("searchList", searchList);
//        return "board/boardList";
//    }
//@GetMapping("/search")
//public String search(String keyword, Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
//    // Perform the search with pagination
//    Page<BoardEntity> searchList = boardService.search(keyword, pageable);
//    model.addAttribute("boardList", searchList);
//
//    // Null check for searchList
//    if (searchList != null) {
//        // Calculate pagination information
//        int blockLimit = 3;
//        int startPage = ((int) Math.ceil((double) pageable.getPageNumber() / blockLimit) - 1) * blockLimit + 1;
//        int endPage = Math.min(startPage + blockLimit - 1, searchList.getTotalPages());
//
//        // Add the search result and pagination information to the model
//        model.addAttribute("searchList", searchList);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//    } else {
//        // Handle the case where searchList is null, you can add appropriate error handling or default values
//        model.addAttribute("searchList", Page.empty());
//        model.addAttribute("startPage", 1);
//        model.addAttribute("endPage", 1);
//    }
//
//    return "board/boardList";
//}




//    @GetMapping("/search")
//    public String search(@PageableDefault(page = 1) Pageable pageable, String keyword, Model model) {
//        List<BoardDTO> boardDTOList = boardService.searchPosts(keyword);
//
////        int blockLimit = 3;
////        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1 ) * blockLimit + 1;
////        int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages();
//
//        model.addAttribute("boardList", boardDTOList);
////        model.addAttribute("startPage", startPage);
////        model.addAttribute("endPage", endPage);
//
//        return "board/boardList";
//    }

//    @GetMapping("/search")
//    public String search(@PageableDefault(page = 1) Pageable pageable,
//                         @RequestParam String keyword, Model model) {
//        try {
//            System.out.println("Received Keyword: " + keyword);
//            System.out.println("Pageable: " + pageable);
//
//            // 검색 서비스 호출
//            Page<BoardDTO> boardDTOList = boardService.searchPosts(keyword, pageable);
//            System.out.println("Searched Boards: " + boardDTOList);
//
//            // 모델에 추가
//            model.addAttribute("boardList", boardDTOList);
//
//            // 페이징 처리
//            Page<BoardDTO> boardList = boardService.paging(pageable);
//            int blockLimit = 3;
//            int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1 ) * blockLimit + 1;
//            int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages();
//            model.addAttribute("boardList", boardList);
//            model.addAttribute("startPage", startPage);
//            model.addAttribute("endPage", endPage);
//
//            return "board/boardList";
//        } catch (Exception e) {
//            // 예외가 발생하면 콘솔에 출력
//            e.printStackTrace();
//            // 예외 페이지로 리다이렉트 또는 예외 메시지를 모델에 추가해서 보여줄 수 있음
//            return "error";
//
//        }
//}



    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model, @PageableDefault(page=1)Pageable pageable) {
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        model.addAttribute("page", pageable.getPageNumber());
        return "board/boardDetail";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardUpdate", boardDTO);
        return "board/boardUpdate";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
        BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("board", board);
        return "board/boardDetail";
//        return "redirect:/board/" + boardDTO.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect:/board/";
    }

    //    페이지 관리
//    @GetMapping("/paging")
//    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
////        pageable.getPageNumber();
//        Page<BoardDTO> boardList = boardService.paging(pageable);
//
//        int blockLimit = 3;
//        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1 ) * blockLimit + 1;
//        int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages();
//
//        model.addAttribute("boardList", boardList);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//        return "paging";
//    }


    // test페이지
    @GetMapping("/test")
    public String test() {
        return "board/test";
    }




}
