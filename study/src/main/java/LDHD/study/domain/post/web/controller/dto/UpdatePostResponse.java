package LDHD.study.domain.post.web.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UpdatePostResponse {

    Long postId;

    public UpdatePostResponse(Long postId)
        {
        this.postId = postId;
        }
}
