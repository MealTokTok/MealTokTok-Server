package core.startup.mealtoktok.infra.dishstore.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDishEntity is a Querydsl query type for DishEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDishEntity extends EntityPathBase<DishEntity> {

    private static final long serialVersionUID = -2005485960L;

    public static final QDishEntity dishEntity = new QDishEntity("dishEntity");

    public final NumberPath<Long> dishCategoryId = createNumber("dishCategoryId", Long.class);

    public final NumberPath<Long> dishId = createNumber("dishId", Long.class);

    public final StringPath dishName = createString("dishName");

    public final SimplePath<core.startup.mealtoktok.common.dto.Money> dishPrice = createSimple("dishPrice", core.startup.mealtoktok.common.dto.Money.class);

    public final NumberPath<Long> dishStoreId = createNumber("dishStoreId", Long.class);

    public final StringPath imgUrl = createString("imgUrl");

    public final BooleanPath isSoldOut = createBoolean("isSoldOut");

    public QDishEntity(String variable) {
        super(DishEntity.class, forVariable(variable));
    }

    public QDishEntity(Path<? extends DishEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDishEntity(PathMetadata metadata) {
        super(DishEntity.class, metadata);
    }

}

