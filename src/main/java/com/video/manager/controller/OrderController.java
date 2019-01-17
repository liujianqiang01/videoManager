package com.video.manager.controller;

import com.github.pagehelper.PageInfo;
import com.video.manager.model.BasePage;
import com.video.manager.model.TOrder;
import com.video.manager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-16
 * @Description:
 */
@Controller
@RequestMapping("admin/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @RequestMapping(method = RequestMethod.GET)
    public String index(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10")int pageSize,
                        TOrder order, ModelMap map){
        BasePage.beanToMap(order,map);
        PageInfo<TOrder> orderPageInfo =orderService.getOrder(order,page,pageSize);
        BasePage.page(orderPageInfo,map);
        return "/order/index";
    }
}