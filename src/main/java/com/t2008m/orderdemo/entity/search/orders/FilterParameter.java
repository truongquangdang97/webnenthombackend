package com.t2008m.orderdemo.entity.search.orders;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FilterParameter {
    private String nameProduct;
    private String nameUser;
    private String phone;
    private int page;
    private int limit;
    private String userId;
    private int status;
    private String start;
    private String end;
}
