package naver.api.navercloud;

import naver.api.push.NaverPushDto;
import naver.api.push.PushController;
import naver.api.push.PushFcmParams;
import naver.api.push.SignatureDto;
import naver.api.util.NaverUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
class NavercloudApplicationTests {

    @Autowired
    PushController pushController;

    @Test
    public void postPushTest() throws Exception {

        SignatureDto dto = new SignatureDto();
        dto.setMethod("POST");
        dto.setUrl("/push/v2/services/ncp:push:kr:303476591202:aiservice/users");

        Timestamp timestampTime = new Timestamp(System.currentTimeMillis());

        System.out.println("timestampTime::"+timestampTime.getTime());
        String signature = NaverUtil.makeSignature(dto);

        System.out.println("signature::"+signature);
    }

    @Test
    public void getPushTest() throws Exception {
        SignatureDto dto = new SignatureDto();
        dto.setMethod("GET");
        dto.setUrl("push/v2/services/ncp:push:kr:303476591202:aiservice/users/ab01");

        Timestamp timestampTime = new Timestamp(System.currentTimeMillis());

        System.out.println("timestampTime::"+timestampTime.getTime());
        String signature = NaverUtil.makeSignature(dto);

        System.out.println("signature::"+signature);
    }


    /**
     * 푸시 발송을 위한 timestamp, signature 출력
     * @throws Exception
     */
    @Test
    public void sendPush() throws Exception {
        
        SignatureDto dto = new SignatureDto();
        dto.setMethod("POST");
        dto.setUrl("/push/v2/services/ncp:push:kr:303476591202:aiservice/messages");

        Timestamp timestampTime = new Timestamp(System.currentTimeMillis());

        System.out.println("timestampTime::"+timestampTime.getTime());
        dto.setTimestamp(String.valueOf(timestampTime.getTime()));
        String signature = NaverUtil.makeSignature(dto);

        System.out.println("signature::"+signature);
    }

    @Test
    public void timeStampTest() throws Exception {
        NaverPushDto naverPushDto = new NaverPushDto();

        pushController.sendNaverPush(naverPushDto);
    }

    /**
     * 발송된 푸시 확인
     * @throws Exception
     */
    @Test
    public void checkPush() throws Exception {

        SignatureDto dto = new SignatureDto();
        dto.setMethod("GET");
        dto.setUrl("/push/v2/services/ncp:push:kr:303476591202:aiservice/messages/302a7cf9e64848609f9d53c4553cbef0");


        // Timestamp timestampTime = new Timestamp(System.currentTimeMillis());

        String signature = NaverUtil.makeSignature(dto);

        System.out.println("signature::"+signature);
    }

    @Test
    public void timeStamp(){
        Timestamp timestampTime = new Timestamp(System.currentTimeMillis());
        System.out.println(timestampTime.getTime());

    }

    /**
     * FCM 푸시 테스트
     * @throws Exception
     */
    @Test
    public void sendFcmPush() throws Exception{
        PushFcmParams params = new PushFcmParams();
        params.setBody("바디");
        params.setTitle("타이틀");
        String result = pushController.sendFcmPush(params);

        Assertions.assertEquals(result, "success");

    }
}
