package com.t2008m.orderdemo.service;

import com.t2008m.orderdemo.entity.Order;
import com.t2008m.orderdemo.entity.search.orders.FilterParameter;
import com.t2008m.orderdemo.entity.search.orders.OrderSpecification;
import com.t2008m.orderdemo.entity.search.orders.SearchCriteria;
import com.t2008m.orderdemo.entity.search.orders.SearchCriteriaOperator;
import com.t2008m.orderdemo.repository.OrderRepository;
import com.t2008m.orderdemo.util.DataDateTimeHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

//    public Page<Order> findAll(int page, int limit,Specification<Order> orderSpecification) {
//        return orderRepository.findAll(
//                orderSpecification, PageRequest.of(page - 1, limit));
//    }

    public Page<Order> findAll(FilterParameter param) {
        Specification<Order> specification = Specification.where(null);
        // lớn hơn hoặc tương đương
//        log.info("check start: " + start);
        if (param.getStart()!=null && !param.getStart().equals("")){
            SearchCriteria searchCriteria
                    = new SearchCriteria("createdAt", SearchCriteriaOperator.GREATER_THAN_OR_EQUALS, DataDateTimeHelper.convertStringLocalDateTime(param.getStart()));
            OrderSpecification filter = new OrderSpecification(searchCriteria);
            specification = specification.and(filter);
        }
        //nhỏ hơn hoặc bằng
        if (param.getEnd()!=null && !param.getEnd().equals("")){
            SearchCriteria searchCriteria
                    = new SearchCriteria("createdAt", SearchCriteriaOperator.LESS_THAN_OR_EQUALS,DataDateTimeHelper.convertStringLocalDateTime(param.getEnd()));
            OrderSpecification filter = new OrderSpecification(searchCriteria);
            specification = specification.and(filter);
        }
        ////test
        /// tìm theo t
        if (param.getPhone() != null && param.getPhone().length() > 0) {
            SearchCriteria searchCriteria = new SearchCriteria("phone", SearchCriteriaOperator.JOIN_USER, param.getPhone());
            OrderSpecification filter = new OrderSpecification(searchCriteria);
            specification = specification.and(filter);
        }
        // theo số điên thoại
//        if (param.getKeyword() != null && param.getKeyword().length() > 0) {
//            SearchCriteria searchCriteria = new SearchCriteria("phone", SearchCriteriaOperator.JOIN_USER, param.getKeyword());
//            OrderSpecification filter = new OrderSpecification(searchCriteria);
//            specification = specification.and(filter);
//        }

        // cái này search theo tên user
        if (param.getNameUser() != null && param.getNameUser().length() > 0) {
            SearchCriteria searchCriteria = new SearchCriteria("nameUser", SearchCriteriaOperator.JOIN_USER, param.getNameUser());
            OrderSpecification filter = new OrderSpecification(searchCriteria);
            specification = specification.and(filter);
        }
//         cái này search theo tên product
        if (param.getNameProduct() != null && param.getNameProduct().length() > 0) {
            SearchCriteria searchCriteria = new SearchCriteria("name", SearchCriteriaOperator.JOIN_PRODUCT, param.getNameProduct());
            OrderSpecification filter = new OrderSpecification(searchCriteria);
            specification = specification.and(filter);
        }

        if (param.getStatus() >0) {
            SearchCriteria searchCriteria
                    = new SearchCriteria("status", SearchCriteriaOperator.EQUALS, param.getStatus());
            OrderSpecification filter = new OrderSpecification(searchCriteria);
            specification = specification.and(filter);
        }
        if (param.getUserId() != null && param.getUserId().length() > 0 ) {
            SearchCriteria searchCriteria
                    = new SearchCriteria("user_id", SearchCriteriaOperator.EQUALS, param.getUserId());
            OrderSpecification filter = new OrderSpecification(searchCriteria);
            specification = specification.and(filter);
        }


        return orderRepository.findAll(
                specification, PageRequest.of(param.getPage() - 1, param.getLimit()));
    }
}
