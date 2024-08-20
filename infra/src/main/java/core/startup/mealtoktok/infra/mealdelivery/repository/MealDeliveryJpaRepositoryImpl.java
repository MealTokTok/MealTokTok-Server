package core.startup.mealtoktok.infra.mealdelivery.repository;

import static core.startup.mealtoktok.infra.mealdelivery.entity.QMealDeliveryEntity.*;
import static core.startup.mealtoktok.infra.order.entity.QOrderEntity.*;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.mealdelivery.DeliveryState;
import core.startup.mealtoktok.domain.mealdelivery.MealDeliverySearchCond;
import core.startup.mealtoktok.domain.mealdelivery.Recipient;
import core.startup.mealtoktok.infra.mealdelivery.entity.MealDeliveryEntity;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
@RequiredArgsConstructor
public class MealDeliveryJpaRepositoryImpl implements MealDeliveryJpaRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<MealDeliveryEntity> search(
            Recipient recipient, MealDeliverySearchCond cond, Pageable pageable) {
        List<MealDeliveryEntity> contents =
                queryFactory
                        .selectFrom(mealDeliveryEntity)
                        .leftJoin(orderEntity)
                        .on(mealDeliveryEntity.orderId.eq(orderEntity.orderId))
                        .where(
                                orderEntity.orderer.userId.eq(recipient.userId()),
                                equalDeliveryState(cond.deliveryState()))
                        .orderBy(mealDeliveryEntity.reservedDate.desc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetch();

        JPAQuery<Long> count =
                queryFactory.select(mealDeliveryEntity.count()).from(mealDeliveryEntity);

        return PageableExecutionUtils.getPage(contents, pageable, count::fetchOne);
    }

    private BooleanExpression equalDeliveryState(DeliveryState deliveryState) {
        if (deliveryState == null) {
            return null;
        }

        return mealDeliveryEntity.deliveryState.eq(deliveryState);
    }
}
