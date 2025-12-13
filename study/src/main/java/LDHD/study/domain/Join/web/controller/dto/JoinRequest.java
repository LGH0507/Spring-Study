package LDHD.study.domain.Join.web.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JoinRequest {
    String name;
    String password;
    String email;
    int age;

    String address;
    String specAddress; // 상세 주소

    int gender;
    int birthYear;
    int birthMonth;
    int birthDay;

}
