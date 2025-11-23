package LDHD.study.domain.post.web.controller.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreateCommentResponse {

    Long userId; //댓글 작성자 ID
    Long postId; // 댓글이 달린 게시물 ID
    Long commentId; // 생성된 댓글의 ID
    String content;

    public CreateCommentResponse(Long userId, Long postId, Long commentId, String content) {

        this.userId = userId;
        this.postId = postId;
        this.commentId = commentId;
        this.content = content;
    }

}
