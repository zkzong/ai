import com.alibaba.dashscope.aigc.multimodalconversation.MultiModalConversation;
import com.alibaba.dashscope.aigc.multimodalconversation.MultiModalConversationParam;
import com.alibaba.dashscope.aigc.multimodalconversation.MultiModalConversationResult;
import com.alibaba.dashscope.common.MultiModalMessage;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.exception.UploadFileException;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class ChatTest {

    @Test
    public void test01() {
        ChatLanguageModel model = OpenAiChatModel.builder()
//                .baseUrl("http://langchain4j.dev/demo/openai/v1")
                .apiKey("demo")
                .modelName("gpt-4o-mini")
                .build();

        String answer = model.chat("你好，你是谁？");
        System.out.println(answer);
    }

    /**
     * 测试基本对话-接入deepseek
     */
    @Test
    public void test02() {
        ChatLanguageModel model = OpenAiChatModel.builder()
                .baseUrl("https://api.deepseek.com")
                .apiKey(System.getenv("DEEP_SEEK_KEY"))
                .modelName("deepseek-reasoner")
                .build();

        String answer = model.chat("你好，你是谁？");
        System.out.println(answer);
    }

    /**
     * 测试基本对话-接入glm
     */
    @Test
    public void test03() {
        ChatLanguageModel model = QwenChatModel.builder()
//                .baseUrl("https://llm-f3k2qi1msp50xikq.cn-beijing.maas.aliyuncs.com/compatible-mode/v1")
                .apiKey(System.getenv("BAILIAN_API_KEY"))
                .modelName("glm-5.3")
                .build();

        String answer = model.chat("你好，你是谁？");
        System.out.println(answer);
    }

    /**
     * 测试基本对话-接入ollama
     */
    @Test
    public void test04() {
        ChatLanguageModel model = OllamaChatModel.builder()
                .baseUrl("http://192.168.163.128:11434")
//                .apiKey(System.getenv("BAILIAN_API_KEY"))
                .modelName("qwen3.5:2b")
                .build();

        String answer = model.chat("你好，你是谁？");
        System.out.println(answer);
    }

//    @Test
//    public void test() {
//        WanxImageModel wanxImageModel = WanxImageModel.builder()
//                .apiKey(System.getenv("BAILIAN_API_KEY"))
//                .modelName("qwen-image-2.1-pro")
//                .build();
//
//        Response<Image> response = wanxImageModel.generate("美女");
//        System.out.println(response.content().url());
//    }

    @Test
    public void test() throws NoApiKeyException, UploadFileException {
        MultiModalMessage message = MultiModalMessage.builder()
                .role(Role.USER.getValue())
                .content(List.of(Map.of("text", "美女")))
                .build();

        MultiModalConversationParam param = MultiModalConversationParam.builder()
                .apiKey(System.getenv("BAILIAN_API_KEY"))
                .model("qwen-image-2.1-pro")
                .messages(List.of(message))
                .build();

        MultiModalConversationResult result = new MultiModalConversation().call(param);
        Map<String, Object> output = result.getOutput().getChoices().get(0).getMessage().getContent().get(0);
        Object imageUrl = output.get("image");
        if (!(imageUrl instanceof String)) {
            throw new IllegalStateException("DashScope response did not contain an image URL: " + result);
        }
        System.out.println(imageUrl);
    }
}
