package com.tugas_akhir.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineResponse {
    private Long id;
    private product product;
    private int quantity;
    private double price;
}
