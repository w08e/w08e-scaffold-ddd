package com.w08e.infrastructure.db.model;


import com.w08e.common.core.domain.BaseEntity;
import com.w08e.infrastructure.db.enums.OrderStatusEnums;
import lombok.Data;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * @author jinyueWang
 * @date 2023/8/1
 */
@Data
@Entity
@Table(name = "order")
public class OrderEntity extends BaseEntity<Long> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "goods_id")
    private Long goodsId;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatusEnums paid;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "create_time")
    private LocalDateTime createTime;

}