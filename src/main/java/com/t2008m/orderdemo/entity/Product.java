package com.t2008m.orderdemo.entity;

import com.t2008m.orderdemo.entity.base.BaseEntity;
import com.t2008m.orderdemo.entity.enums.ProductSimpleStatus;
import com.t2008m.orderdemo.entity.enums.ProductStatus;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private String slug;
    private String description;
    private String detail; // text
    private String thumbnails; // nhiều ảnh cách nhau bởi dấu ,
    private BigDecimal price;
    @Enumerated(EnumType.ORDINAL)
    private ProductSimpleStatus status;

    public Product(){
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
    }
//    @Basic
//    private int status;
//    @Transient
//    private ProductStatus productStatus;
//
//    @PostLoad // khi load
//    void fillTransient() {
//        this.productStatus = ProductStatus.of(status); // chuyển dữ liệu dạng số ở trong database về kiểu enum
//    }
//
//    @PrePersist // trước khi lưu
//    void fillPersistent() {
//        // đưa giá trị của product status vào trường status.
//        this.status = this.productStatus.getValue();
//    }
}
