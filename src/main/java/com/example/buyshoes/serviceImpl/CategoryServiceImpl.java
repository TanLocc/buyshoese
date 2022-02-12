package com.example.buyshoes.serviceImpl;

import com.example.buyshoes.repository.CategoryRepository;
import com.example.buyshoes.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<String> getCategoryList(){
        return categoryRepository.getCategoryNames();
    }
}
