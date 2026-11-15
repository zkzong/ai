import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.community.model.dashscope.WanxImageModel;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.output.Response;
import org.junit.jupiter.api.Test;

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

    @Test
    public void test() {
        WanxImageModel wanxImageModel = WanxImageModel.builder()
                .apiKey(System.getenv("BAILIAN_API_KEY"))
                .modelName("qwen-image-2.1-pro")
                .build();

        Response<Image> response = wanxImageModel.generate("美女");
        System.out.println(response.content().url());
    }
}
