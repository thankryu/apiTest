package naver.api.push;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class FcmResponseDto {

    private long multicast_id; // 멀티캐스트 메시지를 식별하는 숫자로 된 고유 ID
    private int success; // 오류 없이 처리된 메시지 수 
    private int failure; // 처리하지 못한 메시지 수
    private int canonical_ids; // 
    private List<Result> results; // 처리된 메시지 상태

    @Getter
    @Setter
    public static class Result {

        private String message_id; // 성공적으로 처리된 각 메시지 고유 ID
        private String error; // 오류

    }
}