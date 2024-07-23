package core.startup.mealtoktok.domain.auth.exception;

import core.startup.mealtoktok.common.exception.BaseErrorCode;
import core.startup.mealtoktok.common.exception.ErrorReason;
import lombok.RequiredArgsConstructor;

import static core.startup.mealtoktok.common.consts.MealTokTokConstant.NOT_FOUND;
import static core.startup.mealtoktok.common.consts.MealTokTokConstant.UNAUTHORIZED;

@RequiredArgsConstructor
public enum AuthErrorCode implements BaseErrorCode {

    INVALID_TOKEN(UNAUTHORIZED, "AUTH_1", "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(UNAUTHORIZED, "AUTH_2", "만료된 토큰입니다."),
    NO_MATCHED_KID(NOT_FOUND, "AUTH_3", "매칭되는 키가 없습니다.");

    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
