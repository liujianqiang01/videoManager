package com.video.manager.service;

import com.github.pagehelper.PageInfo;
import com.video.manager.model.TMerchant;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-17
 * @Description:
 */
public interface MerchantService {
    PageInfo<TMerchant> getMerchant(TMerchant order, int page, int pageSize);

}
