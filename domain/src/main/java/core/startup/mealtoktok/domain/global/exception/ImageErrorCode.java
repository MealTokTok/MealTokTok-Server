package core.startup.mealtoktok.domain.global.exception;

import static core.startup.mealtoktok.common.consts.MealTokTokConstant.NOT_FOUND;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.exception.BaseErrorCode;
import core.startup.mealtoktok.common.exception.ErrorReason;

@Getter
@RequiredArgsConstructor
public enum ImageErrorCode implements BaseErrorCode {
    IMAGE_NOT_FOUND(NOT_FOUND, "Image_404_1", "이미지 정보를 찾지 못했습니다.");

    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
