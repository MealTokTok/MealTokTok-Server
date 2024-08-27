package core.startup.mealtoktok.infra.meal.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMealEntity is a Querydsl query type for MealEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMealEntity extends EntityPathBase<MealEntity> {

    private static final long serialVersionUID = -1030071089L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMealEntity mealEntity = new QMealEntity("mealEntity");

    public final NumberPath<Long> mealId = createNumber("mealId", Long.class);

    public final StringPath mealName = createString("mealName");

    public final QMealOwnerVO mealOwner;

    public final SimplePath<core.startup.mealtoktok.domain.order.Money> mealPrice = createSimple("mealPrice", core.startup.mealtoktok.domain.order.Money.class);

    public QMealEntity(String variable) {
        this(MealEntity.class, forVariable(variable), INITS);
    }

    public QMealEntity(Path<? extends MealEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMealEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMealEntity(PathMetadata metadata, PathInits inits) {
        this(MealEntity.class, metadata, inits);
    }

    public QMealEntity(Class<? extends MealEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mealOwner = inits.isInitialized("mealOwner") ? new QMealOwnerVO(forProperty("mealOwner")) : null;
    }

}

