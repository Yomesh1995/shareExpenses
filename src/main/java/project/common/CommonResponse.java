package project.common;

public class CommonResponse {

    private int responseCode;
    private String resposneMessage;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResposneMessage() {
        return resposneMessage;
    }

    public void setResposneMessage(String resposneMessage) {
        this.resposneMessage = resposneMessage;
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "responseCode=" + responseCode +
                ", resposneMessage='" + resposneMessage + '\'' +
                '}';
    }
}
