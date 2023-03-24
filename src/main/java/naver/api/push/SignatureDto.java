package naver.api.push;

import lombok.Data;

@Data
public class SignatureDto {
    String url;
    String method;
    String timestamp;
}