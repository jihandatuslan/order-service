package com.tugas_akhir.order_service.controller;

import com.tugas_akhir.order_service.dto.OrderResponse;
import com.tugas_akhir.order_service.entity.Order;
import com.tugas_akhir.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public OrderResponse findById(@PathVariable("id") Long id){
        return orderService.findById(id);
    }
}
