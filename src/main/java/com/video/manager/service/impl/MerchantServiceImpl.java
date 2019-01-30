package com.video.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.video.manager.model.TMerchant;
import com.video.manager.model.TMerchantPrice;
import com.video.manager.model.mapper.ITMerchantMapper;
import com.video.manager.model.mapper.ITMerchantPriceMapper;
import com.video.manager.service.MerchantService;
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
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    ITMerchantMapper merchantMapper;
    @Autowired
    ITMerchantPriceMapper merchantPriceMapper;
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

    @Transactional
    @Override
    public void pass(Integer id,Integer state) {
        TMerchantPrice tMerchantPrice = merchantPriceMapper.selectByPrimaryKey(id);
        if(tMerchantPrice != null ){
            if(state ==1 ) {//通过
                //将之前的失效
                TMerchantPrice param = new TMerchantPrice();
                param.setMerchanId(tMerchantPrice.getMerchanId());
                param.setState(2);
                merchantPriceMapper.updateState(param);
                tMerchantPrice.setState(1);
                merchantPriceMapper.updateByPrimaryKey(tMerchantPrice);
            }else if(state ==2){//拒绝
                tMerchantPrice.setState(2);
                merchantPriceMapper.updateByPrimaryKey(tMerchantPrice);
            }
        }
    }

    @Override
    public void update(TMerchant merchant) {
        TMerchant param = new TMerchant();
        param.setId(merchant.getId());
        TMerchant result = merchantMapper.selectOne(param);
        if(result != null) {
            result.setRate(merchant.getRate());
            merchantMapper.updateByPrimaryKey(result);
        }
    }

    @Override
    public PageInfo<TMerchantPrice> getMerchantPrice(TMerchantPrice merchantPrice, int page, int pageSize) {
        PageInfo<TMerchantPrice> orderPageInfo = PageHelper.startPage(page, pageSize).setOrderBy("id desc").doSelectPageInfo(() -> merchantPriceMapper.select(merchantPrice));
        return orderPageInfo;
    }


}