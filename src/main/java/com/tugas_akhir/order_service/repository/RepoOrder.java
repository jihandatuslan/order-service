package com.tugas_akhir.order_service.repository;

import com.tugas_akhir.order_service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoOrder extends JpaRepository<Order, Long> {

    Order findByOrderNumber(String orderNumber);
}
