package naver.api.push;

import lombok.extern.slf4j.Slf4j;
import naver.api.util.NaverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;

@Slf4j
@Service
public class PushServiceImpl implements PushService{

    // 네이버 발송 키
    @Value("${naver-access-key}") String accessKey;
    @Value("${naver-service-id}") String serviceId;

    private String url = "/push/v2/services/";

    @Autowired
    RestTemplate restTemplate;

    @Override
    public void sendPush(NaverPushDto pushDto) throws Exception{

        // 1. 발송 Url 및 Header Setting

        // x-ncp-apigw-timestamp
        String timestamp = String.valueOf(new Timestamp(System.currentTimeMillis()).getTime());

        String sendUrl = url + serviceId +"/messages";

        log.info("sendUrl::"+sendUrl);

        SignatureDto dto = SignatureDto.builder()
                .url(sendUrl)
                .method("POST")
                .timestamp(timestamp).build();
        
        // x-ncp-apigw-signature-v2
        String signature = NaverUtil.makeSignature(dto);

        log.info("signature::"+signature);
        
        // 2. push 서버에 발송
        
        // 3. 발송 결과값 저장
    }
}