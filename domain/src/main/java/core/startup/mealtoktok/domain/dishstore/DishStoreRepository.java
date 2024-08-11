package core.startup.mealtoktok.domain.dishstore;

import java.util.List;

public interface DishStoreRepository {

    DishStore findById(TargetDishStore targetStore);

    void save(DishStoreInfo dishStoreInfo);

    boolean existsByDishStoreName(String dishStoreName);

    void delete(TargetDishStore targetDishStore);

    void update(TargetDishStore targetDishStore, DishStoreInfo dishStoreInfo);

    List<DishStore> findAll();
}
