package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.model.product.CategoryTrademarkVo;
import com.atguigu.gmall.product.service.BaseCategoryTradeMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: y
 * @date: 2023/7/28 11:15
 * @description:
 */
@RestController
@RequestMapping("/admin/product/baseCategoryTrademark")
public class BaseCategoryTradeMarkController {
    @Autowired
    BaseCategoryTradeMarkService baseCategoryTradeMarkService;

    /**
     * 根据3id查询品牌集合
     * @param category3Id
     * @return
     */
    @GetMapping("/findTrademarkList/{category3Id}")
    public Result findTrademarkList(@PathVariable Long category3Id){
        List<BaseTrademark> list = baseCategoryTradeMarkService.getListByCategory3Id(category3Id);
        return Result.ok(list);
    }

    /**
     * 根据category3Id获取可选品牌列表
     * @param category3Id
     * @return
     */
    @GetMapping("/findCurrentTrademarkList/{category3Id}")
    public Result findCurrentTrademarkList(@PathVariable Long category3Id){
        List<BaseTrademark> baseTrademarkList=baseCategoryTradeMarkService.getCurrentTrademarkListByCategory3Id(category3Id);
        return Result.ok(baseTrademarkList);
    }

    /**
     * 保存
     * @param categoryTrademarkVo
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody CategoryTrademarkVo categoryTrademarkVo){
        baseCategoryTradeMarkService.save(categoryTrademarkVo);
        return Result.ok();
    }

    /**
     * 删除
     * @param category3Id
     * @param trademarkId
     * @return
     */
    @DeleteMapping("/remove/{category3Id}/{trademarkId}")
    public Result remove(@PathVariable Long category3Id,@PathVariable Long trademarkId){
        baseCategoryTradeMarkService.remove(category3Id,trademarkId);
        return Result.ok();
    }
}
