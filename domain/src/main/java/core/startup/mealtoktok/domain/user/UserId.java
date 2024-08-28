package core.startup.mealtoktok.domain.user;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import core.startup.mealtoktok.common.domain.BaseId;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserId extends BaseId<Long> {

    public UserId(Long id) {
        super(id);
    }

    public static UserId from(Long id) {
        return new UserId(id);
    }
}
