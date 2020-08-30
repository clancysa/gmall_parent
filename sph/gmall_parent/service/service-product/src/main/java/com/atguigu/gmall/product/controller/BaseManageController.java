package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.product.service.ManageService;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: y
 * @date: 2023/7/25 16:30
 * @description:
 */
@RestController
@RequestMapping("/admin/product/")
public class BaseManageController {
    @Autowired
    ManageService manageService;

    /**
     * 获取一级分类数据
     * @return
     */
    @GetMapping("getCategory1")
    public Result getCategory1(){
        List<BaseCategory1> list=manageService.getCategory1();
        return Result.ok(list);
    }

    /**
     * 获取二级分类数据
     * @param category1Id
     * @return
     */
    @GetMapping("getCategory2/{category1Id}")
    public Result getCategory2(@PathVariable Long category1Id){
        List<BaseCategory2> list=manageService.getCategory2(category1Id);
        return Result.ok(list);
    }
    /**
     * 获取三级分类数据
     * @param category2Id
     * @return
     */
    @GetMapping("getCategory3/{category2Id}")
    public Result getCategory3(@PathVariable Long category2Id){
        List<BaseCategory3> list=manageService.getCategory3(category2Id);
        return Result.ok(list);
    }

    /**
     * 根据分类Id获取平台属性集合
     * @param category1Id
     * @param category2Id
     * @param category3Id
     * @return
     */
    @GetMapping("attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result getAttrInfoList(
            @PathVariable Long category1Id,
            @PathVariable Long category2Id,
            @PathVariable Long category3Id
    ){
        List<BaseAttrInfo> list=manageService.getAttrInfoList(category1Id,category2Id,category3Id);
        return Result.ok(list);
    }

    /**
     * 根据平台属性Id获取平台属性集合
     * @param attrId
     * @return
     */
    @GetMapping("getAttrValueList/{attrId}")
    public Result getAttrValueList(@PathVariable Long attrId){
        List<BaseAttrValue> list=null;
        BaseAttrInfo baseAttrInfo=manageService.getAttrInfo(attrId);
        if (baseAttrInfo!=null){
            list=baseAttrInfo.getAttrValueList();
        }
        return Result.ok(list);
    }

    /**
     * 保存-修改平台属性
     * @param baseAttrInfo
     * @return
     */

    @PostMapping("/saveAttrInfo")
    public Result saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo){
        manageService.saveAttrInfo(baseAttrInfo);
        return Result.ok();
    }
}
