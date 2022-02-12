package com.example.buyshoes.entities;


import com.example.buyshoes.utils.DateAuditUtil;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;



@Table(name = "shoesProduct")
@Data
@Entity
public class ShoesProduct extends DateAuditUtil {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image")
    @Lob
    private byte[] image;

    @Column(name = "name")
    private String name;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany
    @JoinColumn(name = "shoesProduct_id")
    Set<OrderDetail> orderDetails;

    @Column(name = "price")
    private float price;

    @Column(name = "amount")
    private int amount;

    @Column(name = "detail")
    private String detail;
}
