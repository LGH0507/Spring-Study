package LDHD.study.domain.post;

import LDHD.study.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @Column
    private LocalDateTime created_at;

    @Column
    private LocalDateTime updated_at;


    public Comment(Post post, User user, String content) {

        this.post = post; // 댓글이 속할 게시물 엔티티
        this.user = user; // 댓글 작성자 엔티티
        this.content = content;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();

    }
}
