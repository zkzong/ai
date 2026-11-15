import com.alibaba.dashscope.audio.ttsv2.SpeechSynthesisParam;
import com.alibaba.dashscope.audio.ttsv2.SpeechSynthesizer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class AudioTest {
    private static final String MODEL = "qwen-audio-3.1-tts-plus";
    private static final String VOICE = "longanlingxin_v3.1";

    public static void streamAuidoDataToSpeaker() {
        String apiKey = System.getenv("BAILIAN_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("请先设置 BAILIAN_API_KEY 环境变量");
        }

        SpeechSynthesisParam param =
                SpeechSynthesisParam.builder()
                        .apiKey(apiKey)
                        .model(MODEL)
                        .voice(VOICE)
                        .build();
        SpeechSynthesizer synthesizer = new SpeechSynthesizer(param, null);
        ByteBuffer audio = synthesizer.call("大家好");
        File file = new File("output.mp3");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            byte[] audioBytes = new byte[audio.remaining()];
            audio.get(audioBytes);
            fos.write(audioBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        streamAuidoDataToSpeaker();
    }
}
