package com.w08e.infrastructure.db.model;


import com.w08e.common.core.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;


/**
 * @author jinyueWang
 * @date 2023/8/1
 */
@Data
@Entity
@Table(name = "ddd_order")
@EqualsAndHashCode(callSuper = true)
public class OrderEntity extends BaseEntity<Long> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "goods_id")
    private Long goodsId;

    @Column(name = "real_price")
    private BigDecimal realPrice;

    @Column(name = "pay_status")
    private String payStatus;

    @Column(name = "quantity")
    private Integer quantity;


}