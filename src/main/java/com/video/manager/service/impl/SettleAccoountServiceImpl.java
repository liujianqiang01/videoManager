package com.video.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.video.manager.model.TSettleAccount;
import com.video.manager.model.mapper.ITSettleAccountMapper;
import com.video.manager.service.SettleAccoountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-17
 * @Description:
 */
@Service
public class SettleAccoountServiceImpl implements SettleAccoountService {
    @Autowired
    ITSettleAccountMapper settleAccountMapper;
    @Override
    public PageInfo<TSettleAccount> getSettleAccoount(TSettleAccount settleAccount, int page, int pageSize) {
        PageInfo<TSettleAccount> orderPageInfo = PageHelper.startPage(page, pageSize).setOrderBy("id desc").doSelectPageInfo(() -> settleAccountMapper.select(settleAccount));
        return orderPageInfo;
    }
}