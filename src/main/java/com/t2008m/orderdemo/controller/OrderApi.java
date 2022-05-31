package com.t2008m.orderdemo.controller;

import com.t2008m.orderdemo.entity.Order;
import com.t2008m.orderdemo.entity.enums.OrderSimpleStatus;
import com.t2008m.orderdemo.entity.search.orders.FilterParameter;
import com.t2008m.orderdemo.entity.search.orders.OrderSpecification;
import com.t2008m.orderdemo.entity.search.orders.SearchCriteria;
import com.t2008m.orderdemo.entity.search.orders.SearchCriteriaOperator;
import com.t2008m.orderdemo.service.OrderService;
import com.t2008m.orderdemo.util.DataDateTimeHelper;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/orders")
public class OrderApi {

    final OrderService orderService;

    public OrderApi(OrderService orderService) {
        this.orderService = orderService;
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> findAllByOneObject(
            @RequestBody FilterParameter param) {
        Page<Order> result = this.orderService.findAll(param);
        return ResponseEntity.ok().body(result);
    }

}
