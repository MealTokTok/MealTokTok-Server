package core.startup.mealtoktok.domain.DishStore;

public record TargetDishStore(
        Long storeId
) {
    public static TargetDishStore from(Long storeId){
        return new TargetDishStore(storeId);
    }
}
