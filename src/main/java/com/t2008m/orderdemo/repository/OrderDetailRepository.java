package com.t2008m.orderdemo.repository;

import com.t2008m.orderdemo.entity.OrderDetail;
import com.t2008m.orderdemo.entity.dto.ItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
    @Override
    Page<OrderDetail> findAll(Pageable pageable);

//    ItemDTO addToCart(ItemDTO itemDTO);
}
