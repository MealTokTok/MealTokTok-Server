package core.startup.mealtoktok.domain.fulldining.exception;

import static core.startup.mealtoktok.common.consts.MealTokTokConstant.NOT_FOUND;

import lombok.AllArgsConstructor;

import core.startup.mealtoktok.common.exception.BaseErrorCode;
import core.startup.mealtoktok.common.exception.ErrorReason;

@AllArgsConstructor
public enum FullDiningErrorCode implements BaseErrorCode {
    FULL_DINING_NOT_FOUND(NOT_FOUND, "FULL_DINING_404_1", "풀대접 정보를 찾지 못했습니다");

    FullDiningErrorCode(Integer status, String errorCode) {
        this.status = status;
        this.errorCode = errorCode;
    }

    private final Integer status;
    private final String errorCode;

    private String message;

    public BaseErrorCode setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
