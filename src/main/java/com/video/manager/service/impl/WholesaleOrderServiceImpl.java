package com.video.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.video.manager.model.TWholesaleOrder;
import com.video.manager.model.mapper.ITWholesaleOrderMapper;
import com.video.manager.service.WholesaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-21
 * @Description:
 */
@Service
public class WholesaleOrderServiceImpl implements WholesaleOrderService {

    @Autowired
    ITWholesaleOrderMapper wholesaleOrderMapper;
    @Override
    public PageInfo<TWholesaleOrder> getWholesaleOrderService(TWholesaleOrder wholesaleOrder, int page, int pageSize) {
        PageInfo<TWholesaleOrder> orderPageInfo = PageHelper.startPage(page, pageSize).setOrderBy("id desc").doSelectPageInfo(() -> wholesaleOrderMapper.select(wholesaleOrder));
        return orderPageInfo;
    }
}