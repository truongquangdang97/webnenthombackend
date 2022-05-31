package com.t2008m.orderdemo.service;

import com.t2008m.orderdemo.entity.Order;
import com.t2008m.orderdemo.entity.search.FilterParameter;
import com.t2008m.orderdemo.entity.search.orders.OrderSpecification;
import com.t2008m.orderdemo.entity.search.orders.SearchCriteria;
import com.t2008m.orderdemo.entity.search.orders.SearchCriteriaOperator;
import com.t2008m.orderdemo.entity.x.CartItem;
import com.t2008m.orderdemo.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {
    @Autowired
    CartItemRepository cartItemRepository;

    public Page<CartItem> findAll(int page,int limit){
        return cartItemRepository.findAll(PageRequest.of(page-1,limit));
    }

    public List<CartItem> findByShoppingCartId(String shoppingCartId){
        return cartItemRepository.findAllById_ShoppingCartId(shoppingCartId);
    }

    public Optional<CartItem> findById(String id){
        return cartItemRepository.findById(id);
    }

//    public Page<CartItem> getAll(FilterParameter param){
//        Specification<CartItem> specification = Specification.where(null);
//
//        if (param.getNameUser()!=null&& param.getNameUser().length() > 0){
//        }
//    }

//    public Optional<CartItem> findByShoppingId(String id){
//        return cartItemRepository.findById_ShoppingCartId(id);
//    }
}
