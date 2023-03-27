package naver.api.push;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NaverPushResponseDto {
    public String requestId;
    public String requestTime;
    public String statusCode;
    public String statusName;

}
