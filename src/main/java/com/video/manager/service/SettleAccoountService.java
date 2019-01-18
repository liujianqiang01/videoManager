package com.video.manager.service;

import com.github.pagehelper.PageInfo;
import com.video.manager.model.TSettleAccount;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.Date;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-17
 * @Description:
 */
public interface SettleAccoountService {
    PageInfo<TSettleAccount> getSettleAccoount(TSettleAccount settleAccount, int page, int pageSize);
    void settleAccoountTask(Date date, String merchantId, BigDecimal rate);
    boolean grant(int id) throws Exception;
}
