package core.startup.mealtoktok.infra.notification.exception;

import core.startup.mealtoktok.common.exception.InfraException;
import core.startup.mealtoktok.domain.alarm.exception.AlarmErrorCode;

public class AlarmSendFailException extends InfraException {

    public static final AlarmSendFailException EXCEPTION = new AlarmSendFailException();

    private AlarmSendFailException() {
        super(AlarmErrorCode.ALARM_SEND_FAIL);
    }
}
