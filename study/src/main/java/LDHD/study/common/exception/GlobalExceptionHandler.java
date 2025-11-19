package LDHD.study.common.exception;

import LDHD.study.common.response.ErrorCode;
import LDHD.study.common.response.GlobalResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * 모든 요청은 각각의 Controller로부터 시작
 * 모든 예외는 throw를 통해 발생
 *  -> 언제 어디서 터질지 모르는 예외를 한 마리가 다 잡아주고, 얘가 예외 처리를 해주면 되지 않을까?
 *  그것이 GlobalExceptionHandler
 *
 * @RestControllerAdvice : @RestController가 붙은 모든 컨트롤러를 모두 감지한다.
 * @ExceptionHandler : 예외를 처리하겠다는 어노테이션이다.
 *
 * -> @RestControllerAdvice + @ExceptionHandler : 모든 @RestController에서 발생하는 예외를 감지하고, 이를 @ExceptionHandler가 처리한다.
 *
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * GeneralException이 발생했을 때, 이를 catch 해서 아래의 메소드를 실행한다.
     *
     * GeneralException : 우리가 정의한 커스텀 예외
     *
     */
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<GlobalResponse> handleGeneralException(GeneralException e) {
        ErrorCode errorCode = e.getErrorCode();

        return GlobalResponse.onFailure(errorCode);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // Valid 검증 실패시 예외처리
    public ResponseEntity<GlobalResponse> handleValidationExceptions(
            MethodArgumentNotValidException e) {
        ErrorCode errorCode = ErrorCode.VALIDATION_FAILED;

        // 모든 검증 오류를 하나의 문자열로 결합
        String errorMessage = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        return GlobalResponse.onFailure(errorCode, errorMessage);
    }

    @ExceptionHandler(com.fasterxml.jackson.core.JsonParseException.class) // JSON 파싱 오류 처리
    public ResponseEntity<GlobalResponse> handleJsonParseException(
            com.fasterxml.jackson.core.JsonParseException e) {
        log.error("JSON 파싱 오류: {}", e.getMessage());
        return GlobalResponse.onFailure(ErrorCode.VALIDATION_FAILED);
    }

    @ExceptionHandler(com.fasterxml.jackson.databind.JsonMappingException.class) // JSON 매핑 오류 처리
    public ResponseEntity<GlobalResponse> handleJsonMappingException(
            com.fasterxml.jackson.databind.JsonMappingException e) {
        log.error("JSON 매핑 오류: {}", e.getMessage());
        return GlobalResponse.onFailure(ErrorCode.VALIDATION_FAILED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalResponse> handleGenericException(Exception e) {
        ErrorCode errorCode =
                ErrorCode.INTERNAL_SERVER_ERROR;
        log.error("Unexpected Error Occured");
        log.error(e.getMessage(), e);
        log.error(e.getClass().getSimpleName());

        return GlobalResponse.onFailure(errorCode);
    }


}