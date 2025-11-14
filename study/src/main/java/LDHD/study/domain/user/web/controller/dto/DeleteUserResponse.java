package LDHD.study.domain.user.web.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DeleteUserResponse {
    Long userId;

    public DeleteUserResponse(Long userId) {

        this.userId = userId;
    }

}