package core.startup.mealtoktok.domain.dish.exception;

import core.startup.mealtoktok.common.exception.BaseErrorCode;
import core.startup.mealtoktok.common.exception.ErrorReason;
import lombok.RequiredArgsConstructor;

import static core.startup.mealtoktok.common.consts.MealTokTokConstant.NOT_FOUND;

@RequiredArgsConstructor
public enum DishErrorCode implements BaseErrorCode {

    DISH_NOT_FOUND(NOT_FOUND, "DISH_404_1", "반찬의 정보를 찾지 못했습니다.");

    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
