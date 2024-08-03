package core.startup.mealtoktok.domain.user.exception;

import static core.startup.mealtoktok.common.consts.MealTokTokConstant.*;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.exception.BaseErrorCode;
import core.startup.mealtoktok.common.exception.ErrorReason;

@RequiredArgsConstructor
public enum UserErrorCode implements BaseErrorCode {
    USER_NOT_FOUND(NOT_FOUND, "USER_404_1", "유저의 정보를 찾지 못했습니다."),
    ALREADY_EXIST_ADDRESS(BAD_REQUEST, "USER_400_1", "이미 등록된 주소입니다."),
    REMOVED_USER(BAD_REQUEST, "USER_400_2", "탈퇴한 유저입니다.");

    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
