package com.video.manager.service;

import com.github.pagehelper.PageInfo;
import com.video.manager.model.TOrder;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-16
 * @Description:
 */
public interface OrderService {
    public PageInfo<TOrder> getOrder(TOrder order,int page,int pageSize);
}