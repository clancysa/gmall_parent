package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.BaseCategoryTrademark;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.model.product.CategoryTrademarkVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author: y
 * @date: 2023/7/28 12:14
 * @description:
 */
public interface BaseCategoryTradeMarkService extends IService<BaseCategoryTrademark> {
    /**
     * 根据3id查询品牌集合
     * @return
     */
    List<BaseTrademark> getListByCategory3Id(Long category3Id);

    /**
     * 根据category3Id获取可选品牌列表
     * @param category3Id
     * @return
     */
    List<BaseTrademark> getCurrentTrademarkListByCategory3Id(Long category3Id);

    /**
     * 保存
     * @param categoryTrademarkVo
     */
    void save(CategoryTrademarkVo categoryTrademarkVo);

    /**
     * 删除
     * @param category3Id
     * @param trademarkId
     */
    void remove(Long category3Id, Long trademarkId);
}
