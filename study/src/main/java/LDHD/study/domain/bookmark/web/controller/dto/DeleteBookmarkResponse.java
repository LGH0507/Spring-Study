package LDHD.study.domain.bookmark.web.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteBookmarkResponse {


    Long postId;
    Long userId;

    public DeleteBookmarkResponse (Long postId, Long userId) {

        this.postId = postId;
        this.userId = userId;

    }
}
