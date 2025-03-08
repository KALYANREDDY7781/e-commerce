package com.myorg.Order.repository;

import com.myorg.Order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    List<Order> findByCustomerId(int customerId);
}
