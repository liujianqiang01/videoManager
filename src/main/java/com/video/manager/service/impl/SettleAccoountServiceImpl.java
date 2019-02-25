package com.video.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.thoughtworks.xstream.XStream;
import com.video.manager.common.Configure;
import com.video.manager.common.HttpRequest;
import com.video.manager.common.RandomStringGenerator;
import com.video.manager.common.Signature;
import com.video.manager.model.TSettleAccount;
import com.video.manager.model.WeiXinTransfers;
import com.video.manager.model.WeiXinTransfersReturn;
import com.video.manager.model.mapper.ITMerchantMapper;
import com.video.manager.model.mapper.ITOrderMapper;
import com.video.manager.model.mapper.ITSettleAccountMapper;
import com.video.manager.service.SettleAccoountService;
import com.video.manager.utils.StringUtils;
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
    public void settleAccoountTask(Date date, String merchantId, BigDecimal rate) {
        //统计上月订单金额
        BigDecimal orderPrice = orderMapper.selectSumOrderPriceByName(date, merchantId);
        if (orderPrice == null) {
            orderPrice = BigDecimal.ZERO;
        }
        //统计上个月提成金额
        BigDecimal ratePrice = orderPrice.multiply(rate).setScale(2, BigDecimal.ROUND_HALF_UP);

        TSettleAccount param = new TSettleAccount();
        param.setMerchantId(merchantId);
        param.setSettleAccountTime(date);
        param.setRemittanceState(0);
        TSettleAccount result = settleAccountMapper.selectOne(param);
        TSettleAccount tSettleAccount = new TSettleAccount();
        tSettleAccount.setSettleAccountPrice(orderPrice);
        tSettleAccount.setRatePrice(ratePrice);
        if (result == null) {
            tSettleAccount.setMerchantId(merchantId);
            tSettleAccount.setSettleAccountTime(date);
            tSettleAccount.setRemittanceState(0);
            log.info("======>结算结果" + tSettleAccount.toString());
            settleAccountMapper.insert(tSettleAccount);
        } else {
            result.setSettleAccountPrice(orderPrice.add(result.getSettleAccountPrice()));
            result.setRatePrice(ratePrice.add(result.getRatePrice()));
            settleAccountMapper.updateByPrimaryKey(result);
        }
        orderMapper.updateSumOrderPriceByName(date, merchantId);
    }

    @Override
    @Transactional
    public boolean grant(int id) throws Exception {
        TSettleAccount param = new TSettleAccount();
        param.setId(id);
        param.setRemittanceState(0);
        TSettleAccount tSettleAccount = settleAccountMapper.selectOne(param);
        if (tSettleAccount != null) {
            tSettleAccount.setRemittanceState(1);
            settleAccountMapper.updateByPrimaryKey(tSettleAccount);
            //微信放款
//            String paymentNo = weiXinTransfers(id);
//            if(!StringUtils.isEmpty(paymentNo)) {
//                tSettleAccount.setRemittanceState(1);
//                tSettleAccount.setPaymentNo(paymentNo);
//                settleAccountMapper.updateByPrimaryKey(tSettleAccount);
//            }else {
//                tSettleAccount.setRemittanceState(2);
//                settleAccountMapper.updateByPrimaryKey(tSettleAccount);
//            }
            return true;
        }
        return false;
    }

    /**
     * 微信放款
     */
    private String weiXinTransfers(int id) throws Exception {
        WeiXinTransfers transfers = new WeiXinTransfers();
        transfers.setMch_appid(Configure.getMch_appid());
        transfers.setMchid(Configure.getMch_id());
        transfers.setNonce_str(RandomStringGenerator.getRandomStringByLength(32));
        transfers.setPartner_trade_no(id + "");
        transfers.setOpenid("og3kv5f8yvr6rXpkbdYA11lsvrbU");
        transfers.setCheck_name("NO_CHECK");
        transfers.setAmount("1");
        transfers.setDesc("影盟代理提成");
        transfers.setSpbill_create_ip(Configure.getSpbill_create_ip());
        //生成签名
        String sign = Signature.getSign(transfers);
        transfers.setSign(sign);
        String result = HttpRequest.sendPost("https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers", transfers, true);
        log.info("---------微信放款返回:" + result);
        XStream xStream = new XStream();
        xStream.alias("xml", WeiXinTransfersReturn.class);
        WeiXinTransfersReturn returnInfo = (WeiXinTransfersReturn) xStream.fromXML(result);
        returnInfo.toString();
        if ("SUCCESS".equals(returnInfo.getReturn_code()) && "SUCCESS".equals(returnInfo.getResult_code())) {
            log.info("放款成功 结算id = "+id);
            return returnInfo.getPayment_no();
        } else {
            log.info("放款失败 结算id = "+id);
            return "";
        }
    }
}