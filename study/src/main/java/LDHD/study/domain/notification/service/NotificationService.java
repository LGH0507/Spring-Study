package LDHD.study.domain.notification.service;


import LDHD.study.domain.notification.Notification;
import LDHD.study.domain.notification.repository.NotificationRepository;
import LDHD.study.domain.notification.web.controller.dto.NotificationResponse;
import LDHD.study.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    // 알림 생성
    public NotificationResponse CreateNotification(Notification notification){

    }

    //알림 목록 조회
    public List<NotificationResponse> GetNotification;

}
