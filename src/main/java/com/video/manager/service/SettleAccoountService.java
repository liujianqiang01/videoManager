package com.video.manager.service;

import com.github.pagehelper.PageInfo;
import com.video.manager.model.TSettleAccount;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-17
 * @Description:
 */
public interface SettleAccoountService {
    PageInfo<TSettleAccount> getSettleAccoount(TSettleAccount settleAccount, int page, int pageSize);
    void settleAccoountTask(Date date, String merchantId, BigDecimal rate);
}
