package com.t2008m.orderdemo.entity.search.shopping;

public enum SearchCriteriaOperator {
    EQUALS, NOT_EQUALS,
    GREATER_THAN, GREATER_THAN_OR_EQUALS,
    LESS_THAN, LESS_THAN_OR_EQUALS,
    LIKE,
    IN,
    JOIN_SHOPPING, JOIN_USER;
}
