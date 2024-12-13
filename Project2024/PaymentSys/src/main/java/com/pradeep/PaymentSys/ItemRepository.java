package com.pradeep.PaymentSys;

import com.pradeep.PaymentSys.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
