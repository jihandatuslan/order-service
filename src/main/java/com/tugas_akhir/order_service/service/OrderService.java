package com.tugas_akhir.order_service.service;

import com.tugas_akhir.order_service.dto.Customer;
import com.tugas_akhir.order_service.dto.OrderLineResponse;
import com.tugas_akhir.order_service.dto.OrderResponse;
import com.tugas_akhir.order_service.dto.product;
import com.tugas_akhir.order_service.entity.Order;
import com.tugas_akhir.order_service.entity.OrderLine;
import com.tugas_akhir.order_service.repository.RepoOrder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private RepoOrder repoOrder;

    @Autowired
    private RestTemplate restTemplate;

    public Order save(Order order) {
        for (OrderLine orderLine : order.getOrderLines()) {
            orderLine.setOrder(order);
        }
        return repoOrder.save(order);
    }

    public OrderResponse findById(Long id) {
        Optional<Order> optOrder = repoOrder.findById(id);
        if (!optOrder.isPresent()) {
            return null;
        }

        Order order = optOrder.get();
        OrderResponse response = new OrderResponse(order.getId(), order.getOrderNumber(), order.getOrderDate(), findCustomerById(order.getCustomerId()), new ArrayList<OrderLineResponse>());

        for (OrderLine orderLine : order.getOrderLines()) {
            product product= findProductById(orderLine.getProductId());
            response.getOrderLines().add(new OrderLineResponse(orderLine.getId(), product, orderLine.getQuantity(), orderLine.getPrice()));
        }
        return response;
    }

    public OrderResponse findByOrderNumber(String orderNumber) {
        Order order = repoOrder.findByOrderNumber(orderNumber);
        if (order==null) {
            return null;
        }

        OrderResponse response = new OrderResponse(order.getId(), order.getOrderNumber(), order.getOrderDate(), findCustomerById(order.getCustomerId()), new ArrayList<OrderLineResponse>());

        for (OrderLine orderLine : order.getOrderLines()) {
            product product= findProductById(orderLine.getProductId());
            response.getOrderLines().add(new OrderLineResponse(orderLine.getId(), product, orderLine.getQuantity(), orderLine.getPrice()));
        }
        return response;
    }

    public Customer findCustomerById(Long id) {
        return restTemplate.getForObject("http://CUSTOMER-SERVICE/api/customers/" + id, Customer.class);
    }

    public product findProductById(Long id) {
        return restTemplate.getForObject("http://PRODUCT-SERVICE/api/products/" + id, product.class);
    }
}
