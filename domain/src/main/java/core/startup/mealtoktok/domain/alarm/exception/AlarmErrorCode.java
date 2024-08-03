package core.startup.mealtoktok.domain.alarm.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.exception.BaseErrorCode;
import core.startup.mealtoktok.common.exception.ErrorReason;

@Getter
@RequiredArgsConstructor
public enum AlarmErrorCode implements BaseErrorCode {
    ALARM_SEND_FAIL(500, "ALARM_500_1", "알람 전송에 실패했습니다.");

    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
