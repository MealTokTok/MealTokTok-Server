package core.startup.mealtoktok.domain.dish;

public record DishDetails(
        String dishName,
        String imgUrl
) {
    public static DishDetails of(String dishName, String imgUrl) {
        return new DishDetails(dishName, imgUrl);
    }
}
