package com.t2008m.orderdemo.controller;


import com.t2008m.orderdemo.entity.OrderDetail;
import com.t2008m.orderdemo.entity.dto.ItemDTO;
import com.t2008m.orderdemo.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/orderDetails")
@CrossOrigin
public class OrderDetailApi {

    @Autowired
    OrderDetailService orderDetailService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<OrderDetail>> findAll(@RequestParam(defaultValue = "1") int page,
                                                     @RequestParam(defaultValue = "10") int limit){
        return  ResponseEntity.ok(orderDetailService.findAll(page,limit));
    }

//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<ItemDTO> save(@RequestBody ItemDTO itemDTO){
//        return ResponseEntity.ok(orderDetailService.addToCart(itemDTO));
//    }
}
