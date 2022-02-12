package com.example.buyshoes.entities;

import com.example.buyshoes.utils.DateAuditUtil;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Table(name = "shoesOrder")
@Data
@Entity
public class ShoesOrder extends DateAuditUtil {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "totalPrice")
    private int totalPrice;

    @Column(name = "state")
    private int state;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @OneToMany
    @JoinColumn(name = "shoesOrder_id")
    Set<OrderDetail> orderDetails;
}