package com.video.manager.service;

import com.github.pagehelper.PageInfo;
import com.video.manager.model.TMerchant;
import com.video.manager.model.TMerchantPrice;

import java.util.List;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-17
 * @Description:
 */
public interface MerchantService {
    PageInfo<TMerchant> getMerchant(TMerchant order, int page, int pageSize);
    List<TMerchant> select(TMerchant merchant);
    void addMerchant(TMerchant merchant);
    PageInfo<TMerchantPrice> getMerchantPrice(TMerchantPrice merchantPrice, int page, int pageSize);
    void pass(Integer id);
    void update(TMerchant merchant);
}
