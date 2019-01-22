package com.video.manager.controller;

import com.github.pagehelper.PageInfo;
import com.video.manager.model.*;
import com.video.manager.service.WholesalePriceService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return "/wholesalePrice/index";
    }

    /**
     * 进入编辑页面
     * @return
     */
    @RequestMapping(value = "edit",method = RequestMethod.GET)
    public String edit(){
        return "/wholesalePrice/edit";
    }
    /**
     * 更新信息

     * @return
     */
    @RequestMapping(value = "edit",method = RequestMethod.POST)
    @ResponseBody
    public WebResult edit(TWholesalePrice wholesalePrice){
        if(wholesalePrice.getVipCountEnd() <= wholesalePrice.getVipCountStart()){
            return WebResult.needParams("开始数量不能小于结束数量");
        }
       return wholesalePriceService.addWholesalePriceService(wholesalePrice);
    }

    @RequestMapping(value = "delete",method = RequestMethod.POST)
    @ResponseBody
    public WebResult delete(TWholesalePrice wholesalePrice){
        if(wholesalePrice == null || wholesalePrice.getId() ==null){
            return WebResult.needParams("参数为空");
        }
         wholesalePriceService.delete(wholesalePrice.getId());
        return WebResult.success();
    }
}