package com.w08e.application.controller;

import cn.hutool.core.lang.Pair;
import com.w08e.api.command.GoodsCommand;
import com.w08e.api.service.GoodsService;
import com.w08e.common.core.result.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    private final GoodsService goodsService;

    @PostMapping("/goods")
    public R<Pair<Long,String>> create(@RequestBody GoodsCommand goodsCommand){
        return R.ok(goodsService.create(goodsCommand));
    }
}
