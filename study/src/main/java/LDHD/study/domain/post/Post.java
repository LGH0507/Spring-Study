package LDHD.study.domain.post;


import LDHD.study.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter //get()메서드 자동 생성
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity //엔티티로 선언
public class Post {  // 제목,작성자,카테고리,본문,작성일자,

    @Id //pk값 지정
    @GeneratedValue //Id값 자동으로 증가
    private Long id;

    @Column
    String title;

    @ManyToOne(fetch = FetchType.LAZY)  //다대일 관계 지정
    @JoinColumn(name = "user_id")//작성자 이름(참조하는 외래키 이름)
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

