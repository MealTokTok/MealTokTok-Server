package core.startup.mealtoktok.infra.user.cache;

import core.startup.mealtoktok.domain.user.TargetUser;
import core.startup.mealtoktok.domain.user.User;
import core.startup.mealtoktok.domain.user.UserCacheRepository;
import core.startup.mealtoktok.infra.user.config.RedisConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RedisUserRepository implements UserCacheRepository {

    private final RedisTemplate<String, User> redisTemplate;

    public void cache(User user) {
        String key = getKey(user.getUserId());
        log.info("Set UserEntity from {} : {}", key, user.getUsername());
        redisTemplate.opsForValue().setIfAbsent(key, user, RedisConfig.USER_CACHE_TTL);
    }

    public Optional<User> getUser(TargetUser targetUser) {
        String key = getKey(targetUser.userId());
        Optional<User> user = Optional.ofNullable(redisTemplate.opsForValue().get(key));
        user.ifPresentOrElse(
                u -> log.info("Get User from Cache - {} : {}", key, u.getUsername()),
                () -> log.info("No User Cache - {}", key)
        );
        return user;
    }

    public void deleteUser(User user) {
        String key = getKey(user.getUserId());
        redisTemplate.delete(key);
        log.info("유저 캐싱 폐기 완료 - {}", key);
    }

    public String getKey(Long userId) {
        return "USER:" + userId;
    }

}
