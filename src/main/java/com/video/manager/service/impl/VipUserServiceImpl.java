package com.video.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.video.manager.model.TUser;
import com.video.manager.model.mapper.ITUserMapper;
import com.video.manager.service.VipUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-17
 * @Description:
 */
@Service
public class VipUserServiceImpl implements VipUserService {
    @Autowired
    ITUserMapper userMapper;
    @Override
    public PageInfo<TUser> getVipUser(TUser user, int page, int pageSize) {
        PageInfo<TUser> orderPageInfo = PageHelper.startPage(page, pageSize).setOrderBy("user_id desc").doSelectPageInfo(() -> userMapper.select(user));
        return orderPageInfo;
    }
}