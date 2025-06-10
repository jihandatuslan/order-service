package com.tugas_akhir.order_service.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(length = 10, nullable = false, unique = true)
    private String orderNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Column(length = 10, nullable = false)
    private Long customerId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderLine> orderLines;
}
