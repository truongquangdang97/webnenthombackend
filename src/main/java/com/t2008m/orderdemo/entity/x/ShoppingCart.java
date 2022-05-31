package com.t2008m.orderdemo.entity.x;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "shopping_carts")
@Log4j2
public class ShoppingCart {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String userId; // ai tạo
    private BigDecimal totalPrice;
    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    private Set<CartItem> cartItems;

    public void addTotalPrice(CartItem cartItem) {
        if(this.totalPrice == null){
            this.totalPrice = new BigDecimal(0);
        }
        log.info("cartItem "+cartItem.getQuantity());
        BigDecimal quantityInBigDecimal = new BigDecimal(cartItem.getQuantity());
        log.info("quantityBigdeccimal "+quantityInBigDecimal );

        this.totalPrice = (BigDecimal)this.totalPrice.add(cartItem.getUnitPrice().multiply(quantityInBigDecimal));
//        this.totalPrice += (cartItem.getUnitPrice().multiply(cartItem.getQuantity()));
        log.info(this.totalPrice+"this totalprice ");
    }





}
