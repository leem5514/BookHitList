package com.example.project.controller;

import com.example.project.config.TokenProvider;
import com.example.project.dto.BoardDTO;
import com.example.project.dto.MemberDTO;
import com.example.project.dto.MypageDTO;
import com.example.project.dto.MypageMapDTO;
import com.example.project.entity.MypageEntity;
import com.example.project.service.BoardService;
import com.example.project.service.MypageService;
import com.example.project.util.CookieUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

    private final MypageService mypageService;
    private final BoardService boardService;
    private final TokenProvider tokenProvider;
    private final CookieUtil cookieUtil;

    // 지도 위치 정하는 페이지
    @GetMapping("/mapupdate")
    public String updateMapForm() {
        return "page/mapUpdate";
    }

    // 기능
    @PostMapping("/mapupdate")
    public String updateMap(Model model, @ModelAttribute MypageMapDTO mypageMapDTO, @RequestParam("email") String email) {
        String memberId = email;
        mypageService.updateMap(memberId, mypageMapDTO);
        return "redirect:/mypage";
    }

    // 마이페이지
    @GetMapping("")
    public String page(Model model, HttpServletRequest request) {
        Claims claims = tokenProvider.getClaims(cookieUtil.getCookie(request));
        String Writer = claims.getSubject();
        List<BoardDTO> boardDTOList = boardService.findByBoardWriter(Writer);
        model.addAttribute("boardList", boardDTOList);
        return "page/myPage";
    }

    // 지도 위치
    @GetMapping("/map")
    public String map(Model model, HttpServletRequest request) {
        Claims claims = tokenProvider.getClaims(cookieUtil.getCookie(request));
        String Writer = claims.getSubject();
        MypageEntity mypageEntity = mypageService.findById(Writer);
        if (mypageEntity.getLatitude() == null || mypageEntity.getLongitude() == null)
        {
            return "redirect:/mypage";
        }
        model.addAttribute("lat", mypageEntity.getLatitude());
        model.addAttribute("lon", mypageEntity.getLongitude());
        return "page/map";
    }

}