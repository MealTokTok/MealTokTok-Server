package core.startup.mealtoktok.domain.global;

public record TargetImage(Long imageId) {
    public static TargetImage from(Long imageId) {
        return new TargetImage(imageId);
    }
}
