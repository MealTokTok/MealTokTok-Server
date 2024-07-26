package core.startup.mealtoktok.domain.alarm;

import core.startup.mealtoktok.domain.user.User;

public interface AlarmSender {
    void send(User user, Alarm alarm);
}
