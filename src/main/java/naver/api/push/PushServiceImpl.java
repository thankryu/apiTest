package naver.api.push;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import naver.api.util.NaverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Timestamp;

@Slf4j
@Service
public class PushServiceImpl implements PushService{

    // 네이버 발송 키
    @Value("${naver-access-key}") String accessKey;
    @Value("${naver-service-id}") String serviceId;
    @Value("${fcm-key}") String fcmKey;
    @Value("${fcm-reg-id}") String fcmRegId;

    private String url = "/push/v2/services/";
    private String preUrl= "https://sens.apigw.ntruss.com";

    private String fcmUrl ="https://fcm.googleapis.com/fcm/send";

    @Autowired
    RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    public PushServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * 네이버 클라우드 푸시 발송
     * @param pushDto
     * @throws Exception
     */
    @Override
    public void sendNaverPush(NaverPushDto pushDto) throws Exception{

        // 1. 발송 Url 및 Header Setting

        // x-ncp-apigw-timestamp
        String timestamp = String.valueOf(new Timestamp(System.currentTimeMillis()).getTime());

        String keyUrl = url + serviceId +"/messages";

        log.info("keyUrl::"+keyUrl);

        SignatureDto dto = SignatureDto.builder()
                .url(keyUrl)
                .method("POST")
                .timestamp(timestamp).build();
        
        // x-ncp-apigw-signature-v2
        String signature = NaverUtil.makeSignature(dto);

        log.info("signature::"+signature);
        log.info("timestamp::"+timestamp);

        // 네이버 push TODO 발송기 분리 예정
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        httpHeaders.set("Content-Type", "application/json; charset=utf-8");
        httpHeaders.set("x-ncp-apigw-timestamp", timestamp);
        httpHeaders.set("x-ncp-iam-access-key", accessKey);
        httpHeaders.set("x-ncp-apigw-signature-v2", signature);

        HttpEntity<?> httpEntity = new HttpEntity<>(toJson(pushDto), httpHeaders);

        // 2. push 서버에 발송
        ResponseEntity<NaverPushResponseDto> response =
                restTemplate.exchange(preUrl+keyUrl, HttpMethod.POST, httpEntity,  NaverPushResponseDto.class);

        System.out.println(response.getBody());
        
        // 3. 발송 결과값 저장
    }

    /**
     * FCM 알람 다이렉트 전송
     * @param params
     * @throws Exception
     */
    @Override
    public void sendFcmPush(PushFcmParams params) throws Exception {
        PushFcmDto pushFcmDto = PushFcmDto.builder()
                .to(fcmRegId)
                .priority("high")
                .notification(params)
                .build();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "key="+fcmKey);

        HttpEntity<?> httpEntity = new HttpEntity<>(toJson(pushFcmDto), httpHeaders);

        ResponseEntity<FcmResponseDto> response =
                restTemplate.exchange(fcmUrl, HttpMethod.POST, httpEntity,  FcmResponseDto.class);

        log.info(response.getBody().toString());
    }

    /**
     * json 화에 사용
     * @param data
     * @return
     * @param <T>
     * @throws JsonProcessingException
     */
    private <T> String toJson(T data) throws JsonProcessingException {
        return objectMapper.writeValueAsString(data);
    }
}