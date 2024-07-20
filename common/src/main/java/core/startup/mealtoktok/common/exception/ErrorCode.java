package core.startup.mealtoktok.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    TOKEN_REISSUED(HttpStatus.CREATED, "토큰이 재발급되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
