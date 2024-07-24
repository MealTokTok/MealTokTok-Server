package core.startup.mealtoktok.domain.DishStore.exception;

import core.startup.mealtoktok.common.exception.BaseErrorCode;
import core.startup.mealtoktok.common.exception.ErrorReason;
import lombok.RequiredArgsConstructor;

import static core.startup.mealtoktok.common.consts.MealTokTokConstant.NOT_FOUND;

@RequiredArgsConstructor
public enum DishStoreErrorCode implements BaseErrorCode{

    DISH_STORE_NOT_FOUND(NOT_FOUND, "DISH_STORE_404_1", "반찬 가게의 정보를 찾지 못했습니다.");

    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
