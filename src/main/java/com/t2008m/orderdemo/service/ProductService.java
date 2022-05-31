package com.t2008m.orderdemo.service;

import com.t2008m.orderdemo.controller.OrderDetailApi;
import com.t2008m.orderdemo.entity.Product;
import com.t2008m.orderdemo.entity.dto.ItemDTO;
import com.t2008m.orderdemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderDetailApi orderDetailApi;


    public Page<Product> findAll(int page,int limit){
        return productRepository.findAll(PageRequest.of(page-1,limit));
    }

    public ItemDTO addToCart(ItemDTO itemDTO){
        return null;
    }
}
