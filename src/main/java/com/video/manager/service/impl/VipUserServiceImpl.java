package com.video.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.video.manager.model.TMerchant;
import com.video.manager.model.TUser;
import com.video.manager.model.mapper.ITMerchantMapper;
import com.video.manager.model.mapper.ITUserMapper;
import com.video.manager.service.VipUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-17
 * @Description:
 */
@Service
public class VipUserServiceImpl implements VipUserService {
    @Autowired
    ITUserMapper userMapper;
    @Autowired
    ITMerchantMapper merchantMapper;
    @Override
    public PageInfo<TUser> getVipUser(TUser user, int page, int pageSize) {
        PageInfo<TUser> orderPageInfo = PageHelper.startPage(page, pageSize).setOrderBy("user_id desc").doSelectPageInfo(() -> userMapper.select(user));
        return orderPageInfo;
    }

    @Override
    @Transactional
    public void pass(Integer id,Integer applyState) {
        TUser tUser = userMapper.selectByPrimaryKey(id);
        if(tUser != null){
            TUser param = new TUser();
            param.setOpenId(tUser.getOpenId());
            List<TUser> select = userMapper.select(param);
            if(select != null && select.size()<2){
                //新建
                TUser user = select.get(0);
                TUser newUser = new TUser();
                newUser.setOpenId(user.getOpenId());
                newUser.setUserType(1);

                TMerchant merchant = merchantMapper.selectRandomOne();
                if(merchant != null) {
                    newUser.setMenchantId(merchant.getMenchantId());
                    userMapper.updateApplyState(user.getUserId());
                    userMapper.insert(newUser);
                    merchant.setState(1);
                    merchantMapper.updateByPrimaryKeySelective(merchant);
                }
            }
        }
    }
}