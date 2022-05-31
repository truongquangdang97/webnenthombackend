package com.t2008m.orderdemo.entity.search.shopping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {
    private String key; // trường nào
    private SearchCriteriaOperator operator; // toán tử
    private Object value; // giá trị là gì
}
