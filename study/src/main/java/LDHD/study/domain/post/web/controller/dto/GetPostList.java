package LDHD.study.domain.post.web.controller.dto;


import LDHD.study.domain.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class GetPostList {

    Long postId;
    String title;
    String username;
    String category;
    LocalDateTime createdDate;

    public GetPostList(Post post) {

        this.postId = post.getId();
        this.title = post.getTitle();
        this.username = post.getUser().getName();
        this.category = post.getCategory();
        this.createdDate = post.getCreated_at();
    }
}
