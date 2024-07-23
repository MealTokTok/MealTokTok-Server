package core.startup.mealtoktok.infra.auth.dto;

import core.startup.mealtoktok.domain.auth.OIDCPublicKey;

import java.util.List;

public record KakaoOIDCPublicKeysResponse(
        List<OIDCPublicKey> keys
){
}
