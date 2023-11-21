import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

//import sun.awt.windows.ThemeReader;

public class Main {
    public static void main(String[] args) throws Exception {
        Transcript transcript = new Transcript();
        transcript.setAudioUrl("https://storage.googleapis.com/aai-web-samples/news.mp4");
        Gson gson = new Gson();
        String jsonRequst = gson.toJson(transcript);
        System.out.println(jsonRequst);
        HttpRequest postRequest = HttpRequest.newBuilder()
            .uri(new URI("https://api.assemblyai.com/v2/transcript"))
            .header("Authorization", Constant.API_KEY)
            .POST(BodyPublishers.ofString(jsonRequst))
            .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> postResponse = httpClient.send(postRequest, BodyHandlers.ofString());
//        System.out.println(postResponse.body().split("\\,")[4].split("\\:")[0]+ " : "+ postResponse.body().split("\\,")[4].split("\\:")[1]);
        transcript = gson.fromJson(postResponse.body(), Transcript.class);
        System.out.println(transcript.getId());

        HttpRequest getRequest = HttpRequest.newBuilder()
            .uri(new URI("https://api.assemblyai.com/v2/transcript/"+ transcript.getId()))
            .header("Authorization", Constant.API_KEY)
            .build();
        while (true) {
            HttpResponse<String> getResponse = httpClient.send(getRequest, BodyHandlers.ofString());
            transcript = gson.fromJson(getResponse.body(), Transcript.class);
            if("completed".equals(transcript.getStatus()) || "error".equals(transcript.getStatus())){
                break;
            }
            Thread.sleep(1000);
        }
        System.out.println("Transcription completed: ");
        System.out.println(transcript.getText());
            

    }
}
