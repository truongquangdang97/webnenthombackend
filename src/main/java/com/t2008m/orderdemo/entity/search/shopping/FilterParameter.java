package com.t2008m.orderdemo.entity.search;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FilterParameter {
    private int page;
    private int limit;

    private String nameUser;

}
