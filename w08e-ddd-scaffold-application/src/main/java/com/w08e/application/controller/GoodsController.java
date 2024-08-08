package com.w08e.application.controller;

import cn.hutool.core.lang.Pair;
import com.w08e.common.core.domain.result.Result;
import com.w08e.api.command.GoodsCommand;
import com.w08e.api.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 梦想成为超人的猪猪侠
 * @Date: 2024/8/7 16:09
 */

@RestController
@RequestMapping("/goods")
@RequiredArgsConstructor
public class GoodsController {

    private GoodsService goodsService;

    @PostMapping("/goods")
    public Result<Pair<Long,String>> create(GoodsCommand goodsCommand){
        return Result.success(goodsService.create(goodsCommand));
    }
}
