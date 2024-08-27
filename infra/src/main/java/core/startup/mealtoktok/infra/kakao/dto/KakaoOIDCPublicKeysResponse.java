package core.startup.mealtoktok.infra.kakao.dto;

import java.util.List;

import core.startup.mealtoktok.domain.auth.OIDCPublicKey;

public record KakaoOIDCPublicKeysResponse(List<OIDCPublicKey> keys) {}
