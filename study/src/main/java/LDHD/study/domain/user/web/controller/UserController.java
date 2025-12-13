package LDHD.study.domain.user.web.controller;

import LDHD.study.common.response.ErrorCode;
import LDHD.study.common.response.GlobalResponse;
import LDHD.study.common.response.SuccessCode;
import LDHD.study.domain.user.service.UserService;
import LDHD.study.domain.user.web.controller.dto.CreateUserRequest;
import LDHD.study.domain.user.web.controller.dto.CreateUserResponse;
import LDHD.study.domain.user.web.controller.dto.UpdateUserRequest;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "새로운 사용자 정보 등록", description = "회원가입 요청 데이터를 받아 새로운 데이터를 DB에 저장합니다.")
    @PostMapping // PostMapping : 말 그대로 POST 방식으로 오는 요청을 이 메소드로 매핑해준다.
    //매개변수(CreateUserRequest)의 @RequestBody를 통해 JSON 데이터가 Java 객체로 역직렬화 된다.
    public ResponseEntity<GlobalResponse>addUser(@RequestBody CreateUserRequest createUserRequest){

        // 이렇게 받은 json 데이터를 DTO를 통해 Service에게 전달한다.
        CreateUserResponse response = userService.addUser(createUserRequest);

        return GlobalResponse.onSuccess(SuccessCode.CREATED, response);

    }

    @Operation(summary = "기존 사용자 정보 삭제", description = "DB에 저장되어 있는 사용자 정보를 삭제합니다.")
    @DeleteMapping("/{userId}")
    public ResponseEntity<GlobalResponse>deleteUser(@PathVariable Long userId){

        userService.deleteUser(userId);

        return GlobalResponse.onSuccess(SuccessCode.DELETED);
    }

    /**
     *
     * 사용자 정보를 수정하는 API
     *  - 만약, 수정을 요청한 사람이 본인이 아닐 경우
     *  - 만약, 수정하고자 했는데 데이터베이스에 회원 정보가 없을 경우
     *      -> 얘는 발생할 가능성이 매우 낮다. 하지만 조금이라도 발생할 가능성이 있는 부분에 대해서는 모두 예외 처리를 해야 한다.
     *  따라서 이 API를 실행할 때, 발생할 수 있는 모든 경우를 예외 처리해야 한다.
     *
     *  일반적으로 Java에서는 try-catch 문으로 Exception 객체를 던지고 받아서 예외 처리를 한다.
     *
     *  가장 간단한 방법은 예외가 발생할 수 있는 모든 지점에 try-catch 문을 적용하는 것이다.
     *  근데 이렇게 하면 문제가 발생할 수 있다.
     *   - 코드가 길어진다.
     *   - 예외 처리 로직이 모든 메소드마다 존재하기 때문에 예외 처리 방식이 바뀌면 그 코드를 하나하나 전부 다 수정해야 한다.
     *
     *   -> 전역 예외 처리를 둔다. -> 한 곳에서 예외 처리 로직을 관리한다.
     *
     * @param userId
     * @param request
     * @return
     */

    @Operation(summary = "사용자 정보 수정", description = "DB에 저장되어 있는 사용자 정보를 수정합니다.")
    @PutMapping("/{userId}")
    public ResponseEntity<GlobalResponse>updateUser(@PathVariable Long userId, @RequestBody UpdateUserRequest request){

        userService.updateUser(userId, request, "Updated Successfully!");

        return GlobalResponse.onSuccess(SuccessCode.UPDATED, request);
    }
}
