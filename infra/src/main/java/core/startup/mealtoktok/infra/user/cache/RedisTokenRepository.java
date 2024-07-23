package core.startup.mealtoktok.infra.user.cache;

import core.startup.mealtoktok.domain.auth.RefreshToken;
import core.startup.mealtoktok.domain.user.TargetUser;
import core.startup.mealtoktok.domain.auth.TokenRepository;
import core.startup.mealtoktok.infra.user.config.RedisConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RedisTokenRepository implements TokenRepository {

    private final RedisTemplate<String, String> banTokenRedisTemplate;
    private final RedisTemplate<String, RefreshToken> tokenRedisTemplate;

    @Override
    public void setRefreshToken(RefreshToken refreshToken) {
        String key = getKey(refreshToken.userId());
        log.info("Set Refresh Token from {} : {}", key, refreshToken);
        tokenRedisTemplate.opsForValue().set(key, refreshToken);
    }

    @Override
    public Optional<RefreshToken> getRefreshToken(TargetUser targetUser) {
        String key = getKey(targetUser.userId());
        RefreshToken token = tokenRedisTemplate.opsForValue().get(key);
        log.info("Get Refresh Token from {} : {}", key, token);
        return Optional.ofNullable(token);
    }

    @Override
    public void ban(String token) {
        banTokenRedisTemplate.opsForSet().add(RedisConfig.BAN_TOKEN_KEY, token);
    }

    @Override
    public boolean isAlreadyLogin(String token) {
        return Boolean.FALSE.equals(banTokenRedisTemplate.opsForSet().isMember(RedisConfig.BAN_TOKEN_KEY, token));
    }

    public String getKey(Long userId) {
        return "REFRESH_TOKEN:" + userId;
    }
}
