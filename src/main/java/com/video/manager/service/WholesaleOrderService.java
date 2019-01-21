package com.video.manager.service;

import com.github.pagehelper.PageInfo;
import com.video.manager.model.TWholesaleOrder;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-21
 * @Description:
 */
public interface WholesaleOrderService {
    PageInfo<TWholesaleOrder> getWholesaleOrderService(TWholesaleOrder wholesaleOrder, int page, int pageSize);
}
