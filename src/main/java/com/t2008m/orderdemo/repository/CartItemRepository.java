package com.t2008m.orderdemo.repository;
import com.t2008m.orderdemo.entity.x.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,String> , JpaSpecificationExecutor<CartItem> {
    @Override
    Page<CartItem> findAll(Pageable pageable);
    List<CartItem> findAllById_ShoppingCartId(String shoppingCartId);
}
