package LDHD.study.domain.bookmark;


import LDHD.study.domain.post.Post;
import LDHD.study.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.awt.print.Book;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Bookmark {

    @Id // PK로 등록
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Id값 자동 증가
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)  // 다대일 관계 설정
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE) // User가 삭제될 경우 해당 즐겨찾기 기록 자동 삭제
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) // 다대일 관계 설정
    @JoinColumn(name = "post_id")
    @OnDelete(action = OnDeleteAction.CASCADE) // Post가 삭제될 경우 해당 즐겨찾기 기록 자동 삭제
    private Post post;

    @Column
    LocalDateTime created_at; // 즐겨찾기 생성 시간 기록

    @Column
    LocalDateTime updated_at; // 즐겨찾기 수정 시간 기록

    public Bookmark(User user, Post post) {

        this.user = user;
        this.post = post;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }

}
