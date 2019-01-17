package com.video.manager.service;

import com.github.pagehelper.PageInfo;
import com.video.manager.model.TVipCodes;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-17
 * @Description:
 */
public interface VipCodesService {
    PageInfo<TVipCodes> getVipCodes(TVipCodes vipCodes, int page, int pageSize);

}