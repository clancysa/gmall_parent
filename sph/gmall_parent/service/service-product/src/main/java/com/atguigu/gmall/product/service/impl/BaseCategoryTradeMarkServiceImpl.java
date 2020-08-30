package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseCategoryTrademark;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.model.product.CategoryTrademarkVo;
import com.atguigu.gmall.product.mapper.BaseCategoryTradeMarkMapper;
import com.atguigu.gmall.product.mapper.BaseTrademarkMapper;
import com.atguigu.gmall.product.service.BaseCategoryTradeMarkService;
import com.atguigu.gmall.product.service.BaseTrademarkService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jodd.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Target;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: y
 * @date: 2023/7/28 12:14
 * @description:
 */
@Service
public class BaseCategoryTradeMarkServiceImpl extends ServiceImpl<BaseCategoryTradeMarkMapper, BaseCategoryTrademark> implements BaseCategoryTradeMarkService {
    @Autowired
    BaseCategoryTradeMarkMapper baseCategoryTradeMarkMapper;
    @Autowired
    BaseTrademarkMapper baseTrademarkMapper;
    @Override
    public List<BaseTrademark> getListByCategory3Id(Long category3Id) {
        QueryWrapper<BaseCategoryTrademark> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category3_id",category3Id);
        List<BaseCategoryTrademark> trademarkList = baseCategoryTradeMarkMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(trademarkList)){
            List<Long> list = trademarkList.stream().map(baseCategoryTrademark -> {
                return baseCategoryTrademark.getTrademarkId();
            }).collect(Collectors.toList());
            //返回id封装的集合
            return baseTrademarkMapper.selectBatchIds(list);
        }
        //没有中间表集合返回空
        return null;
    }

    /**
     * 根据category3Id获取可选品牌列表
     * @param category3Id
     * @return
     */
    @Override
    public List<BaseTrademark> getCurrentTrademarkListByCategory3Id(Long category3Id) {
        QueryWrapper<BaseCategoryTrademark> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category3_id",category3Id);
        //根据3id查找中间表的集合
        List<BaseCategoryTrademark> trademarkList = baseCategoryTradeMarkMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(trademarkList)) {
            //将中间表的所有id封装集合
            List<Long> list = trademarkList.stream().map(baseCategoryTrademark -> {
                return baseCategoryTrademark.getTrademarkId();
            }).collect(Collectors.toList());
            //过滤已关联的id封装集合
            List<BaseTrademark> trademarks = baseTrademarkMapper.selectList(null).stream().filter(baseTrademark -> {
                return !list.contains(baseTrademark.getId());
            }).collect(Collectors.toList());
            //返回集合
            return trademarks;
        }
        //如果没有任何品牌则获取所有品牌数据
        return baseTrademarkMapper.selectList(null);
    }

    /**
     * 保存
     * @param categoryTrademarkVo
     */
    @Override
    public void save(CategoryTrademarkVo categoryTrademarkVo) {
        //获取tradeId列表
        List<Long> trademarkIdList = categoryTrademarkVo.getTrademarkIdList();
        if (!CollectionUtils.isEmpty(trademarkIdList)) {
            //映射
            List<BaseCategoryTrademark> trademarks = trademarkIdList.stream().map(trademarkId -> {
                //创建一个分类id与品牌相关联的对象
                BaseCategoryTrademark baseCategoryTrademark = new BaseCategoryTrademark();
                baseCategoryTrademark.setCategory3Id(categoryTrademarkVo.getCategory3Id());
                baseCategoryTrademark.setTrademarkId(trademarkId);
                return baseCategoryTrademark;
            }).collect(Collectors.toList());
         //集中保存到数据库
         this.saveBatch(trademarks);
        }
    }

    /**
     * 删除
     * @param category3Id
     * @param trademarkId
     */
    @Override
    public void remove(Long category3Id, Long trademarkId) {
        QueryWrapper<BaseCategoryTrademark> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category3_id",category3Id);
        queryWrapper.eq("trademark_id",trademarkId);
        baseCategoryTradeMarkMapper.delete(queryWrapper);
    }
}
