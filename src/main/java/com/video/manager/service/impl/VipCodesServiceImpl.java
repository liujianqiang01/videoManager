package com.video.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.video.manager.model.TVipCodes;
import com.video.manager.model.mapper.ITVipCodesMapper;
import com.video.manager.service.VipCodesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-17
 * @Description:
 */
@Service
public class VipCodesServiceImpl implements VipCodesService {

    @Autowired
    ITVipCodesMapper codesMapper;
    @Override
    public PageInfo<TVipCodes> getVipCodes(TVipCodes vipCodes, int page, int pageSize) {
        PageInfo<TVipCodes> orderPageInfo = PageHelper.startPage(page, pageSize).setOrderBy("id desc").doSelectPageInfo(() -> codesMapper.select(vipCodes));
        return orderPageInfo;
    }
}