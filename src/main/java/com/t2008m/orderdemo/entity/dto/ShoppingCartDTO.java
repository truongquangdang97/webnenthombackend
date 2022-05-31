package com.t2008m.orderdemo.entity.dto;

import com.t2008m.orderdemo.entity.x.CartItem;
import com.t2008m.orderdemo.entity.x.ShoppingCart;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class ShoppingCartDTO {
    private String id;
    private String userId;
    private BigDecimal totalPrice;
    private Set<CartItemDTO> cartItemDTOSet;

    public ShoppingCart generateCart(){
        return ShoppingCart.builder()
                .userId(this.userId)
                .build();
    }
//    public void addTotalPrice(CartItem cartItem) {
//        if(this.totalPrice == null){
//            this.totalPrice = new BigDecimal(0);
//        }
//        BigDecimal quantityInBigDecimal = new BigDecimal(cartItem.getQuantity());
//        this.totalPrice = this.totalPrice.add(cartItem.getUnitPrice().multiply(quantityInBigDecimal));
//    }
}
