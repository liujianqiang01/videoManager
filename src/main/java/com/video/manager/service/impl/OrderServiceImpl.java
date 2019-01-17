package com.video.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.video.manager.model.TOrder;
import com.video.manager.model.mapper.ITOrderMapper;
import com.video.manager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-16
 * @Description:
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    ITOrderMapper orderMapper;
    @Override
    public PageInfo<TOrder> getOrder(TOrder order,int page,int pageSize) {
        PageInfo<TOrder> orderPageInfo = PageHelper.startPage(page, pageSize).setOrderBy("id desc").doSelectPageInfo(() -> orderMapper.select(order));
        return orderPageInfo;
    }
}