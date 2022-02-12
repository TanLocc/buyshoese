package com.example.buyshoes.service;

import com.example.buyshoes.dto.ProductDto;
import com.example.buyshoes.entities.ShoesProduct;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public interface ProductService {
    Page<ShoesProduct> listAll(int pageNum, String sortField, String sortDir) ;
    Page<ShoesProduct> listAll(String category, int pageNum);
    void showPhoto(HttpServletResponse response, Long id) throws IOException;
    public ShoesProduct getShoesProduct(Long id);
    public ShoesProduct save(ProductDto shoesProductDto);
}
