package LDHD.study.domain.user.web.controller;

import LDHD.study.domain.user.service.UserService;
import LDHD.study.domain.user.web.controller.dto.CreateUserRequest;
import LDHD.study.domain.user.web.controller.dto.CreateUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users") // "/api/users"로 들어오는 요청이 이 UserController로 매핑 된다는 의미를 갖는 어노테이션이다.

/**
 *  POST localhost:8080/api/users로 요청을 보냈다.
 *  @localhost -> 내 컴퓨터
 *  @8080 - 포트 -> 컴퓨터 내부에서 프로세스가 실행 되는 논리적인 단위 -> 스프링부트는 기본적으로 서버 컴퓨터의 8080포트에서 실행이 된다.
 *  결국에는 localhost:8080/api/users는 "내 컴퓨터의 8080 포트에 위치한 프로세스의 /api/users 라는 경로로 요청을 보냈다" 라는 의미가 된다.
 *  @POST : Http의 메소드
 *
 *  따라서 POST localhost:8080/api/users 이 요청은 localhost:8080의 프로세스(스프링 서버)에게 /api/users에 위치한 기능을 요청하되,
 *  POST 방식으로 요청한 것이다(데이터를 "처리"해달라는 요청을 보낸 것이다.)
 */
public class UserController {

    private final UserService userService;

    @PostMapping // PostMapping : 말 그대로 POST 방식으로 오는 요청을 이 메소드로 매핑해준다.
    //매개변수(CreateUserRequest)의 @RequestBody를 통해 JSON 데이터가 Java 객체로 역직렬화 된다.
    public ResponseEntity<?>addUser(@RequestBody CreateUserRequest createUserRequest){

        // 이렇게 받은 json 데이터를 DTO를 통해 Service에게 전달한다.
        CreateUserResponse response = userService.addUser(createUserRequest);

        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?>addUser(@PathVariable Long userId){

        userService.deleteUser(userId);

        return ResponseEntity.ok().build();
    }

}
