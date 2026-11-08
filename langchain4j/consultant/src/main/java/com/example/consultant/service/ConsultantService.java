package com.example.consultant.service;

import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;

@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,
        chatModel = "openAiChatModel",
        streamingChatModel = "openAiStreamingChatModel"
)
//@AiService
public interface ConsultantService {

    // 用于聊天的方法，message为用户输入的内容
    public Flux<String> chat(String message);

}
