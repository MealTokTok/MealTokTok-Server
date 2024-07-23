package core.startup.mealtoktok.common.exception;

import lombok.Getter;

@Getter
public class WebException extends CustomException {

    public WebException(BaseErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public WebException(BaseErrorCode errorCode) {
        super(errorCode, null);
    }
}
