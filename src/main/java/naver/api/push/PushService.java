package naver.api.push;

public interface PushService {

    void sendNaverPush(NaverPushDto dto) throws Exception;

}