package core.startup.mealtoktok.infra.toss.dto;

public record TossCancelRequest(String cancelReason) {

    public static TossCancelRequest from(String cancelReason) {
        return new TossCancelRequest(cancelReason);
    }
}
