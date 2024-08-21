package core.startup.mealtoktok.domain.mealdelivery.exception;

import static core.startup.mealtoktok.common.consts.MealTokTokConstant.NOT_FOUND;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.exception.BaseErrorCode;
import core.startup.mealtoktok.common.exception.ErrorReason;

@RequiredArgsConstructor
public enum MealDeliveryErrorCode implements BaseErrorCode {
    MEAL_DELIVERY_NOT_FOUND(NOT_FOUND, "MEAL_DELIVERY_404_1", "배송 정보를 찾지 못했습니다"),
    NEXT_MEAL_DELIVERY_NOT_FOUND(NOT_FOUND, "MEAL_DELIVERY_404_2", "다음 배송 정보를 찾지 못했습니다"),
    FULL_DINING_NOT_FOUND(NOT_FOUND, "FULL_DINING_404_1", "풀대접 정보를 찾지 못했습니다");

    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
