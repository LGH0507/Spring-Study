package LDHD.study.domain.user.web.controller.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 클래스의 멤버 변수에 대해 getter 메소드를 자동 생성해준다.
 *
 * 예를 들어, String name, String password 이런 식으로 멤버 변수가 선언 됐을 때, 클래스에 이 어노테이션을 붙이면
 *      public String getName(){
 *         return this.name;
 *     }
 *
 * 위 메소드를 자동 생성해준다.
 * -> 반대로 @Setter는 setName()을 자동으로 생성해주겠지?
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    // 특정 필드의 getter만 사용할 경우에는 이렇게 필드에 붙여도 된다. (Setter도 마찬가지)
    //@Getter
    String name;
    String password;
    String address;
    String email;

    int age;

    /**
     * 이 JSON 데이터가 컨트롤러의 @RequestBody를 통해 Java 객체로 매핑된다.
     * 이때, JSON이란, JavaScriptObjectNotation으로, 한마디로 javascript에서 주로 다루는 데이터 형식이라고 이해해도 된다.
     *
     * 아니, 애초에 Http 요청을 보낼 때 Java 객체에 편리한 방향으로 데이터 형식을 만들어서 보내면 되는데,
     * 왜 javascript에서 자주 사용하는 데이터 형식으로 요청을 보낼까?
     *
     * >>> 웹에서는 주로 html, css, javascript를 쓰니까. Http는 클라이언트(웹)와 서버가 통신하는 구조를 갖는다.
     * 근데 웹은 javascript를 사용하니까 당연히 json으로 보내는 게 편리하다.
     * 그리고 json 데이터 구조 자체를 봤을 때 key:value 형식으로 돼있어서 그냥 매핑하기도 쉽다.
     *
     * {
     * 		"name": "이교형",
     * 		"password": "1234",
     * 		"address": "서울시 강동구",
     * 		"email": "kevin0507@gachon.ac.kr",
     * 		"age": 24
     * }
     *
     * 사실, Http에서 요청과 함께 데이터를 보낼 때, 데이터 형식을 json외에도 다양하게 보낼 수 있다.
     * 대표적인게 옛날에 사용했던 form data 형식 -> 궁금하면 찾아볼 것
     * 간단하게 설명하면 html 페이지 자체에서 요청을 보내는 것이다.
     * 근데 요즘은 거의 http 데이터 형식을 json으로 하는 것이 거의 표준 -> 거의 대부분이 이렇게 함
     */

}
