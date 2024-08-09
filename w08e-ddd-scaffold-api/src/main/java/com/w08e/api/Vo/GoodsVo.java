package com.w08e.api.Vo;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * @Author: 梦想成为超人的猪猪侠
 * @Date: 2024/8/7 14:38
 */

@Getter
public class GoodsVo{

    private Long id;

    private String name;

    private String image;

    private BigDecimal originalPrice;

    private BigDecimal discountPrice;

    private Integer stock;

    private String category;

    private String desc;

    private String addPerson;

}
