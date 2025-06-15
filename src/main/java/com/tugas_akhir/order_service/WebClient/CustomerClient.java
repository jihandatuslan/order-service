package com.tugas_akhir.order_service.WebClient;

import com.tugas_akhir.order_service.dto.Customer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface CustomerClient {

    @GetExchange("/api/customers/{id}")
    public Customer findById(@PathVariable ("id") Long id);
}
