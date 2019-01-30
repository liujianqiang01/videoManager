package com.video.manager.service;

import com.github.pagehelper.PageInfo;
import com.video.manager.model.TUser;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-17
 * @Description:
 */
public interface VipUserService {
    PageInfo<TUser> getVipUser(TUser user, int page, int pageSize);

    void pass(Integer id,Integer applyState);
}
