package com.t2008m.orderdemo.service;

import com.t2008m.orderdemo.entity.OrderDetail;
import com.t2008m.orderdemo.entity.dto.ItemDTO;
import com.t2008m.orderdemo.repository.OrderDetailRepository;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    public Page<OrderDetail> findAll(int page,int limit){
        return orderDetailRepository.findAll(PageRequest.of(page-1,limit));
    }

//    public ItemDTO addToCart(ItemDTO itemDTO){
//        OrderDetail orderDetail = new OrderDetail();
//        return orderDetailRepository.addToCart(itemDTO);
//    }
}
