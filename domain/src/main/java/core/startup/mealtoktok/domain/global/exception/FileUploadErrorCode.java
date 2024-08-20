package core.startup.mealtoktok.domain.global.exception;

import static core.startup.mealtoktok.common.consts.MealTokTokConstant.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.exception.BaseErrorCode;
import core.startup.mealtoktok.common.exception.ErrorReason;

@Getter
@RequiredArgsConstructor
public enum FileUploadErrorCode implements BaseErrorCode {
    FILE_UPLOAD_FAIL_ERROR(INTERNAL_SERVER, "FILE_500_1", "파일 업로드에 실패하였습니다.");

    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
