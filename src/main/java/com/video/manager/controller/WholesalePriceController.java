package com.video.manager.controller;

import com.github.pagehelper.PageInfo;
import com.video.manager.model.BasePage;
import com.video.manager.model.TWholesalePrice;
import com.video.manager.service.WholesalePriceService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-21
 * @Description:
 */
@Controller
@RequestMapping("admin/wholesalePrice")
@Log4j
public class WholesalePriceController {

    @Autowired
    WholesalePriceService wholesalePriceService;
    @RequestMapping(method = RequestMethod.GET)
    public String index(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize,
                        TWholesalePrice wholesalePrice, ModelMap map) {
        BasePage.beanToMap(wholesalePrice, map);
        PageInfo<TWholesalePrice> orderPageInfo = wholesalePriceService.getWholesalePriceService(wholesalePrice,page,pageSize);
        BasePage.page(orderPageInfo, map);
        return "/wholesale/index";
    }
}