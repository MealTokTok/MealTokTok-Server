package core.startup.mealtoktok.infra.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import core.startup.mealtoktok.domain.order.Orderer;
import core.startup.mealtoktok.infra.order.entity.OrderEntity;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByOrderer(Orderer orderer);
}
