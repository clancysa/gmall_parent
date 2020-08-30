package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.product.service.ManageService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: y
 * @date: 2023/7/29 12:50
 * @description:
 */
@RestController
@RequestMapping("/admin/product")
public class SkuManageController {
    @Autowired
    ManageService manageService;

    /**
     * 获取spu销售属性和销售属性值集合
     * @param spuId
     * @return
     */
    @GetMapping("spuSaleAttrList/{spuId}")
    public Result getSpuSaleAttrList(@PathVariable Long spuId){
        List<SpuSaleAttr> list = manageService.getSpuSaleAttrList(spuId);
        return Result.ok(list);
    }

    /**
     * 获取商品图片列表
     * @param spuId
     * @return
     */
    @GetMapping("/spuImageList/{spuId}")
    public Result getImageList(@PathVariable Long spuId){
        List<SpuImage> list=manageService.getImageList(spuId);
        return Result.ok(list);
    }

    /**
     * 保存skuinfo
     * @param skuInfo
     * @return
     */
    @PostMapping("/saveSkuInfo")
    public Result saveSkuInfo(@RequestBody SkuInfo skuInfo){
        manageService.saveSkuInfo(skuInfo);
        return Result.ok();
    }

    /**
     * sku分页显示
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/list/{page}/{limit}")
    public Result getSkuListPage(
            @PathVariable Long page,
            @PathVariable Long limit
    ){
        IPage page1=manageService.getSkuListPage(page,limit);
        return Result.ok(page1);

    }

}
