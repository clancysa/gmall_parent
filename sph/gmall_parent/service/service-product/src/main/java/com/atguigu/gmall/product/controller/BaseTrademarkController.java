package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.product.service.BaseTrademarkService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.web.bind.annotation.*;

/**
 * @author: y
 * @date: 2023/7/27 14:58
 * @description:
 */
@RestController
@RequestMapping("/admin/product/baseTrademark")
public class BaseTrademarkController {
    @Autowired
    BaseTrademarkService baseTrademarkService;

    /**
     * 获取品牌分页列表
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("{page}/{limit}")
    public Result findTrademarkList(
            @PathVariable Long page,
            @PathVariable Long limit
    ){
        Page<BaseTrademark> pageParam = new Page<>(page,limit);
        IPage<BaseTrademark> iPage = baseTrademarkService.getPage(pageParam);
        return Result.ok(iPage);
    }
    @PostMapping("/save")
    public Result save(@RequestBody BaseTrademark baseTrademark){
        baseTrademarkService.save(baseTrademark);
        return Result.ok();
    }
    @PutMapping("/update")
    public Result update(@RequestBody BaseTrademark baseTrademark){
        baseTrademarkService.updateById(baseTrademark);
        return Result.ok();
    }
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable Long id){
        baseTrademarkService.removeById(id);
        return Result.ok();
    }
    @GetMapping("/get/{id}")
    public Result get(@PathVariable Long id){
        BaseTrademark byId = baseTrademarkService.getById(id);
        return Result.ok(byId);
    }
}
