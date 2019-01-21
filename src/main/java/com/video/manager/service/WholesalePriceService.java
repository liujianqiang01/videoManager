package com.video.manager.service;

import com.github.pagehelper.PageInfo;
import com.video.manager.model.TWholesalePrice;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-21
 * @Description:
 */
public interface WholesalePriceService {
    PageInfo<TWholesalePrice> getWholesalePriceService(TWholesalePrice wholesalePrice, int page, int pageSize);
    void addWholesalePriceService(TWholesalePrice wholesalePrice);

}
