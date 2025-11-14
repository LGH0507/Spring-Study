package LDHD.study.domain.user.web.controller;

import LDHD.study.domain.user.service.UserService;
import LDHD.study.domain.user.web.controller.dto.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?>addUser(@RequestBody CreateUserRequest createUserRequest){

        userService.addUser(createUserRequest);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?>addUser(@PathVariable Long userId){


        return ResponseEntity.ok().build();
    }

}
