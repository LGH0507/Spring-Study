package LDHD.study.domain.post.web.controller.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DeletePostResponse {

    Long postId;

    public DeletePostResponse(Long postId) {
        this.postId = postId;
    }
}
