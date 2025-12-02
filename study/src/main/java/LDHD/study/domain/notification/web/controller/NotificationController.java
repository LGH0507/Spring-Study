package LDHD.study.domain.notification.web.controller;


import LDHD.study.common.response.GlobalResponse;
import LDHD.study.common.response.SuccessCode;
import LDHD.study.domain.notification.service.NotificationService;
import LDHD.study.domain.notification.web.controller.dto.NotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<GlobalResponse> getNotification(@RequestHeader("X-USER-ID") Long currentUserId){

        List<NotificationResponse> response = notificationService.getNotification(currentUserId);

        return GlobalResponse.onSuccess(SuccessCode.OK, response);
    }
}
