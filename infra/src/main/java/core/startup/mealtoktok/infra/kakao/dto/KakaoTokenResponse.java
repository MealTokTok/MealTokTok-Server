package core.startup.mealtoktok.infra.kakao.dto;

public record KakaoTokenResponse(String access_token, String refresh_token, String id_token) {}
