package com.video.manager.controller;

import com.github.pagehelper.PageInfo;
import com.video.manager.model.BasePage;
import com.video.manager.model.TMerchant;
import com.video.manager.service.MerchantService;
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
@RequestMapping("admin/merchant")
public class MerchantController {
    @Autowired
    MerchantService merchantService;
    @RequestMapping(method = RequestMethod.GET)
    public String index(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10")int pageSize,
                        TMerchant merchant, ModelMap map){
        BasePage.beanToMap(merchant,map);
        PageInfo<TMerchant> orderPageInfo =merchantService.getMerchant(merchant,page,pageSize);
        BasePage.page(orderPageInfo,map);
        return "/merchant/index";
    }
}