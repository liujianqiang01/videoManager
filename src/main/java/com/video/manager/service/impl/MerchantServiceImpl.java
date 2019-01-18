package com.video.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.video.manager.model.TMerchant;
import com.video.manager.model.mapper.ITMerchantMapper;
import com.video.manager.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-17
 * @Description:
 */
@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    ITMerchantMapper merchantMapper;
    @Override
    public PageInfo<TMerchant> getMerchant(TMerchant merchant, int page, int pageSize) {
        PageInfo<TMerchant> orderPageInfo = PageHelper.startPage(page, pageSize).setOrderBy("id desc").doSelectPageInfo(() -> merchantMapper.select(merchant));
        return orderPageInfo;
    }

    @Override
    public List<TMerchant> select(TMerchant merchant) {
        return merchantMapper.select(merchant);
    }

    @Override
    public void addMerchant(TMerchant merchant) {
        merchantMapper.insert(merchant);
    }
}