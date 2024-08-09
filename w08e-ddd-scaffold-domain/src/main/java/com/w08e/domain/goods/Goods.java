package com.w08e.domain.goods;

import com.w08e.common.core.domain.AggregateRoot;
import com.w08e.common.core.exception.ServiceException;
import com.w08e.common.core.utils.ValidationUtil;
import com.w08e.domain.user.User;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @Author: 梦想成为超人的猪猪侠
 * @Date: 2024/8/7 14:38
 */

@Getter
public class Goods extends AggregateRoot {

    private String name;

    private String image;

    private BigDecimal originalPrice;

    private BigDecimal discountPrice;

    private Integer stock;

    private String category;

    private String desc;

    private String addPerson;


    public Goods(String name, BigDecimal originalPrice, Integer stock, String category, User user) {
        this.originalPrice = originalPrice;
        this.stock = stock;
        this.category = category;
        this.addPerson = user.getName();
        this.name = name;
    }

    public Goods(String name, BigDecimal originalPrice, BigDecimal discountPrice, Integer stock, String category, String desc, User user) {
        this.name = name;
        this.originalPrice = originalPrice;
        this.discountPrice = discountPrice;
        this.stock = stock;
        this.category = category;
        this.desc = desc;
        this.addPerson = user.getName();

    }

    public void update(String name, String category, String desc) {
        if(!Objects.equals(name, this.name)) {
            this.name = name;
        }
        this.category = category;
        this.desc = desc;
    }

    public void updateOriginalPrice(BigDecimal originalPrice) {
        // 参数非空与否是在应用层控制 这里不做非空判断 只处理业务逻辑
        ValidationUtil.isTrue(originalPrice.compareTo(BigDecimal.ZERO) > 0, "商品原价必须大于0");

        if(!Objects.equals(originalPrice, this.originalPrice)) {
            this.originalPrice = originalPrice;
        }
    }

    public void reduceInventory(int quantity) {

        if(quantity > this.stock) {
            throw new ServiceException("库存不足。。。");
        }

        this.stock -= quantity;
    }
}
