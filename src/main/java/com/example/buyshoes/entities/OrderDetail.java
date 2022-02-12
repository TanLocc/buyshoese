package com.example.buyshoes.entities;

import com.example.buyshoes.utils.DateAuditUtil;
import lombok.Data;

import javax.persistence.*;

@Table(name = "orderDetail")
@Data
@Entity
public class OrderDetail extends DateAuditUtil {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private float amount;

    @Column(name = "prices")
    private float prices;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "shoesOrder_id")
    ShoesOrder order;

    @ManyToOne
    @JoinColumn(name = "shoesProduct_id")
    ShoesProduct product;
}
