package com.t2008m.orderdemo.controller;

import com.t2008m.orderdemo.entity.x.ShoppingCart;
import com.t2008m.orderdemo.entity.dto.ShoppingCartDTO;
import com.t2008m.orderdemo.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/shopping-cart")
public class ShoppingCartApi {

    @Autowired
    ShoppingCartService shoppingCartService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ShoppingCart> save(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(shoppingCartService.save(shoppingCartDTO));
    }
    @RequestMapping(method = RequestMethod.GET, path = "/get-all")
    public ResponseEntity<List<ShoppingCart>> findAll() {
        return ResponseEntity.ok(shoppingCartService.findAll());
    }



    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<ShoppingCart> findById(@PathVariable String id) {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartService.findById(id);

        if (!shoppingCartOptional.isPresent()) {
            ResponseEntity.notFound();
        }
        return ResponseEntity.ok(shoppingCartOptional.get());
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<ShoppingCart> findByUserId(@RequestParam(defaultValue = "") String userId) {
//
//        Optional<ShoppingCart> shoppingCartOptional = shoppingCartService.findByUserId(userId);
//
//        if (!shoppingCartOptional.isPresent()) {
//            ResponseEntity.notFound();
//        }
//
//        ShoppingCart existShoppingCart = shoppingCartOptional.get();
//
//        existShoppingCart.setCartItems(existShoppingCart.getCartItems().stream().filter(c -> c.getStatus() == 1).collect(Collectors.toSet()));
//
//        return ResponseEntity.ok(existShoppingCart);
//    }

    @RequestMapping(method = RequestMethod.GET)
    public Optional<ShoppingCart> findByUserId(@RequestParam(defaultValue = "") String userId) {
        return shoppingCartService.findByUserId(userId);
    }

}
