package LDHD.study.domain.user.web.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UpdateUserResponse {

    Long userId;
    String message = " Updated Successfully!";


    public UpdateUserResponse(Long userId) {
        this.userId = userId;
        this.message = message;
    }
}
