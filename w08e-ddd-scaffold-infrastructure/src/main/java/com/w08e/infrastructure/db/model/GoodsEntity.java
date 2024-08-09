package com.w08e.infrastructure.db.model;


import com.w08e.common.core.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author jinyueWang
 * @date 2023/8/1
 */
@Data
@Entity
@Table(name = "ddd_goods")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GoodsEntity extends BaseEntity<Long> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "original_price")
    private BigDecimal originalPrice;

    @Column(name = "discount_price")
    private BigDecimal discountPrice;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "category")
    private String category;

    @Column(name = "descp")
    private String descp;


}