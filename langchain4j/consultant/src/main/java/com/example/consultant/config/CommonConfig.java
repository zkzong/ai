//package com.example.consultant.config;
//
//import com.example.consultant.service.ConsultantService;
//import dev.langchain4j.model.openai.OpenAiChatModel;
//import dev.langchain4j.service.AiServices;
//import jakarta.annotation.Resource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class CommonConfig {
//
//    @Resource
//    private OpenAiChatModel model;
//
//    @Bean
//    public ConsultantService consultantService() {
//        ConsultantService cs = AiServices.builder(ConsultantService.class)
//                .chatModel(model)
//                .build();
//        return cs;
//    }
//}
