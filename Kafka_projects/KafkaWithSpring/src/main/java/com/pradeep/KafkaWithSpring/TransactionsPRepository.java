package com.pradeep.KafkaWithSpring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsPRepository extends JpaRepository<TransactionsP, Long> {
}
