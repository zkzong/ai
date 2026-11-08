package com.example.consultant.controller;

import com.example.consultant.service.ConsultantService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

//    @Resource
//    private OpenAiChatModel model;
//
//    @RequestMapping("/chat")
//    public String chat(String message) {
//        String result = model.chat(message);
//        return result;
//    }

    @Resource
    private ConsultantService consultantService;

    @RequestMapping("/chat")
    public String chat(String message) {
        String result = consultantService.chat(message);
        return result;
    }
}
