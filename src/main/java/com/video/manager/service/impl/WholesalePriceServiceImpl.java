package com.video.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.video.manager.model.TWholesalePrice;
import com.video.manager.model.WebResult;
import com.video.manager.model.mapper.ITWholesalePriceMapper;
import com.video.manager.service.WholesalePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-21
 * @Description:
 */
@Service
public class WholesalePriceServiceImpl implements WholesalePriceService {
    @Autowired
    ITWholesalePriceMapper wholesalePriceMapper;
    @Override
    public PageInfo<TWholesalePrice> getWholesalePriceService(TWholesalePrice wholesalePrice, int page, int pageSize) {
        PageInfo<TWholesalePrice> orderPageInfo = PageHelper.startPage(page, pageSize).setOrderBy("vip_type asc").doSelectPageInfo(() -> wholesalePriceMapper.select(wholesalePrice));
        return orderPageInfo;
    }

    @Override
    public WebResult addWholesalePriceService(TWholesalePrice wholesalePrice) {
        List<TWholesalePrice> tWholesalePrice = wholesalePriceMapper.chenkExist(wholesalePrice);
        if(tWholesalePrice != null &&tWholesalePrice.size()>0 ){
            return WebResult.needParams("区间价格已经存在");
        }
        wholesalePrice.setState(1);
        wholesalePrice.setCreateTime(new Date());
        wholesalePriceMapper.insert(wholesalePrice);
        return WebResult.success();
    }

    @Override
    public void delete(Integer id) {
        TWholesalePrice tWholesalePrice = wholesalePriceMapper.selectByPrimaryKey(id);
        tWholesalePrice.setState(0);
        tWholesalePrice.setUpdateTime(new Date());
        wholesalePriceMapper.updateByPrimaryKey(tWholesalePrice);
    }


}