package LDHD.study.domain.user.web.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreateUserResponse {
    String name;

    public CreateUserResponse(String name) {

        this.name = name;
    }

    /**
     *      {
     *         "name" : "이교형"
     *      }
     */

}