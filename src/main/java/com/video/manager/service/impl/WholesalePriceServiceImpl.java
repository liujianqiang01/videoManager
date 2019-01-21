package com.video.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.video.manager.model.TWholesalePrice;
import com.video.manager.model.mapper.ITWholesalePriceMapper;
import com.video.manager.service.WholesalePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        PageInfo<TWholesalePrice> orderPageInfo = PageHelper.startPage(page, pageSize).setOrderBy("id desc").doSelectPageInfo(() -> wholesalePriceMapper.select(wholesalePrice));
        return orderPageInfo;
    }

    @Override
    public void addWholesalePriceService(TWholesalePrice vipCodes) {
        wholesalePriceMapper.insert(vipCodes);
    }
}