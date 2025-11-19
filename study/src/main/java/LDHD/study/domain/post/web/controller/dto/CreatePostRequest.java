package LDHD.study.domain.post.web.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreatePostRequest {

    Long userId;

    String title;
    String category;
    String content;

}
