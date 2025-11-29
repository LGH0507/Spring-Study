package LDHD.study.domain.bookmark.web.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookmarkResponse {

    Long id;
    Long postId;
    Long userId;
    String message;

    public CreateBookmarkResponse(Long id, Long postId, Long userId) {

        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.message = "즐겨 찾기에 등록되었습니다.";
    }
}
