package com.example.buyshoes.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    public List<String> getCategoryList();
}
