package com.video.manager.task;

import com.video.manager.model.TMerchant;
import com.video.manager.service.MerchantService;
import com.video.manager.service.SettleAccoountService;
import com.video.manager.utils.DateUtils;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-18
 * @Description:
 */
@Component
@Log4j
public class SettleAccountTask {
    @Autowired
    SettleAccoountService settleAccoountService;
    @Autowired
    MerchantService merchantService;
    @Scheduled(cron = "0 0/30 * * * ?")
    public void unPayTask(){
        log.info("------结算定时开始---start-");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String thisMounthFirstDay = DateUtils.getThisMounthFirstDay();
        try {
            Date date = format.parse(thisMounthFirstDay);
            TMerchant merchant = new TMerchant();
            merchant.setState(1);
            List<TMerchant> merchants = merchantService.select(merchant);
            for(TMerchant m : merchants){
                try {
                    settleAccoountService.settleAccoountTask(date,m.getMenchantId(),m.getRate());
                }catch (Exception e){
                    log.error("结算失败 merchantId = "+ m.getMenchantId(),e);
                }

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        log.info("------结算定时结束---end-");
    }
}