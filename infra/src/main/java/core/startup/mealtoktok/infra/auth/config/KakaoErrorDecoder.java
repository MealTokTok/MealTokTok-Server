package core.startup.mealtoktok.infra.auth.config;

import core.startup.mealtoktok.infra.auth.exception.OtherServerBadRequestException;
import core.startup.mealtoktok.infra.auth.exception.OtherServerExpiredTokenException;
import core.startup.mealtoktok.infra.auth.exception.OtherServerForbiddenException;
import core.startup.mealtoktok.infra.auth.exception.OtherServerUnauthorizedException;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class KakaoErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() >= 400) {
            switch (response.status()) {
                case 401:
                    throw OtherServerUnauthorizedException.EXCEPTION;
                case 403:
                    throw OtherServerForbiddenException.EXCEPTION;
                case 419:
                    throw OtherServerExpiredTokenException.EXCEPTION;
                default:
                    throw OtherServerBadRequestException.EXCEPTION;
            }
        }

        return FeignException.errorStatus(methodKey, response);
    }
}
