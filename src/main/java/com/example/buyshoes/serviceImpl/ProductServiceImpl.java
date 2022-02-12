package com.example.buyshoes.serviceImpl;


import com.example.buyshoes.dto.ProductDto;
import com.example.buyshoes.entities.Category;
import com.example.buyshoes.entities.ShoesProduct;
import com.example.buyshoes.entities.User;
import com.example.buyshoes.repository.CategoryRepository;
import com.example.buyshoes.repository.ProductRepository;
import com.example.buyshoes.service.ProductService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Page<ShoesProduct> listAll(int pageNum, String sortField, String sortDir) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );

        return productRepository.findAll(pageable);
    }

    @Override
    public Page<ShoesProduct> listAll(String category, int pageNum) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);

        if(category != null && !category.trim().isEmpty()){
            String categorId = categoryRepository.getCategoryIdByName(category);
            return productRepository.findAllByCategoryId(Long.parseLong(categorId),pageable);
        } else {
            return productRepository.findAll(pageable);
        }

    }

    @Override
    public void showPhoto(HttpServletResponse response, Long id) throws IOException {
        response.setContentType("image/jpeg");
        ShoesProduct shoesProduct = getShoesProduct(id);
        response.setHeader("Content-Disposition", "inline;filename=\""+ shoesProduct.getName() + "\"");
        response.setContentType("image/jpeg");
        byte[] bytes = shoesProduct.getImage();
        InputStream inputStream = new ByteArrayInputStream(bytes);
        IOUtils.copy(inputStream, response.getOutputStream());
    }

    @Override
    public ShoesProduct getShoesProduct(Long id) {
        ShoesProduct shoesProduct = productRepository.getById(id);
        return shoesProduct;
    }

    @Override
    public ShoesProduct save(ProductDto shoesProductDto) {
        // TODO Auto-generated method stub

        ShoesProduct shoesProduct = new ShoesProduct();
        byte[] image;
        if(shoesProductDto.getId()!=null){
            shoesProduct = productRepository.findById(shoesProductDto.getId()).orElse(new ShoesProduct());
            shoesProduct.setUpdatedAt(new Date());
            if(shoesProductDto.getImage()==null||shoesProductDto.getImage().getSize()==0) {
                image = (productRepository.findById(shoesProduct.getId())).get().getImage();
                shoesProduct.setImage(image);
            }
            else {
                image = shoesProductDto.getImage().getBytes();
                shoesProduct.setImage(image);
            }
        } else {
            if(shoesProductDto.getImage()!=null&&shoesProductDto.getImage().getSize()!=0) {
                image = shoesProductDto.getImage().getBytes();
                shoesProduct.setImage(image);
            }
            shoesProduct.setCreatedAt(new Date());
            shoesProduct.setUpdatedAt(new Date());
        }
        Category category = categoryRepository.findByName(shoesProductDto.getCategoryName()).orElse(null);
        shoesProduct.setName(shoesProductDto.getName());
        shoesProduct.setCategory(category);
        shoesProduct.setAmount(shoesProductDto.getAmount());
        shoesProduct.setDetail(shoesProductDto.getDetail());
        shoesProduct.setPrice(shoesProductDto.getPrice());
        return productRepository.save(shoesProduct);
    }
}
