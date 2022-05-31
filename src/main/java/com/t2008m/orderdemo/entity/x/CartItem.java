package com.t2008m.orderdemo.entity.x;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.t2008m.orderdemo.entity.enums.CartItemStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Thông tin sản phẩm và số lượng sản phẩm,
 * kèm các thông tin giá tại thời điểm mua.
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cart_items")
public class CartItem {
    @EmbeddedId
    private CartItemId id;
    private String productName; // đỡ truy vấn ngược
    private String productThumbnail;
    private int quantity;
    private BigDecimal unitPrice;
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("shoppingCartId")
    @JoinColumn(name = "shopping_cart_id")
    @JsonBackReference
    private ShoppingCart shoppingCart;

    @Basic
    private int status;
    @Transient
    private CartItemStatus cartItemStatus;
    @PostLoad
    void fillTransient(){
        this.cartItemStatus = CartItemStatus.of(this.status);
    }


}
