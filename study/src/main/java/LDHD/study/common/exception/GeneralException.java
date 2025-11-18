package LDHD.study.common.exception;

import LDHD.study.common.response.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException{ //런타임에 발생하는 예외라고 정의한다.

    private ErrorCode errorCode;

}