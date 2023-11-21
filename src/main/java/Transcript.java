
/**
 * Transcript
 */
public class Transcript {

    private String audio_url;
    private String id;
    private String status;
    private String text;
    public String getText(){
        return text;
    }
    public void setText(String newText){
        text = newText;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String newStatus){
        status = newStatus;
    }

    public String getId(){
        return id;
    }
    public void setId(String newId){
        id = newId;
    }
    public String getAudioUrl(){
        return audio_url;
    }
    public void setAudioUrl(String newBaseUrl ){
        audio_url = newBaseUrl;
    }

}
