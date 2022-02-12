package com.example.buyshoes.entities;

import com.example.buyshoes.utils.DateAuditUtil;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;



@Table(name = "category")
@Entity
@Data
public class Category extends DateAuditUtil {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinColumn(name = "category_id")
    private Set<ShoesProduct> products;


}
