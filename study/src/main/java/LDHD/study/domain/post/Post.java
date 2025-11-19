package LDHD.study.domain.post;


import LDHD.study.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post {  // 제목,작성자,카테고리,본문,작성일자,

    @Id
    @GeneratedValue
    private Long id;

    @Column
    String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")//작성자 이름
    User user;

    @Column
    String category;

    @Column
    String content;

    @Column
    LocalDateTime created_at;

    public Post(String  title, User user, String category, String content, LocalDateTime created_at) {
        this.title = title;
        this.user = user;
        this.category = category;
        this.content = content;
        this.created_at = LocalDateTime.now();
    }

}

