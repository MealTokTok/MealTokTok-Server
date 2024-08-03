package core.startup.mealtoktok.infra.notification;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import core.startup.mealtoktok.domain.alarm.Alarm;
import core.startup.mealtoktok.domain.alarm.AlarmSender;
import core.startup.mealtoktok.domain.user.User;
import core.startup.mealtoktok.infra.notification.exception.AlarmSendFailException;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;

@Component
@RequiredArgsConstructor
@Slf4j
public class FcmAlarmSender implements AlarmSender {

    private final FirebaseMessaging firebaseMessaging;

    @Override
    public void send(User user, Alarm alarm) {
        MulticastMessage message = makeMessage(user, alarm);
        try {
            firebaseMessaging.sendEachForMulticast(message);
        } catch (FirebaseMessagingException e) {
            log.error("userId : {} 님에게 알람 전송을 실패하였습니다.", user.getUserId(), e);
            throw AlarmSendFailException.EXCEPTION;
        }
    }

    private MulticastMessage makeMessage(User user, Alarm alarm) {
        Notification notification =
                Notification.builder().setTitle(alarm.title()).setBody(alarm.body()).build();

        return MulticastMessage.builder()
                .setNotification(notification)
                .addAllTokens(user.getDeviceTokens())
                .build();
    }
}
