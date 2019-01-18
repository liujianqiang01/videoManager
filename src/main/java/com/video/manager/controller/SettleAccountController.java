package com.video.manager.controller;

import com.github.pagehelper.PageInfo;
import com.video.manager.model.BasePage;
import com.video.manager.model.TSettleAccount;
import com.video.manager.model.WebResult;
import com.video.manager.service.SettleAccoountService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-17
 * @Description:
 */
@Controller
@RequestMapping("admin/settleAccount")
@Log4j
public class SettleAccountController {
    @Autowired
    SettleAccoountService settleAccoountService;
    @RequestMapping(method = RequestMethod.GET)
    public String index(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10")int pageSize,
                        TSettleAccount settleAccount, ModelMap map){
        BasePage.beanToMap(settleAccount,map);
        PageInfo<TSettleAccount> orderPageInfo = settleAccoountService.getSettleAccoount(settleAccount,page,pageSize);
        BasePage.page(orderPageInfo,map);
        return "/settleAccount/index";
    }

    @PostMapping("grant")
    @ResponseBody
    public WebResult grant(int id){
        log.info("发放收益 id = "+id);
        boolean grant = settleAccoountService.grant(id);
        if(grant){
            return WebResult.success();
        }else{
            return WebResult.needParams("发放失败");
        }
    }
}