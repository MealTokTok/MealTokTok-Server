package core.startup.mealtoktok.domain.dish;

public record TargetDish (
        Long dishId
) {
    public static TargetDish from(Long dishId){
        return new TargetDish(dishId);
    }
}


