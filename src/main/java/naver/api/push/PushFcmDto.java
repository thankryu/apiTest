package naver.api.push;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PushFcmDto {
    String to; // 수신자
    String priority; // 메시지 우선순위
    PushFcmParams notification; // 발송 내용
}
