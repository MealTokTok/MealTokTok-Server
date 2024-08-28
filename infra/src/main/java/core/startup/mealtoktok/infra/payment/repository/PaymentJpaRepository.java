package core.startup.mealtoktok.infra.payment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import core.startup.mealtoktok.infra.payment.entity.PaymentEntity;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, Long> {

    @Query("SELECT p FROM PaymentEntity p WHERE p.orderId = :orderId")
    Optional<PaymentEntity> findByOrderId(String orderId);
}
