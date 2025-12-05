package LDHD.study.domain.notification.repository;

import LDHD.study.domain.notification.Notification;
import LDHD.study.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByReceiverOrderByCreated_at(User receiver); // 알림을 최신순으로 조회 가능
}
