package core.startup.mealtoktok.domain.user;

public record WithDrawReason(Long userId, String reason) {

    public static WithDrawReason of(Long userId, String reason) {
        return new WithDrawReason(userId, reason);
    }
}
