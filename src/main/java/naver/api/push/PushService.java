package naver.api.push;

public interface PushService {

    void sendNaverPush(NaverPushDto dto) throws Exception;

    void sendFcmPush(PushFcmParams params) throws Exception;
}