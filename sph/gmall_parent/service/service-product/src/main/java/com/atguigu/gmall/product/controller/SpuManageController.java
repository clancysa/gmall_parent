package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseSaleAttr;
import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.product.service.ManageService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: y
 * @date: 2023/7/26 21:04
 * @description:
 */
@RestController // @ResponseBody + @Controller
@RequestMapping("/admin/product")
public class SpuManageController {
    @Autowired
    ManageService manageService;
    @GetMapping("{page}/{size}")
    public Result getSpuInfoPage(
            @PathVariable Long page,
            @PathVariable Long size,
            SpuInfo spuInfo
    ){
        Page<SpuInfo> infoPage = new Page<>();
        IPage<SpuInfo> spuInfoPage = manageService.getSpuInfoPage(infoPage, spuInfo);
        return Result.ok(spuInfoPage);
    }

    /**
     * 品牌列表
     * @return
     */
    @GetMapping("/baseSaleAttrList")
    public Result baseSaleAttrList(){
        List<BaseSaleAttr> baseSaleAttrList=manageService.getSaleAttrList();
        return Result.ok(baseSaleAttrList);
    }

    /**
     * 保存spu
     * @param spuInfo
     * @return
     */
    @PostMapping("/saveSpuInfo")
    public Result saveSpuInfo(@RequestBody SpuInfo spuInfo){
        manageService.saveSpuInfo(spuInfo);
        return Result.ok();
    }

    /**
     * 商品上架
     * @param skuId
     * @return
     */
    @GetMapping("/onSale/{skuId}")
    public Result onSale(@PathVariable Long skuId){
        manageService.onSale(skuId);
        return Result.ok();
    }

    /**
     * 商品下架
     * @param skuId
     * @return
     */
    @GetMapping("/cancelSale/{skuId}")
    public Result cancelSale(@PathVariable Long skuId){
        manageService.cancelSale(skuId);
        return Result.ok();
    }
}
