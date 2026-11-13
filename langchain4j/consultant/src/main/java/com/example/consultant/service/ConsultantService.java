package com.example.consultant.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;

@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT, // 手动装配
        chatModel = "openAiChatModel", // 指定模型
        streamingChatModel = "openAiStreamingChatModel",
//        chatMemory = "chatMemory", // 配置会话记忆对象
        chatMemoryProvider = "chatMemoryProvider", // 配置会话记忆提供者对象
        contentRetriever = "contentRetriever" // 配置向量数据库检索对象
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
    public Flux<String> chat(@MemoryId String memoryId, @UserMessage String message);

}
