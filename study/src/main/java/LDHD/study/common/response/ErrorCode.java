package LDHD.study.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // COMMON
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    VALIDATION_FAILED(HttpStatus.BAD_REQUEST, "COMMON400", "유효하지 않는 값입니다."),

    // USER
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER400", "존재하지 않는 회원입니다."),
    ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "USER401", "이미 존재하는 회원입니다");

    private final HttpStatus status;
    private final String code;
    private final String message;

}