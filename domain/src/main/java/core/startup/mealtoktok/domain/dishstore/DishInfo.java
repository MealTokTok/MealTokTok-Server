package core.startup.mealtoktok.domain.dishstore;

public record DishInfo (
        String dishName,
        String imgUrl
){
    public static DishInfo of(String dishName, String imgUrl){
        return new DishInfo(dishName, imgUrl);
    }
}
