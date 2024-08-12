package core.startup.mealtoktok.domain.order.exception;

import static core.startup.mealtoktok.common.consts.MealTokTokConstant.BAD_REQUEST;
import static core.startup.mealtoktok.common.consts.MealTokTokConstant.NOT_FOUND;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.exception.BaseErrorCode;
import core.startup.mealtoktok.common.exception.ErrorReason;

@RequiredArgsConstructor
public enum OrderErrorCode implements BaseErrorCode {
    ORDER_NOT_FOUND(NOT_FOUND, "ORDER_404_1", "주문 정보를 찾지 못했습니다."),
    ORDERER_NOT_MATCH(BAD_REQUEST, "ORDER_400_1", "주문자가 일치하지 않습니다.");

    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
