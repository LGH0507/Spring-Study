package LDHD.study.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // COMMON
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    VALIDATION_FAILED(HttpStatus.BAD_REQUEST, "COMMON400", "유효하지 않는 값입니다."),

    // USER
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER400", "존재하지 않는 회원입니다."),
    ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "USER401", "이미 존재하는 회원입니다"),

    //Post(게시물 정보 누락, 게시물 접근 권한 없음-사용자 Id와 게시물의 userId가 일치하지 않을 경우)
    POST_NOT_FOUND(HttpStatus.NOT_FOUND,"POST400", "게시물 정보를 찾을 수 없습니다."),
    UNAUTHORIZED(HttpStatus.FORBIDDEN, "AUTHO400", "접근 권한이 없습니다."),

    //중복되는 경우
    DUPLICATE_RESOURCE(HttpStatus.CONFLICT, "RES409","이미 존재하는 리소스입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}