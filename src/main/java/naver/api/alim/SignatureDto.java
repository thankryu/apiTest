package naver.api.alim;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SignatureDto {
    String url;
    String method;
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
}