package com.itheima.langchain4j;

import dev.langchain4j.model.openai.OpenAiChatModel;

public class App {
    public static void main(String[] args) {
        // 构建OpenAiChatModel对象
        OpenAiChatModel model = OpenAiChatModel.builder()
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .apiKey("sk-447d9c2836554791a4b3087ef21811e6")
                .modelName("deepseek-v4-pro-0813")
                .logRequests(true)
                .logResponses(true)
                .build();
        // 调用chat方法,交互
        String result = model.chat("你是谁？");
        System.out.println(result);
    }
}
