package LDHD.study.domain.user.web.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateUserRequest {

    String name;
    String password;
    String address;
    String email;
    int age;

}


