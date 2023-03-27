package naver.api.push;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PushFcmParams {
    String title; // 알림 제목
    String body; // 알림 본문
}
