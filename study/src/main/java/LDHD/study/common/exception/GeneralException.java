package LDHD.study.common.exception;

import LDHD.study.common.response.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 사용자가 직접 정의한 Exception 종류 중에 하나이다.
 * RuntimeException 상속 받아서 Exception 객체로 구현된 것이다.
 *
 * 왜 우리가 예외를 이렇게 만들까?
 *  Java에서 기본적으로 제공하는 예외보다 더 많은 예외를 처리하기 위함이다.
 *      -> 근데 Java에서는 기본적인 Exception 클래스가 존재한다. 이걸 사용하면 되지 않나?
 *      -> GeneralException과 같이 일반적인 예외를 우리가 직접 설정함으로써 ErrorCode와 같이 예외를 구성하는 형식도 우리가 자유롭게 정할 수 있다.
 *          -> 예외가 발생하여 사용자에게 정보를 제공할 때도, 우리가 직접 설정한 형식에 맞게 정보를 제공할 수 있게 된다.
 *
 */

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException{ //런타임에 발생하는 예외라고 정의한다.

    private ErrorCode errorCode;

}