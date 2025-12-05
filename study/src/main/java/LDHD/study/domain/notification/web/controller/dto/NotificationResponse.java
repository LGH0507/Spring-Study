package LDHD.study.domain.notification.web.controller.dto;


import LDHD.study.domain.notification.Notification;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NotificationResponse {

    Long id;
    Long sender_id;
    Long targetId;
    String content;
    Boolean isRead;
    LocalDateTime created_at;

    public static NotificationResponse from (Notification notification) {
        return NotificationResponse.builder()
                .id(notification.getId())
                .sender_id(notification.getSender().getId())
                .targetId(notification.getTargetId())
                .content(notification.getContent())
                .isRead(notification.isRead())
                .created_at(notification.getCreated_at())
                .build();
    }


}
