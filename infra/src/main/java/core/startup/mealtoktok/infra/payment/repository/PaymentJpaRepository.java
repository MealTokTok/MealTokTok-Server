package core.startup.mealtoktok.infra.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import core.startup.mealtoktok.infra.payment.entity.PaymentEntity;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, Long> {}
