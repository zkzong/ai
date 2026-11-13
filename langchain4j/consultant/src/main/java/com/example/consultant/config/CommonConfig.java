package com.example.consultant.config;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.ClassPathDocumentLoader;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CommonConfig {

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

    @Resource
    private ChatMemoryStore redisChatMemoryStore;

    // 构建会话记忆对象
    @Bean
    public ChatMemory chatMemory() {
        MessageWindowChatMemory memory = MessageWindowChatMemory.builder()
                .maxMessages(20)
                .build();
        return memory;
    }

    // 构建ChatMemoryProvider对象
    @Bean
    public ChatMemoryProvider chatMemoryProvider() {
        ChatMemoryProvider chatMemoryProvider = new ChatMemoryProvider() {
            @Override
            public ChatMemory get(Object memoryId) {
                return MessageWindowChatMemory.builder()
                        .id(memoryId)
                        .maxMessages(20)
                        .chatMemoryStore(redisChatMemoryStore)
                        .build();
            }
        };
        return chatMemoryProvider;
    }

    // 构建向量数据库操作对象
    @Bean
    public EmbeddingStore store() { // EmbeddingStore的对象，这个对象的名字不能重复，所以这里使用store
        // 1、加载文档进内存
        List<Document> documents = ClassPathDocumentLoader.loadDocuments("content");
        // 2、构建向量数据库操作对象
        InMemoryEmbeddingStore store = new InMemoryEmbeddingStore();
        // 3、构建一个EmbeddingStoreIngestor对象，完成文本数据切割、向量化、存储
        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .embeddingStore(store)
                .build();
        ingestor.ingest(documents);
        return store;
    }

    // 构建向量数据库检索对象
    @Bean
    public ContentRetriever contentRetriever(EmbeddingStore store) {
        return EmbeddingStoreContentRetriever.builder()
                .embeddingStore(store)
                .minScore(0.5)
                .maxResults(3)
                .build();
    }

}
