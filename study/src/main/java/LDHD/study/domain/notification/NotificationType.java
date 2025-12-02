package LDHD.study.domain.notification;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum NotificationType {

    COMMENT_ON_POST("새로운 댓글"),
    BOOKMARK_ON_POST("즐겨찾기에 등록");

    private String description;
    NotificationType(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
