package LDHD.study.domain.post.web.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateCommentRequest {

    //Json으로 전송할 댓글 내용
    String content;
}
