package com.t2008m.orderdemo.entity.search.orders;

import com.t2008m.orderdemo.entity.Order;
import com.t2008m.orderdemo.entity.OrderDetail;
import com.t2008m.orderdemo.entity.Product;
import com.t2008m.orderdemo.entity.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;

public class OrderSpecification implements Specification<Order> {

    private SearchCriteria searchCriteria;

    public OrderSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    // criteriaBuilder giúp xử lý các toán tử.
    // root lấy ra trường và giá trị các trường.
    @Override
    public Predicate toPredicate(
            Root<Order> root,
            CriteriaQuery<?> query,
            CriteriaBuilder criteriaBuilder) {
        switch (searchCriteria.getOperator()) {
            case EQUALS:
                return criteriaBuilder.equal(
                        root.get(searchCriteria.getKey()),searchCriteria.getValue());
            case GREATER_THAN_OR_EQUALS:
                if (root.get(searchCriteria.getKey()).getJavaType() == LocalDateTime.class) {
                    return criteriaBuilder.greaterThanOrEqualTo(
                            root.get(searchCriteria.getKey()),
                            (LocalDateTime) searchCriteria.getValue());
                } else {
                    return criteriaBuilder.greaterThanOrEqualTo(
                            root.get(searchCriteria.getKey()),
                            String.valueOf(searchCriteria.getValue()));
                }
            case LESS_THAN_OR_EQUALS:
                if (root.get(searchCriteria.getKey()).getJavaType()==LocalDateTime.class){
                    return criteriaBuilder.lessThanOrEqualTo(
                            root.get(searchCriteria.getKey()),
                            (LocalDateTime)searchCriteria.getValue());
                }else {
                    return criteriaBuilder.lessThanOrEqualTo(
                            root.get(searchCriteria.getKey()),
                            String.valueOf(searchCriteria.getValue()));
                }
            case LESS_THAN:
                return criteriaBuilder.lessThan(
                        root.get(searchCriteria.getKey()),
                        String.valueOf(searchCriteria.getValue()));

            case JOIN_USER:
                From<Order,User> orderUserJoin = root.join("user");
                Predicate predicate =criteriaBuilder.like(
                        orderUserJoin.get(searchCriteria.getKey()),"%" + searchCriteria.getValue() + "%"
                );
                return predicate;
            case JOIN_PRODUCT:
                Join<OrderDetail, Product> orderDetailProductJoin = root.join("orderDetails").join("product");
                return criteriaBuilder.or(
                        criteriaBuilder.like(root.get("id"), "%" + searchCriteria.getValue() + "%"),
                        criteriaBuilder.like(orderDetailProductJoin.get("name"), "%" + searchCriteria.getValue() + "%"));


//                Predicate predicate2 = criteriaBuilder.or(
//                        // tìm trong order bản ghi có id giống giá trị truyền vào
//                        criteriaBuilder.like(root.get("id"), "%" + searchCriteria.getValue() + "%"),
//                        // hoặc tìm trong bảng product bản ghi có name giống với giá trị
//                        criteriaBuilder.like(orderDetailProductJoin.get("name"), "%" + searchCriteria.getValue() + "%"));
////
//                Predicate predicate1 = criteriaBuilder.like(
//                        orderDetailProductJoin.get(searchCriteria.getKey()), "%" + searchCriteria.getValue() + "%"
//                );

        }
        return null;
    }

    public static void main(String[] args) {
//        Specification<Order> specification = Specification.where(null);
//
//        SearchCriteria searchCriteria1 = new SearchCriteria(
//                "userId",
//                SearchCriteriaOperator.EQUALS,
//                "");
//        OrderSpecification spec1 = new OrderSpecification(searchCriteria1);
//        specification = specification.and(spec1);
//
//        SearchCriteria searchCriteria2 = new SearchCriteria(
//                "status",
//                SearchCriteriaOperator.EQUALS,
//                2);
//        OrderSpecification spec2 = new OrderSpecification(searchCriteria2);
//        specification = specification.and(spec2);


    }
}
