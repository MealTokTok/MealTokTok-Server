package core.startup.mealtoktok.common.exception;

import core.startup.mealtoktok.common.consts.MealTokTokConstant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static core.startup.mealtoktok.common.consts.MealTokTokConstant.*;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INVALID_TOKEN(UNAUTHORIZED,"AUTH_1", "유효하지 않은 토큰입니다.");

    private final Integer status;
    private final String code;
    private final String message;
}
