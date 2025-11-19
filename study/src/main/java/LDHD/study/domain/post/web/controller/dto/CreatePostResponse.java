package LDHD.study.domain.post.web.controller.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class CreatePostResponse { //응답으로 반환

    Long id;
    String title;
    String content;
    String category;

    public CreatePostResponse(Long id, String title, String content, String category) {

        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
    }

}
