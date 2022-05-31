package com.t2008m.orderdemo.service;


import com.t2008m.orderdemo.controller.CartItemApi;
import com.t2008m.orderdemo.entity.Product;
import com.t2008m.orderdemo.entity.dto.CartItemDTO;
import com.t2008m.orderdemo.entity.dto.ShoppingCartDTO;
import com.t2008m.orderdemo.entity.enums.CartItemStatus;
import com.t2008m.orderdemo.entity.x.CartItem;
import com.t2008m.orderdemo.entity.x.CartItemId;
import com.t2008m.orderdemo.entity.x.ShoppingCart;
import com.t2008m.orderdemo.repository.CartItemRepository;
import com.t2008m.orderdemo.repository.ProductRepository;
import com.t2008m.orderdemo.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    CartItemApi cartItemApi;
    @Autowired
    CartItemRepository cartItemRepository;

    public ShoppingCart save(ShoppingCartDTO shoppingCartDTO) {

        ShoppingCart shoppingCart = shoppingCartDTO.generateCart();/// đã tìm đc userId

        Optional<ShoppingCart> optionalShoppingCart = shoppingCartRepository.findByUserId(shoppingCartDTO.getUserId());
        if (optionalShoppingCart.isPresent()) {
            shoppingCart = optionalShoppingCart.get();
            shoppingCart.setTotalPrice(new BigDecimal(0));
        }
        Set<CartItem> setCartItem = new HashSet<>();
        for (CartItemDTO cartItemDTO :
                shoppingCartDTO.getCartItemDTOSet()) {
            Optional<Product> optionalProduct = productRepository.findById(cartItemDTO.getProductId());
            if (!optionalProduct.isPresent()) {
                break;
            }
            if (cartItemDTO.getQuantity() <= 0){
                cartItemDTO.setStatus(-1);
            }
            Product product = optionalProduct.get();

            CartItem cartItem = CartItem.builder()
                    .id(new CartItemId(shoppingCart.getId(), product.getId()))
                    .productName(product.getName())
                    .productThumbnail(product.getThumbnails())
                    .quantity(cartItemDTO.getQuantity())
                    .unitPrice(product.getPrice())
                    .shoppingCart(shoppingCart)
                    .status(cartItemDTO.getStatus())
                    .cartItemStatus(CartItemStatus.of(cartItemDTO.getStatus()))
                    .build();

//            Optional<CartItem> optionalCartItem = cartItemRepository.findById_ProductIdAndId_ShoppingCartId(product.getId(),shoppingCart.getId());
//            if (optionalCartItem.isPresent()){
//                cartItem = optionalCartItem.get();
//                cartItem.setQuantity(cartItem.getQuantity()+1);
//            }
            shoppingCart.addTotalPrice(cartItem);
            setCartItem.add(cartItem);
        }


        shoppingCart.setCartItems(setCartItem);
        return shoppingCartRepository.save(shoppingCart);
    }


    public List<ShoppingCart> findAll() {
        return shoppingCartRepository.findAll();
    }

    public Optional<ShoppingCart> findById(String id) {
        return shoppingCartRepository.findById(id);
    }

//    public Optional<ShoppingCart> findByUserId(String userId) {
//        return shoppingCartRepository.findByUserId(userId);
//    }
    public Optional<ShoppingCart> findByUserId(String userId) {
        return shoppingCartRepository.findByUserId(userId);
    }


}
