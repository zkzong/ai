package org.example.springai.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatClient chatClient;

    @RequestMapping("/call")
    public String call(@RequestParam String prompt) {
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }

    @RequestMapping(value = "/stream", produces = "text/html;charset=utf-8")
    public Flux<String> stream(@RequestParam String prompt) {
        return chatClient.prompt()
                .user(prompt)
                .stream()
                .content();
    }

}
