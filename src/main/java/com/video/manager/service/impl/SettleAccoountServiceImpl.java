package com.video.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.video.manager.model.TSettleAccount;
import com.video.manager.model.mapper.ITMerchantMapper;
import com.video.manager.model.mapper.ITOrderMapper;
import com.video.manager.model.mapper.ITSettleAccountMapper;
import com.video.manager.service.SettleAccoountService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-17
 * @Description:
 */
@Service
@Log4j
public class SettleAccoountServiceImpl implements SettleAccoountService {
    @Autowired
    ITSettleAccountMapper settleAccountMapper;
    @Autowired
    ITMerchantMapper merchantMapper;
    @Autowired
    ITOrderMapper orderMapper;
    @Override
    public PageInfo<TSettleAccount> getSettleAccoount(TSettleAccount settleAccount, int page, int pageSize) {
        PageInfo<TSettleAccount> orderPageInfo = PageHelper.startPage(page, pageSize).setOrderBy("settle_account_price desc").doSelectPageInfo(() -> settleAccountMapper.select(settleAccount));
        return orderPageInfo;
    }

    @Override
    @Transactional
    public void settleAccoountTask(Date date,String merchantId,BigDecimal rate) {
        //统计上月订单金额
        BigDecimal orderPrice = orderMapper.selectSumOrderPriceByName(date, merchantId);
        if(orderPrice == null){
            orderPrice = BigDecimal.ZERO;
        }
        //统计上个月提成金额
        BigDecimal ratePrice = orderPrice.multiply(rate).setScale(2,BigDecimal.ROUND_HALF_UP);

        TSettleAccount param = new TSettleAccount();
        param.setMerchantId(merchantId);
        param.setSettleAccountTime(date);
        param.setRemittanceState(0);
        TSettleAccount result = settleAccountMapper.selectOne(param);
        TSettleAccount tSettleAccount = new TSettleAccount();
        tSettleAccount.setSettleAccountPrice(orderPrice);
        tSettleAccount.setRatePrice(ratePrice);
        if(result == null) {
            tSettleAccount.setMerchantId(merchantId);
            tSettleAccount.setSettleAccountTime(date);
            tSettleAccount.setRemittanceState(0);
            if(orderPrice.compareTo(BigDecimal.ZERO) == 0){//如果没有收益，直接为放款状态
                tSettleAccount.setRemittanceState(2);
            }
            log.info("======>结算结果"+tSettleAccount.toString());
            settleAccountMapper.insert(tSettleAccount);
        }else {
            tSettleAccount.setId(result.getId());
            settleAccountMapper.updateByPrimaryKey(tSettleAccount);
        }
        orderMapper.updateSumOrderPriceByName(date, merchantId);
    }

    @Override
    public boolean grant(int id) {
        TSettleAccount param = new TSettleAccount();
        param.setId(id);
        param.setRemittanceState(0);
        TSettleAccount tSettleAccount = settleAccountMapper.selectOne(param);
        if(tSettleAccount != null) {
            tSettleAccount.setRemittanceState(1);
            settleAccountMapper.updateByPrimaryKey(tSettleAccount);
            //todo 微信
            return true;
        }
        return false;
    }
}