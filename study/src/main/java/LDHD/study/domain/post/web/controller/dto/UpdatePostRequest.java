package LDHD.study.domain.post.web.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdatePostRequest {

    //수정 내용 받기 위한 필드
    String title;
    String category;
    String content;

}
