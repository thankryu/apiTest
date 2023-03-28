package naver.api.push;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/push")
@RestController
public class PushController {

    private final PushService pushService;

    public PushController(PushService pushService) {
        this.pushService = pushService;
    }

    /**
     * push 메시지 발송
     * @param NaverPushDto
     * @return
     */
    @PostMapping("/sendNaverPush")
    public ResponseEntity sendNaverPush(NaverPushDto naverPushDto) throws Exception{
        pushService.sendNaverPush(naverPushDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * FCM Push 발송기
     * @param params
     * @return
     * @throws Exception
     */
    @PostMapping("/sendFCMPush")
    public String sendFcmPush(PushFcmParams params) throws Exception{
        String result = pushService.sendFcmPush(params);
        return result;
    }
}
