package core.startup.mealtoktok.domain.meal.exception;

import static core.startup.mealtoktok.common.consts.MealTokTokConstant.NOT_FOUND;

import core.startup.mealtoktok.common.exception.BaseErrorCode;
import core.startup.mealtoktok.common.exception.ErrorReason;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MealErrorCode implements BaseErrorCode {
    MEAL_NOT_FOUND(NOT_FOUND, "MEAL_404_1", "도시락의 정보를 찾지 못했습니다.");

    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
