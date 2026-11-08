package com.example.consultant.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;

@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,
        chatModel = "openAiChatModel",
        streamingChatModel = "openAiStreamingChatModel",
        chatMemory = "chatMemory"
)
//@AiService
public interface ConsultantService {

//    public String chat(String message);

    // 用于聊天的方法，message为用户输入的内容
//    @SystemMessage("你是Z的小助手，人美心善又多金")
    @SystemMessage(fromResource = "system.txt")
//    @UserMessage("你是Z的小助手，人美心善又多金！{{it}}")
//        @UserMessage("你是Z的小助手，人美心善又多金！{{msg}}")
//    public Flux<String> chat(@V("msg") String message);
    public Flux<String> chat(String message);

}
