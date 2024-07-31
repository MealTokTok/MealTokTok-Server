package core.startup.mealtoktok.domain.user;

public record TargetUser(long userId) {
    public static TargetUser from(long userId) {
        return new TargetUser(userId);
    }

    public static TargetUser from(User user) {
        return new TargetUser(user.getUserId());
    }
}
