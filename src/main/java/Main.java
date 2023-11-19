import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;

public class Main {
    public static void main(String[] args) throws URISyntaxException {
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.assemblyai.com/v2/transcript"))
            .header("Authorization", Constant.API_KEY)
            .POST(BodyPublishers.ofString("\"audio_url\": \"https://storage.googleapis.com/aai-web-samples/news.mp4\""))
    }
}
