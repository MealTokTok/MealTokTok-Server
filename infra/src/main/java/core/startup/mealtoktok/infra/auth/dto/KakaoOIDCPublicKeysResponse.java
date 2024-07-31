package core.startup.mealtoktok.infra.auth.dto;

import java.util.List;

import core.startup.mealtoktok.domain.auth.OIDCPublicKey;

public record KakaoOIDCPublicKeysResponse(List<OIDCPublicKey> keys) {}
