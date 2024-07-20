package core.startup.mealtoktok.domain.user;

public record TargetUser(
        long userId
) {
    public static TargetUser from(long userId) {
        return new TargetUser(userId);
    }

}
