package LDHD.study.domain.notification;


import LDHD.study.domain.post.Post;
import LDHD.study.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receiver_id") // 알림 받는 사용자
    private User receiver;

    @ManyToOne
    @JoinColumn(name = "sender_id") // 알림 유발 사용자
    private User sender;

    @Column
    private Long targetId; // 알림이 관련된 대상 Id (ex. 댓글이나 즐겨찾기에 추가된 게시물 Id)

    @Column
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @Column
    private String content;

    @Column
    private boolean isRead = false; // 알림이 읽혔는지 확인

    @Column
    LocalDateTime created_at;

    public Notification(Long id, User receiver, User sender, Long targetId, NotificationType notificationType, String content, boolean isRead) {
        this.id = id;
        this.receiver = receiver;
        this.sender = sender;
        this.targetId = targetId;
        this.notificationType = notificationType;
        this.content = content;
        this.isRead = isRead;
        this.created_at = LocalDateTime.now();
    }


}
