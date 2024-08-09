package com.w08e.api.command;

import com.w08e.common.core.domain.command.Command;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * @Author: 梦想成为超人的猪猪侠
 * @Date: 2024/8/7 14:38
 */

@Getter
public class GoodsCommand implements Command {

    @NotBlank(message = "{goods.name.is.blank}")
    private String name;

    private String image;

    @NotBlank(message = "{goods.price.is.blank}")
    private BigDecimal originalPrice;

    private BigDecimal discountPrice;

    @NotBlank(message = "{goods.stock.is.blank}")
    private Integer stock;

    @NotBlank(message = "{goods.category.is.blank}")
    private String category;

    private String desc;

    private String addPerson;

}
