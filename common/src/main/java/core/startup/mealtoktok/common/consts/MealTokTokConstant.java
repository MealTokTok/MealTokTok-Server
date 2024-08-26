package core.startup.mealtoktok.common.consts;

import java.time.LocalDateTime;

public class MealTokTokConstant {

    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int CONFLICT = 409;
    public static final int INTERNAL_SERVER = 500;

    public static final LocalDateTime VALID_DATE_TIME = LocalDateTime.now().minusDays(7); // 7일 이내

    public static final String BEARER = "Bearer ";
}
