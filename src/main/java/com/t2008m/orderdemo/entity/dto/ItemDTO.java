package com.t2008m.orderdemo.entity.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDTO {
    private String productId;
    private int quantity;
}
