package com.example.buyshoes.dto;

import com.example.buyshoes.entities.Category;
import com.example.buyshoes.entities.OrderDetail;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
public class ProductResponseDto implements Serializable {
    private String id;
    private String name;
    private String categoryName;
    private String price;
    private String amount;
    private String detail;
}
