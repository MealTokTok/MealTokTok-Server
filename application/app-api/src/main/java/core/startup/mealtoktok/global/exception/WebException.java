package core.startup.mealtoktok.global.exception;

import core.startup.mealtoktok.common.exception.ApiException;
import core.startup.mealtoktok.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class WebException extends ApiException {

    public WebException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public WebException(ErrorCode errorCode) {
        super(errorCode, null);
    }
}
