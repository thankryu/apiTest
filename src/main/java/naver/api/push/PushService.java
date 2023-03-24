package naver.api.push;

public interface PushService {

    void sendPush(NaverPushDto dto) throws Exception;

}