package com.video.manager.controller;

import com.github.pagehelper.PageInfo;
import com.video.manager.model.BasePage;
import com.video.manager.model.TWholesaleOrder;
import com.video.manager.service.WholesaleOrderService;
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
@RequestMapping("admin/wholesaleOrder")
@Log4j
public class WholesaleOrderController {
    @Autowired
    WholesaleOrderService wholesaleOrderService;
    @RequestMapping(method = RequestMethod.GET)
    public String index(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize,
                        TWholesaleOrder wholesaleOrder, ModelMap map) {
        BasePage.beanToMap(wholesaleOrder, map);
        PageInfo<TWholesaleOrder> orderPageInfo = wholesaleOrderService.getWholesaleOrderService(wholesaleOrder,page,pageSize);
        BasePage.page(orderPageInfo, map);
        return "/wholesaleOrder/index";
    }
}