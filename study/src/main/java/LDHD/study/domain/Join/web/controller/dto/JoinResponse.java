package LDHD.study.domain.Join.web.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JoinResponse {

    Long userId;
    String name; // 가입된 사용자 이름
    LocalDateTime created_at;

}
