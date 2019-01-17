package com.video.manager.controller;

import com.github.pagehelper.PageInfo;
import com.video.manager.model.BasePage;
import com.video.manager.model.TSettleAccount;
import com.video.manager.model.TVipCodes;
import com.video.manager.service.SettleAccoountService;
import com.video.manager.service.VipCodesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-17
 * @Description:
 */
@Controller
@RequestMapping("admin/vipCodes")
public class VipCodesController {
    @Autowired
    VipCodesService vipCodesService;
    @RequestMapping(method = RequestMethod.GET)
    public String index(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10")int pageSize,
                        TVipCodes vipCodes, ModelMap map){
        BasePage.beanToMap(vipCodes,map);
        PageInfo<TVipCodes> orderPageInfo = vipCodesService.getVipCodes(vipCodes,page,pageSize);
        BasePage.page(orderPageInfo,map);
        return "/vipCodes/index";
    }
}