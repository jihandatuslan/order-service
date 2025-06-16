package com.tugas_akhir.order_service.WebClient;

import com.tugas_akhir.order_service.dto.product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface ProductClient {

    @GetExchange("/api/products/{id}")
    public product findById(@PathVariable("id") Long id);
}
