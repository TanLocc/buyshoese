package com.example.buyshoes.dto;

import com.example.buyshoes.entities.Category;
import com.example.buyshoes.entities.OrderDetail;
import lombok.Data;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.persistence.*;
import java.util.Set;

@Data
public class ProductDto {
    private Long id;
    private CommonsMultipartFile image;
    private String name;
    private float price;
    private int amount;
    private String detail;
    String categoryName;
}
