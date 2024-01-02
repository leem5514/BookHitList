package com.example.project.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ChatController {
    @GetMapping("/chat")
    public String index(){
        return "error";
    }

    @GetMapping("/chat/{id}")
    public String chattingRoom(@PathVariable String id, HttpSession session, Model model){
            model.addAttribute("name", id);
        return "chat/chattingRoom";
    }
}