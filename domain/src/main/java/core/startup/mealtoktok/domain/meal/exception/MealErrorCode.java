package core.startup.mealtoktok.domain.meal.exception;

import static core.startup.mealtoktok.common.consts.MealTokTokConstant.CONFLICT;
import static core.startup.mealtoktok.common.consts.MealTokTokConstant.FORBIDDEN;
import static core.startup.mealtoktok.common.consts.MealTokTokConstant.NOT_FOUND;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.exception.BaseErrorCode;
import core.startup.mealtoktok.common.exception.ErrorReason;

@RequiredArgsConstructor
public enum MealErrorCode implements BaseErrorCode {
    MEAL_NOT_FOUND(NOT_FOUND, "MEAL_404_1", "도시락의 정보를 찾지 못했습니다."),
    INVALID_DISH_COUNT(CONFLICT, "MEAL_409_1", "도시락의 반찬 개수가 올바르지 않습니다."),
    MEAL_NAME_ALREADY_EXISTS(CONFLICT, "MEAL_409_2", "이미 존재하는 도시락 이름입니다."),
    MEAL_OWNER_NOT_MATCH(FORBIDDEN, "MEAL_403_1", "도시락의 소유자가 일치하지 않습니다.");

    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
