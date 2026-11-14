package com.example.consultant.controller;

import com.example.consultant.aiservice.ConsultantService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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

//    @RequestMapping("/chat")
//    public String chat(String message) {
//        String result = consultantService.chat(message);
//        return result;
//    }

    @RequestMapping(value = "/chat", produces = "text/html;charset=UTF-8")
    public Flux<String> chat(String memoryId, String message) {
        Flux<String> result = consultantService.chat(memoryId, message);
        return result;
    }
}
