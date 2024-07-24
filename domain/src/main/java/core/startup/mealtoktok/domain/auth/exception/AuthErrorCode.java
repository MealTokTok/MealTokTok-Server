package core.startup.mealtoktok.domain.auth.exception;

import core.startup.mealtoktok.common.exception.BaseErrorCode;
import core.startup.mealtoktok.common.exception.ErrorReason;
import lombok.RequiredArgsConstructor;

import static core.startup.mealtoktok.common.consts.MealTokTokConstant.*;
import static core.startup.mealtoktok.common.consts.MealTokTokConstant.BAD_REQUEST;

@RequiredArgsConstructor
public enum AuthErrorCode implements BaseErrorCode {

    INVALID_TOKEN(UNAUTHORIZED, "AUTH_401_1", "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(UNAUTHORIZED, "AUTH_401_2", "만료된 토큰입니다."),
    UNAUTHORIZED_USER(UNAUTHORIZED, "AUTH_401_3,", "인증되지 않은 사용자 입니다."),
    NO_MATCHED_KID(NOT_FOUND, "AUTH_404_1", "매칭되는 키가 없습니다."),
    OTHER_SERVER_BAD_REQUEST(BAD_REQUEST, "AUTH_OTHER_400", "Other server bad request"),
    OTHER_SERVER_UNAUTHORIZED(BAD_REQUEST, "AUTH_OTHER_400", "Other server unauthorized"),
    OTHER_SERVER_FORBIDDEN(BAD_REQUEST, "AUTH_OTHER_400", "Other server forbidden"),
    OTHER_SERVER_EXPIRED_TOKEN(BAD_REQUEST, "AUTH_OTHER_400", "Other server expired token"),
    OTHER_SERVER_NOT_FOUND(BAD_REQUEST, "AUTH_OTHER_400", "Other server not found error"),
    OTHER_SERVER_INTERNAL_SERVER_ERROR(BAD_REQUEST, "FEIGN_400_6", "Other server internal server error");

    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
