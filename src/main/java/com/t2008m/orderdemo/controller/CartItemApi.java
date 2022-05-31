package com.t2008m.orderdemo.controller;


import com.t2008m.orderdemo.entity.dto.CartItemDTO;
import com.t2008m.orderdemo.entity.dto.ShoppingCartDTO;
import com.t2008m.orderdemo.entity.x.CartItem;
import com.t2008m.orderdemo.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/cartItems")
@RestController
@CrossOrigin
public class CartItemApi {
    @Autowired
    CartItemService cartItemService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<CartItem>> findAll(@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "10") int limit){
        return ResponseEntity.ok(cartItemService.findAll(page,limit));
    }

    @RequestMapping(method = RequestMethod.GET,path = "/shoppingCartId")
    public ResponseEntity<List<CartItemDTO>> findByShoppingCartId(@RequestParam(defaultValue = "") String shoppingCartId){
        List<CartItem> cartItemList = cartItemService.findByShoppingCartId(shoppingCartId);
        List<CartItemDTO> cartItemDTOList = new ArrayList<>();

        for (CartItem item:
             cartItemList) {
            CartItemDTO cartItemDTO = new CartItemDTO();
            cartItemDTO.setProductId(item.getId().getProductId());
            cartItemDTO.setQuantity(item.getQuantity());
            cartItemDTOList.add(cartItemDTO);
        }

        return ResponseEntity.ok(cartItemDTOList);
    }




    @RequestMapping(method = RequestMethod.GET,path = "/id")
    public ResponseEntity<CartItem> findById(@RequestParam String id){
        Optional<CartItem> optionalCartItem = cartItemService.findById(id);
        if (!optionalCartItem.isPresent()){
            ResponseEntity.notFound();
        }
        return ResponseEntity.ok(optionalCartItem.get());
    }

//    @RequestMapping(method = RequestMethod.GET,path = "/shoppingId")
//    public ResponseEntity<CartItem> findByShoppingId(@RequestParam String id){
//        Optional<CartItem> optionalCartItem = cartItemService.findByShoppingId(id);
//        return ResponseEntity.ok(optionalCartItem.get());
//    }
}
