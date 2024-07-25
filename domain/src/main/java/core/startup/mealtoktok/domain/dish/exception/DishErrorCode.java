package core.startup.mealtoktok.domain.dish.exception;

import core.startup.mealtoktok.common.exception.BaseErrorCode;
import core.startup.mealtoktok.common.exception.ErrorReason;
import lombok.RequiredArgsConstructor;

import static core.startup.mealtoktok.common.consts.MealTokTokConstant.*;

@RequiredArgsConstructor
public enum DishErrorCode implements BaseErrorCode {

    DISH_NOT_FOUND(NOT_FOUND, "DISH_404_1", "반찬의 정보를 찾지 못했습니다."),
    DISH_NAME_ALREADY_EXISTS(CONFLICT, "DISH_409_1","이미 존재하는 반찬 이름입니다.");

    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
