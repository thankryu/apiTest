package naver.api.push;

import lombok.Builder;
import lombok.Data;

@Data
public class SignatureDto {
    String url;
    String method;
    String timestamp;

    public SignatureDto(){};

    @Builder
    public SignatureDto(String url, String method, String timestamp) {
        this.url = url;
        this.method = method;
        this.timestamp = timestamp;
    }
}